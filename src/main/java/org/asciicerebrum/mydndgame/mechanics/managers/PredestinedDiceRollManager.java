package org.asciicerebrum.mydndgame.mechanics.managers;

import java.util.Stack;
import org.asciicerebrum.mydndgame.domain.core.particles.DiceRoll;
import org.asciicerebrum.mydndgame.domain.ruleentities.DiceAction;

/**
 *
 * @author species8472
 */
public class PredestinedDiceRollManager implements DiceRollManager {

    /**
     * Static stack that can be manipulated from the outside. This is useful for
     * testing purposes.
     */
    public static final Stack<Long> RESULT_STACK = new Stack<Long>();

    @Override
    public final DiceRoll rollDice(final DiceAction diceAction) {
        return new DiceRoll(RESULT_STACK.pop());
    }

}
