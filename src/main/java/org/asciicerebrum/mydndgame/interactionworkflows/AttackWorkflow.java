package org.asciicerebrum.mydndgame.interactionworkflows;

import org.asciicerebrum.mydndgame.Damage;
import org.asciicerebrum.mydndgame.interfaces.entities.IDamage;
import org.asciicerebrum.mydndgame.interfaces.entities.IDiceAction;
import org.asciicerebrum.mydndgame.interfaces.entities.IInteraction;
import org.asciicerebrum.mydndgame.interfaces.entities.ISituationContext;
import org.asciicerebrum.mydndgame.interfaces.entities.IWeapon;
import org.asciicerebrum.mydndgame.interfaces.entities.IWorkflow;
import org.asciicerebrum.mydndgame.interfaces.managers.IDiceRollManager;

/**
 *
 * @author species8472
 */
public class AttackWorkflow implements IWorkflow {

    /**
     * The dice action for attacking. It contains the dice for this interaction.
     */
    private IDiceAction attackAction;

    /**
     * Service for rolling dice.
     */
    private IDiceRollManager diceRollManager;

    /**
     * Roll results that leads to automatic failure.
     */
    private Long autoFailureRoll;

    /**
     * Roll result that leads to automatic success.
     */
    private Long autoSuccessRoll;

    /**
     * The least damage you can do on a successful attack roll.
     */
    private Long minimumDamage;

    /**
     * {@inheritDoc} Performs an attack on the given target.
     */
    @Override
    public final void runWorkflow(final IInteraction interaction) {

        // make an attack roll and add attack bonus
        Long atkRollResult
                = this.getDiceRollManager().rollDice(this.getAttackAction());

        // consider automatic failure and success
        if (atkRollResult <= this.getAutoFailureRoll()) {
            return;
        }

        Long sourceAtkBonus = interaction.getTriggeringCharacter()
                .getAtkBoni().get(0);
        Long totalAtkResult = atkRollResult + sourceAtkBonus;

        Long targetAc = interaction.getTargetCharacters().get(0).getAc();

        // check against target's ac
        // not able to surpass foe's ac and also no auto success
        if (totalAtkResult < targetAc
                && atkRollResult < this.getAutoSuccessRoll()) {
            return;
        }

        ISituationContext sourceContext = interaction.getTriggeringCharacter()
                .getSituationContext();
        IWeapon sourceWeapon = (IWeapon) interaction.getTriggeringCharacter()
                .getBodySlotByType(sourceContext.getBodySlotType()).getItem();

        // when you are here you have hit the enemy!!!
        // it could be critical
        Boolean isFirstCritical
                = atkRollResult >= sourceWeapon.getCriticalMinimumLevel();

        // roll again to assure critical!!!
        Boolean isSecondCritical = Boolean.FALSE;
        if (isFirstCritical) {
            Long secondAtkRollResult
                    = this.getDiceRollManager()
                    .rollDice(this.getAttackAction());

            isSecondCritical
                    = secondAtkRollResult
                    >= sourceWeapon.getCriticalMinimumLevel();
        }

        // standard when there is no critical hit
        Integer damageMuliplicator = 1;
        // consider critical hits and damage multiplication!
        if (isFirstCritical && isSecondCritical) {
            damageMuliplicator = sourceWeapon.getCriticalFactor();
        }

        // make a damage roll
        Long sourceDamageRollResult = 0L;
        for (int i = 0; i < damageMuliplicator; i++) {
            sourceDamageRollResult += this.getDiceRollManager()
                    .rollDice(sourceWeapon.getDamage());
        }

        Long sourceDamageBonus = interaction.getTriggeringCharacter()
                .getDamageBonus();
        // consider minimum damage!
        Long totalSourceDamage
                = Math.max(this.getMinimumDamage(),
                        sourceDamageRollResult + sourceDamageBonus);

        // apply damage: this must be done at the character (special method for
        // applying damage) because special attributes like damage reduction
        // could apply! (character has observers for that.)
        //TODO consider possible sneak attack of rogue, etc.
        //TODO an attack could inflict multiple types of damage,
        // e.g. +1d6 poison, etc. So we deliver a list of damages.
        IDamage sourceDamage = new Damage();
        sourceDamage.setDamageValue(totalSourceDamage);
        sourceDamage.setWeapon(sourceWeapon);
        sourceDamage.setDamageType(sourceContext.getDamageType());

        interaction.getTargetCharacters().get(0).applyDamage(sourceDamage);

        // set conditions, e.g. unconscious --> this is done in the character!
    }

    /**
     * @return the attackAction
     */
    public final IDiceAction getAttackAction() {
        return attackAction;
    }

    /**
     * @param attackActionInput the attackAction to set
     */
    public final void setAttackAction(final IDiceAction attackActionInput) {
        this.attackAction = attackActionInput;
    }

    /**
     * @return the diceRollManager
     */
    public final IDiceRollManager getDiceRollManager() {
        return diceRollManager;
    }

    /**
     * @param diceRollManagerInput the diceRollManager to set
     */
    public final void setDiceRollManager(
            final IDiceRollManager diceRollManagerInput) {
        this.diceRollManager = diceRollManagerInput;
    }

    /**
     * @return the autoFailureRoll
     */
    public final Long getAutoFailureRoll() {
        return autoFailureRoll;
    }

    /**
     * @param autoFailureRollInput the autoFailureRoll to set
     */
    public final void setAutoFailureRoll(final Long autoFailureRollInput) {
        this.autoFailureRoll = autoFailureRollInput;
    }

    /**
     * @return the autoSuccessRoll
     */
    public final Long getAutoSuccessRoll() {
        return autoSuccessRoll;
    }

    /**
     * @param autoSuccessRollInput the autoSuccessRoll to set
     */
    public final void setAutoSuccessRoll(final Long autoSuccessRollInput) {
        this.autoSuccessRoll = autoSuccessRollInput;
    }

    /**
     * @return the minimumDamage
     */
    public final Long getMinimumDamage() {
        return minimumDamage;
    }

    /**
     * @param minimumDamageInput the minimumDamage to set
     */
    public final void setMinimumDamage(final Long minimumDamageInput) {
        this.minimumDamage = minimumDamageInput;
    }

}
