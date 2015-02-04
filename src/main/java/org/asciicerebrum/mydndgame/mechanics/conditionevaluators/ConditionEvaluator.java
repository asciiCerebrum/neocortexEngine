package org.asciicerebrum.mydndgame.mechanics.conditionevaluators;

import org.asciicerebrum.mydndgame.domain.core.ICharacter;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;

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
    boolean evaluate(ICharacter dndCharacter, UniqueEntity contextEntity);

}
