package org.asciicerebrum.mydndgame.interactionworkflows;

import org.asciicerebrum.mydndgame.interfaces.entities.IDice;
import org.asciicerebrum.mydndgame.interfaces.entities.IDiceAction;
import org.asciicerebrum.mydndgame.interfaces.entities.IInteraction;
import org.asciicerebrum.mydndgame.interfaces.entities.IWorkflow;
import org.asciicerebrum.mydndgame.managers.DiceRollManager;

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
    private DiceRollManager diceRollManager;

    /**
     * Roll results that leads to automatic failure.
     */
    private Long autoFailureRoll;

    /**
     * Roll result that leads to automatic success.
     */
    private Long autoSuccessRoll;

    /**
     * {@inheritDoc} Performs an attack on the given target.
     */
    @Override
    public final void runWorkflow(final IInteraction interaction) {

        // make an attack roll and add attack bonus
        IDice diceType = this.getAttackAction().getDiceType();
        Long diceNumber = this.getAttackAction().getDiceNumber();

        Long rollResult
                = this.getDiceRollManager().rollDice(diceType, diceNumber);

        // consider automatic failure and success
        if (rollResult <= this.getAutoFailureRoll()) {
            return;
        } else if (rollResult >= this.getAutoSuccessRoll()) {

        } else {
            // it could be critical
        }

        // check against target's ac
        // consider critical hits and damage multiplication!
        // consider minimum damage!
        // make a damage roll
        // apply damage: this must be done at the character (special method for
        // applying damage) because special attributes like damage reduction
        // could apply! (character has observers for that.)
        //  
        // set conditions, e.g. unconscious
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
    public final DiceRollManager getDiceRollManager() {
        return diceRollManager;
    }

    /**
     * @param diceRollManagerInput the diceRollManager to set
     */
    public final void setDiceRollManager(
            final DiceRollManager diceRollManagerInput) {
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

}
