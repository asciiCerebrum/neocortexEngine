package org.asciicerebrum.mydndgame.interfaces.managers;

import org.asciicerebrum.mydndgame.interfaces.entities.IDiceAction;

/**
 *
 * @author species8472
 */
public interface IDiceRollManager {

    /**
     * Rolling the dice.
     *
     * @param diceAction that contains information about the roll.
     * @return the summation of all roll results.
     */
    Long rollDice(IDiceAction diceAction);
}
