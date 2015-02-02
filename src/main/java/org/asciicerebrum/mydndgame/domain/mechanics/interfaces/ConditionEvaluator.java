package org.asciicerebrum.mydndgame.domain.mechanics.interfaces;

import org.asciicerebrum.mydndgame.domain.mechanics.entities.Bonus;
import org.asciicerebrum.mydndgame.domain.mechanics.entities.Observer;
import org.asciicerebrum.mydndgame.domain.game.entities.DndCharacter;

/**
 *
 * @author species8472
 */
public interface ConditionEvaluator {

    /**
     * Checks if the condition is met.
     *
     * @param dndCharacter the affected character.
     * @param referenceObserver the observer calling this evaluator.
     * @return the status of the condition.
     */
    boolean evaluate(DndCharacter dndCharacter,
            Observer referenceObserver);

    /**
     * Checks if the condition is met.
     *
     * @param dndCharacter the affected character.
     * @param referenceBonus the bonus calling this evaluator.
     * @return the status of the condition.
     */
    boolean evaluate(DndCharacter dndCharacter, Bonus referenceBonus);

}
