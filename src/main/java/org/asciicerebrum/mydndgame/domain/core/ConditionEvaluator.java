package org.asciicerebrum.mydndgame.domain.core;

import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;

/**
 *
 * @author species8472
 */
public interface ConditionEvaluator {

    /**
     * Checks if the condition is met.
     *
     * @param dndCharacter the affected character.
     * @param contextEntity an object that the character used in this context.
     * E.g. the weapon that is hitting a foe (and not the 2nd weapon in the
     * other hand.
     * @return the status of the condition.
     */
    boolean evaluate(DndCharacter dndCharacter, UniqueEntity contextEntity);

}
