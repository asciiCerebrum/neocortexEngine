package org.asciicerebrum.neocortexengine.mechanics.managers;

import org.asciicerebrum.neocortexengine.domain.core.particles.DiceRoll;
import org.asciicerebrum.neocortexengine.domain.ruleentities.DiceAction;

/**
 *
 * @author t.raab
 */
public interface DiceRollManager {

    /**
     * Rolls the dice given in the dice action and returns the result as a dice
     * roll object.
     *
     * @param diceAction the dice action containing information how to roll
     * which dice.
     * @return the roll result.
     */
    DiceRoll rollDice(DiceAction diceAction);
}
