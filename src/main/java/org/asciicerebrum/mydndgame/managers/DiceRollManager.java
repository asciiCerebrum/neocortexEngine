package org.asciicerebrum.mydndgame.managers;

import java.security.SecureRandom;
import org.asciicerebrum.mydndgame.domain.core.particles.DiceRoll;
import org.asciicerebrum.mydndgame.domain.rules.entities.DiceAction;

/**
 *
 * @author species8472
 */
public class DiceRollManager {

    /**
     * Random number generator as a dice analogon.
     */
    private SecureRandom random = new SecureRandom();

    public final DiceRoll rollDice(final DiceAction diceAction) {

        long randomValue = 0L;

        for (long i = 0; i < diceAction.getDiceNumber().getValue(); i++) {

            randomValue += 1L + (long) (this.random.nextDouble()
                    * ((double) diceAction.getDiceType()
                    .getSides().getValue()));

        }

        return new DiceRoll(randomValue);
    }

    /**
     * @param randomInput the random to set
     */
    public final void setRandom(final SecureRandom randomInput) {
        this.random = randomInput;
    }

}
