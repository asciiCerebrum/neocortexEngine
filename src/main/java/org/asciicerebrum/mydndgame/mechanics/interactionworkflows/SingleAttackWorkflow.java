package org.asciicerebrum.mydndgame.mechanics.interactionworkflows;

import org.asciicerebrum.mydndgame.domain.core.particles.ArmorClass;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusRank;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.core.particles.DiceRoll;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.InventoryItem;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.domain.mechanics.workflow.IWorkflow;
import org.asciicerebrum.mydndgame.domain.mechanics.workflow.Interaction;
import org.asciicerebrum.mydndgame.domain.ruleentities.DiceAction;
import org.asciicerebrum.mydndgame.facades.game.WeaponServiceFacade;
import org.asciicerebrum.mydndgame.managers.DefaultDiceRollManager;
import org.asciicerebrum.mydndgame.services.context.SituationContextService;
import org.asciicerebrum.mydndgame.services.statistics.AcCalculationService;
import org.asciicerebrum.mydndgame.services.statistics.AtkCalculationService;

/**
 *
 * @author species8472
 */
public class SingleAttackWorkflow implements IWorkflow {

    /**
     * The dice action for attacking. It contains the dice for this interaction.
     */
    private DiceAction attackAction;

    /**
     * Service for rolling dice.
     */
    private DefaultDiceRollManager diceRollManager;

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

    private AtkCalculationService atkService;

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
     * {@inheritDoc} Performs an attack on the given target.
     */
    @Override
    public final void runWorkflow(final Interaction interaction) {

        final InventoryItem sourceWeapon
                = this.getSituationContextService().getActiveItem(interaction
                        .getTriggeringCharacter());

        if (!(sourceWeapon instanceof Weapon)) {
            return;
        }

        // make an attack roll and add attack bonus
        DiceRoll atkRollResultRaw
                = this.getDiceRollManager().rollDice(this.getAttackAction());

        // consider automatic failure and success
        if (atkRollResultRaw.lessThanOrEqualTo(this.getAutoFailureRoll())) {
            return;
        }

        BonusValue sourceAtkBonus
                = this.getAtkService().calcAtkBoni((Weapon) sourceWeapon,
                        interaction.getTriggeringCharacter())
                .getBonusValueByRank(this.getAtkBonusRank());

        DiceRoll atkRollResult = atkRollResultRaw.add(sourceAtkBonus);

        // single attack always attacks the first of the target characters.
        ArmorClass targetAc
                = this.getAcService().calcAc(interaction
                        .getFirstTargetCharacter());

        // check against target's ac
        // not able to surpass foe's ac and also no auto success
        if (atkRollResult.lessThan(targetAc)
                && atkRollResult.lessThan(this.getAutoSuccessRoll())) {
            return;
        }

        boolean isCritical = this.determineCritical(atkRollResultRaw,
                sourceAtkBonus, targetAc, interaction.getTriggeringCharacter());

        this.terminate(isCritical, interaction);
    }

    final void terminate(final boolean isCritical,
            final Interaction interaction) {
        if (isCritical && this.criticalDamageWorkflow != null) {
            this.criticalDamageWorkflow.runWorkflow(interaction);
        }
        if (!isCritical && this.getDamageWorkflow() != null) {
            this.getDamageWorkflow().runWorkflow(interaction);
        }
    }

    /**
     * Determines if an attack is critical or not.
     *
     * @param atkRollResult the result of the first attack roll.
     * @param sourceAtkBonus the bonus that is added to the attack roll.
     * @param targetAc the armor class of the target character.
     * @param interaction the interaction object.
     * @return the criticallity of the attack.
     */
    final boolean determineCritical(final DiceRoll atkRollResultRaw,
            final BonusValue sourceAtkBonus,
            final ArmorClass targetAc,
            final DndCharacter triggeringCharacter) {

        final Weapon sourceWeapon
                = (Weapon) this.getSituationContextService().getActiveItem(
                        triggeringCharacter);

        // when you are here you have hit the enemy!!!
        // it could be critical
        final Boolean isThreat
                = atkRollResultRaw.greaterThanOrEqualTo(
                        this.getWeaponServiceFacade().getCriticalMinimumLevel(
                                sourceWeapon, triggeringCharacter));

        Boolean isCritical = false;
        if (isThreat) {
            final DiceRoll secondAtkRollResultRaw
                    = this.getDiceRollManager().rollDice(
                            this.getAttackAction());
            final DiceRoll secondAtkRollResult
                    = secondAtkRollResultRaw.add(sourceAtkBonus);
            isCritical = this.isHit(secondAtkRollResult, targetAc);
        }
        return isCritical;
    }

    /**
     * Determines if an attack roll hits the target.
     *
     * @param atkRollResult the rsult of the attack roll.
     * @param targetAc the target's armor class.
     * @return true if attack was successfull, false otherwise.
     */
    final boolean isHit(final DiceRoll atkRollResult,
            final ArmorClass targetAc) {
        return atkRollResult.greaterThanOrEqualTo(targetAc)
                || atkRollResult.greaterThanOrEqualTo(
                        this.getAutoSuccessRoll());
    }

    /**
     * @param attackActionInput the attackAction to set
     */
    public final void setAttackAction(final DiceAction attackActionInput) {
        this.attackAction = attackActionInput;
    }

    /**
     * @param diceRollManagerInput the diceRollManager to set
     */
    public final void setDiceRollManager(
            final DefaultDiceRollManager diceRollManagerInput) {
        this.diceRollManager = diceRollManagerInput;
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
     * @return the diceRollManager
     */
    public final DefaultDiceRollManager getDiceRollManager() {
        return diceRollManager;
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

}
