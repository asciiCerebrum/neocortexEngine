package org.asciicerebrum.mydndgame.mechanics.interactionworkflows;

import org.asciicerebrum.mydndgame.domain.mechanics.workflow.IWorkflow;
import org.asciicerebrum.mydndgame.domain.core.particles.ArmorClass;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusRank;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.core.particles.DiceRoll;
import org.asciicerebrum.mydndgame.domain.game.InventoryItem;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.domain.ruleentities.DiceAction;
import org.asciicerebrum.mydndgame.domain.mechanics.workflow.Interaction;
import org.asciicerebrum.mydndgame.facades.game.WeaponServiceFacade;
import org.asciicerebrum.mydndgame.managers.DiceRollManager;
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
    private DiceRollManager diceRollManager;

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
                = this.situationContextService.getActiveItem(interaction
                        .getTriggeringCharacter());
        
        if (!(sourceWeapon instanceof Weapon)) {
            return;
        }

        // make an attack roll and add attack bonus
        DiceRoll atkRollResultRaw
                = this.diceRollManager.rollDice(this.attackAction);

        // consider automatic failure and success
        if (atkRollResultRaw.lessThanOrEqualTo(this.autoFailureRoll)) {
            return;
        }
        
        BonusValue sourceAtkBonus
                = this.atkService.calcAtkBoni((Weapon) sourceWeapon,
                        interaction.getTriggeringCharacter())
                .getBonusValueByRank(this.atkBonusRank);
        
        DiceRoll atkRollResult = atkRollResultRaw.add(sourceAtkBonus);

        // single attack always attacks the first of the target characters.
        ArmorClass targetAc
                = this.acService.calcAc(interaction.getFirstTargetCharacter());

        // check against target's ac
        // not able to surpass foe's ac and also no auto success
        if (atkRollResult.lessThan(targetAc)
                && atkRollResult.lessThan(this.autoSuccessRoll)) {
            return;
        }
        
        boolean isCritical = this.determineCritical(atkRollResultRaw,
                sourceAtkBonus, targetAc, interaction);
        
        this.finalize(isCritical, interaction);
    }
    
    final void finalize(final boolean isCritical,
            final Interaction interaction) {
        if (isCritical && this.criticalDamageWorkflow != null) {
            this.criticalDamageWorkflow.runWorkflow(interaction);
        }
        if (!isCritical && this.damageWorkflow != null) {
            this.damageWorkflow.runWorkflow(interaction);
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
            final Interaction interaction) {
        
        final Weapon sourceWeapon
                = (Weapon) this.situationContextService.getActiveItem(
                        interaction.getTriggeringCharacter());

        // when you are here you have hit the enemy!!!
        // it could be critical
        final Boolean isThreat
                = atkRollResultRaw.greaterThanOrEqualTo(
                        this.weaponServiceFacade.getCriticalMinimumLevel(
                                sourceWeapon,
                                interaction.getTriggeringCharacter()));
        
        Boolean isCritical = false;
        if (isThreat) {
            final DiceRoll secondAtkRollResultRaw
                    = this.diceRollManager.rollDice(this.attackAction);
            final DiceRoll secondAtkRollResult
                    = secondAtkRollResultRaw.add(sourceAtkBonus);
            isCritical
                    = secondAtkRollResult.greaterThanOrEqualTo(targetAc)
                    || secondAtkRollResult.greaterThanOrEqualTo(
                            this.autoSuccessRoll);
        }
        return isCritical;
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
            final DiceRollManager diceRollManagerInput) {
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
    
}
