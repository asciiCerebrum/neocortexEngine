package org.asciicerebrum.neocortexengine.mechanics.managers;

import java.util.ArrayDeque;
import java.util.Deque;
import org.asciicerebrum.neocortexengine.domain.core.particles.DiceRoll;
import org.asciicerebrum.neocortexengine.domain.ruleentities.DiceAction;

/**
 *
 * @author species8472
 */
public class PredestinedDiceRollManager implements DiceRollManager {

    /**
     * Static stack that can be manipulated from the outside. This is useful for
     * testing purposes.
     */
    public static final Deque<Long> RESULT_STACK = new ArrayDeque<Long>();

    @Override
    public final DiceRoll rollDice(final DiceAction diceAction) {
        return new DiceRoll(RESULT_STACK.pop());
    }

}
