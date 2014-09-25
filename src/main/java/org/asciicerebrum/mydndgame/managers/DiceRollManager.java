package org.asciicerebrum.mydndgame.managers;

import java.security.SecureRandom;
import org.asciicerebrum.mydndgame.interfaces.entities.IDice;

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
     * @param diceType the type of dice. E.g. D20, D10, D6, etc.
     * @param diceNumber the number of dice to roll.
     * @return the summation of all roll results.
     */
    public final Long rollDice(final IDice diceType, final Long diceNumber) {

        Long randomValue = 0L;

        for (long i = 0; i < diceNumber; i++) {

            randomValue += 1L + (long) (this.random.nextDouble()
                    * ((double) diceType.getSides() - 1.0D));

        }

        return randomValue;
    }

}
