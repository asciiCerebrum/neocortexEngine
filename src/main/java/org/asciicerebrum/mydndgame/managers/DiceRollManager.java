package org.asciicerebrum.mydndgame.managers;

import java.security.SecureRandom;
import org.asciicerebrum.mydndgame.interfaces.entities.IDiceAction;
import org.asciicerebrum.mydndgame.interfaces.managers.IDiceRollManager;

/**
 *
 * @author species8472
 */
public class DiceRollManager implements IDiceRollManager {

    /**
     * Random number generator as a dice analogon.
     */
    private SecureRandom random = new SecureRandom();

    /**
     * {@inheritDoc}
     */
    @Override
    public final Long rollDice(final IDiceAction diceAction) {

        Long randomValue = 0L;

        for (long i = 0; i < diceAction.getDiceNumber(); i++) {

            randomValue += 1L + (long) (this.random.nextDouble()
                    * ((double) diceAction.getDiceType().getSides()));

        }

        return randomValue;
    }

    /**
     * @param randomInput the random to set
     */
    public final void setRandom(final SecureRandom randomInput) {
        this.random = randomInput;
    }

}
