package org.asciicerebrum.neocortexengine.mechanics.interactionworkflows;

import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.core.particles.ArmorClass;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusRank;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusValue;
import org.asciicerebrum.neocortexengine.domain.core.particles.CriticalMinimumLevel;
import org.asciicerebrum.neocortexengine.domain.core.particles.DiceRoll;
import org.asciicerebrum.neocortexengine.domain.core.particles.EventFact;
import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueIds;
import org.asciicerebrum.neocortexengine.domain.events.EventEntry;
import org.asciicerebrum.neocortexengine.domain.events.EventType;
import org.asciicerebrum.neocortexengine.domain.game.Campaign;
import org.asciicerebrum.neocortexengine.domain.game.Weapon;
import org.asciicerebrum.neocortexengine.domain.mechanics.workflow.IWorkflow;
import org.asciicerebrum.neocortexengine.domain.mechanics.workflow.Interaction;
import org.asciicerebrum.neocortexengine.domain.ruleentities.DiceAction;
import org.asciicerebrum.neocortexengine.domain.ruleentities.composition.RollResult;
import org.asciicerebrum.neocortexengine.facades.game.WeaponServiceFacade;
import org.asciicerebrum.neocortexengine.mechanics.managers.RollResultManager;
import org.asciicerebrum.neocortexengine.services.context.EntityPoolService;
import org.asciicerebrum.neocortexengine.services.context.SituationContextService;
import org.asciicerebrum.neocortexengine.services.events.EventTriggerService;
import org.asciicerebrum.neocortexengine.services.statistics.AcCalculationService;
import org.asciicerebrum.neocortexengine.services.statistics.AtkCalculationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author species8472
 */
public class SingleAttackWorkflow implements IWorkflow {

    /**
     * The logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(
            SingleAttackWorkflow.class);

    /**
     * The dice action for attacking. It contains the dice for this interaction.
     */
    private DiceAction attackAction;

    /**
     * Service for rolling dice.
     */
    private RollResultManager rollResultManager;

    /**
     * Roll results that leads to automatic failure.
     */
    private DiceRoll autoFailureRoll;

    /**
     * Roll result that leads to automatic success.
     */
    private DiceRoll autoSuccessRoll;

    /**
     * Determination of damage effects and applying them on the character.
     */
    private IWorkflow damageWorkflow;

    /**
     * Damage effects for a critical hit.
     */
    private IWorkflow criticalDamageWorkflow;

    /**
     * The attack calculation service.
     */
    private AtkCalculationService atkService;

    /**
     * The armor class calculation service.
     */
    private AcCalculationService acService;

    /**
     * Getting modified real-time-values from the weapon.
     */
    private WeaponServiceFacade weaponServiceFacade;

    /**
     * The bonus rank of this single attack. Normally rank 0 when it is a
     * standard attack.
     */
    private BonusRank atkBonusRank = BonusRank.RANK_0;

    /**
     * Getting settings from the character.
     */
    private SituationContextService situationContextService;

    /**
     * Triggering events.
     */
    private EventTriggerService eventTriggerService;

    /**
     * The entity pool service.
     */
    private EntityPoolService entityPoolService;

    /**
     * {@inheritDoc} Performs an attack on the given target.
     */
    @Override
    public final void runWorkflow(final Interaction interaction,
            final Campaign campaign) {

        final UniqueEntity sourceWeapon
                = this.getEntityPoolService().getEntityById(
                        this.getSituationContextService()
                        .getActiveItemId(interaction.getTriggeringCharacter()));

        if (!(sourceWeapon instanceof Weapon)) {
            LOG.info("{} has not setup a weapon to attack. Skipping.",
                    interaction.getTriggeringCharacter().getUniqueId());
            return;
        }

        final EventEntry preEvent
                = new EventEntry(EventType.SINGLE_ATTACK_PRE);
        preEvent.setWho(interaction.getTriggeringCharacter().getUniqueId());
        preEvent.setWhom(new UniqueIds(interaction.getFirstTargetCharacter()
                .getUniqueId()));
        preEvent.setWhen(campaign.getCombatRound().getCurrentDate());
        preEvent.setWhat(sourceWeapon.getUniqueId());
        this.getEventTriggerService().trigger(preEvent);

        final BonusValue sourceAtkBonus
                = this.getAtkService().calcAtkBoni((Weapon) sourceWeapon,
                        interaction.getTriggeringCharacter())
                .getBonusValueByRank(this.getAtkBonusRank());

        // make an attack roll and add attack bonus
        final RollResult atkRollResult
                = this.getRollResultManager().retrieveRollResult(
                        sourceAtkBonus,
                        this.getAttackAction(),
                        sourceWeapon,
                        interaction.getTriggeringCharacter(),
                        null,
                        campaign.getCombatRound().getCurrentDate(), campaign);

        // single attack always attacks the first of the target characters.
        final ArmorClass targetAc
                = this.getAcService().calcAc(interaction
                        .getFirstTargetCharacter());

        if (!this.isHit(atkRollResult, targetAc)) {
            this.triggerResultEvent(EventType.SINGLE_ATTACK_MISS, interaction,
                    campaign, targetAc);
            return;
        }

        final CriticalMinimumLevel critMinLvl = this.getWeaponServiceFacade()
                .getCriticalMinimumLevel((Weapon) sourceWeapon,
                        interaction.getTriggeringCharacter());

        boolean isCritical = this.determineCritical(atkRollResult, targetAc,
                critMinLvl, sourceWeapon, interaction, campaign);

        this.terminate(isCritical, interaction, campaign, targetAc);
    }

    /**
     * Constructs and triggers the correct event depending on the outcome of
     * this attack roll.
     *
     * @param eventType the type of result event. E.g. hit or miss.
     * @param interaction the interaction of the workflow.
     * @param campaign the campaign for this workflow.
     * @param targetAc the AC of the target character.
     */
    private void triggerResultEvent(final EventType eventType,
            final Interaction interaction, final Campaign campaign,
            final ArmorClass targetAc) {
        if (this.getEventTriggerService() == null) {
            return;
        }
        final EventEntry resultEvent = new EventEntry(eventType);
        resultEvent.setWho(interaction
                .getTriggeringCharacter().getUniqueId());
        resultEvent.setWhom(new UniqueIds(interaction
                .getFirstTargetCharacter().getUniqueId()));
        resultEvent.setWhen(campaign.getCombatRound().getCurrentDate());
        resultEvent.addEventFact(new EventFact(
                Long.toString(targetAc.getValue())));
        this.getEventTriggerService().trigger(resultEvent);
    }

    /**
     * Terminates the workflow by making the decision whether to continue with a
     * critical damage workflow or a standard one.
     *
     * @param isCritical if hit was critical.
     * @param interaction the interaction.
     * @param campaign the campaign.
     * @param targetAc the AC of the target character.
     */
    final void terminate(final boolean isCritical,
            final Interaction interaction, final Campaign campaign,
            final ArmorClass targetAc) {
        if (isCritical) {
            this.triggerResultEvent(EventType.SINGLE_ATTACK_HIT_CRITICAL,
                    interaction, campaign, targetAc);
            this.getCriticalDamageWorkflow().runWorkflow(interaction, campaign);
            return;
        }
        this.triggerResultEvent(EventType.SINGLE_ATTACK_HIT, interaction,
                campaign, targetAc);
        this.getDamageWorkflow().runWorkflow(interaction, campaign);
    }

    /**
     * Determines if an attack is critical or not.
     *
     * @param atkRollResult the result of the first attack roll.
     * @param targetAc the armor class of the target character.
     * @param sourceCritMinLvl the criticial minimum level for the weapon.
     * @param sourceWeapon the weapon used in the attack.
     * @param interaction the interaction.
     * @param campaign the campaign.
     * @return the criticallity of the attack.
     */
    final boolean determineCritical(final RollResult atkRollResult,
            final ArmorClass targetAc,
            final CriticalMinimumLevel sourceCritMinLvl,
            final UniqueEntity sourceWeapon, final Interaction interaction,
            final Campaign campaign) {

        // when you are here you have hit the enemy!!!
        // it could be critical
        final Boolean isThreat
                = atkRollResult.getDiceRoll()
                .greaterThanOrEqualTo(sourceCritMinLvl);

        Boolean isCritical = false;
        if (isThreat) {
            final RollResult secondAtkRollResult
                    = this.getRollResultManager().retrieveRollResult(
                            atkRollResult.getBonusValue(),
                            this.getAttackAction(),
                            sourceWeapon,
                            interaction.getTriggeringCharacter(),
                            null,
                            campaign.getCombatRound().getCurrentDate(),
                            campaign);

            isCritical = this.isHit(secondAtkRollResult, targetAc);
        }
        return isCritical;
    }

    /**
     * Determines if an attack roll hits the target. Considers automatic failure
     * and success. Checked against target's ac.
     *
     * @param rollResult the attack roll result.
     * @param targetAc the target's armor class.
     * @return true if attack was successfull, false otherwise.
     */
    final boolean isHit(final RollResult rollResult,
            final ArmorClass targetAc) {
        return rollResult.getDiceRoll().greaterThan(this.getAutoFailureRoll())
                && (rollResult.calcTotalResult().greaterThanOrEqualTo(targetAc)
                || rollResult.getDiceRoll()
                .greaterThanOrEqualTo(this.getAutoSuccessRoll()));
    }

    /**
     * @param attackActionInput the attackAction to set
     */
    public final void setAttackAction(final DiceAction attackActionInput) {
        this.attackAction = attackActionInput;
    }

    /**
     * @param damageWorkflowInput the damageWorkflow to set
     */
    public final void setDamageWorkflow(
            final IWorkflow damageWorkflowInput) {
        this.damageWorkflow = damageWorkflowInput;
    }

    /**
     * @param autoFailureRollInput the autoFailureRoll to set
     */
    public final void setAutoFailureRoll(final DiceRoll autoFailureRollInput) {
        this.autoFailureRoll = autoFailureRollInput;
    }

    /**
     * @param autoSuccessRollInput the autoSuccessRoll to set
     */
    public final void setAutoSuccessRoll(final DiceRoll autoSuccessRollInput) {
        this.autoSuccessRoll = autoSuccessRollInput;
    }

    /**
     * @param atkServiceInput the atkService to set
     */
    public final void setAtkService(
            final AtkCalculationService atkServiceInput) {
        this.atkService = atkServiceInput;
    }

    /**
     * @param acServiceInput the acService to set
     */
    public final void setAcService(final AcCalculationService acServiceInput) {
        this.acService = acServiceInput;
    }

    /**
     * @param atkBonusRankInput the atkBonusRank to set
     */
    public final void setAtkBonusRank(final BonusRank atkBonusRankInput) {
        this.atkBonusRank = atkBonusRankInput;
    }

    /**
     * @param criticalDamageWorkflowInput the criticalDamageWorkflow to set
     */
    public final void setCriticalDamageWorkflow(
            final IWorkflow criticalDamageWorkflowInput) {
        this.criticalDamageWorkflow = criticalDamageWorkflowInput;
    }

    /**
     * @param situationContextServiceInput the situationContextService to set
     */
    public final void setSituationContextService(
            final SituationContextService situationContextServiceInput) {
        this.situationContextService = situationContextServiceInput;
    }

    /**
     * @param weaponServiceFacadeInput the weaponServiceFacade to set
     */
    public final void setWeaponServiceFacade(
            final WeaponServiceFacade weaponServiceFacadeInput) {
        this.weaponServiceFacade = weaponServiceFacadeInput;
    }

    /**
     * @return the attackAction
     */
    public final DiceAction getAttackAction() {
        return attackAction;
    }

    /**
     * @return the autoFailureRoll
     */
    public final DiceRoll getAutoFailureRoll() {
        return autoFailureRoll;
    }

    /**
     * @return the autoSuccessRoll
     */
    public final DiceRoll getAutoSuccessRoll() {
        return autoSuccessRoll;
    }

    /**
     * @return the damageWorkflow
     */
    public final IWorkflow getDamageWorkflow() {
        return damageWorkflow;
    }

    /**
     * @return the atkService
     */
    public final AtkCalculationService getAtkService() {
        return atkService;
    }

    /**
     * @return the acService
     */
    public final AcCalculationService getAcService() {
        return acService;
    }

    /**
     * @return the weaponServiceFacade
     */
    public final WeaponServiceFacade getWeaponServiceFacade() {
        return weaponServiceFacade;
    }

    /**
     * @return the atkBonusRank
     */
    public final BonusRank getAtkBonusRank() {
        return atkBonusRank;
    }

    /**
     * @return the situationContextService
     */
    public final SituationContextService getSituationContextService() {
        return situationContextService;
    }

    /**
     * @return the criticalDamageWorkflow
     */
    public final IWorkflow getCriticalDamageWorkflow() {
        return criticalDamageWorkflow;
    }

    /**
     * @return the rollResultManager
     */
    public final RollResultManager getRollResultManager() {
        return rollResultManager;
    }

    /**
     * @param rollResultManagerInput the rollResultManager to set
     */
    public final void setRollResultManager(
            final RollResultManager rollResultManagerInput) {
        this.rollResultManager = rollResultManagerInput;
    }

    /**
     * @return the eventTriggerService
     */
    public final EventTriggerService getEventTriggerService() {
        return eventTriggerService;
    }

    /**
     * @param eventTriggerServiceInput the eventTriggerService to set
     */
    public final void setEventTriggerService(
            final EventTriggerService eventTriggerServiceInput) {
        this.eventTriggerService = eventTriggerServiceInput;
    }

    /**
     * @return the entityPoolService
     */
    public final EntityPoolService getEntityPoolService() {
        return entityPoolService;
    }

    /**
     * @param entityPoolServiceInput the entityPoolService to set
     */
    public final void setEntityPoolService(
            final EntityPoolService entityPoolServiceInput) {
        this.entityPoolService = entityPoolServiceInput;
    }

}
