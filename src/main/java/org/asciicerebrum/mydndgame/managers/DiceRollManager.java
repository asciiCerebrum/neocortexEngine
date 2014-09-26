package org.asciicerebrum.mydndgame.managers;

import java.security.SecureRandom;
import org.asciicerebrum.mydndgame.interfaces.entities.IDiceAction;

/**
 *
 * @author species8472
 */
public class DiceRollManager {

    /**
     * Random number generator as a dice analogon.
     */
    private final SecureRandom random = new SecureRandom();

    /**
     * Rolling the dice.
     *
     * @param diceAction that contains information about the roll.
     * @return the summation of all roll results.
     */
    public final Long rollDice(final IDiceAction diceAction) {

        Long randomValue = 0L;

        for (long i = 0; i < diceAction.getDiceNumber(); i++) {

            randomValue += 1L + (long) (this.random.nextDouble()
                    * ((double) diceAction.getDiceType().getSides() - 1.0D));

        }

        return randomValue;
    }

}
