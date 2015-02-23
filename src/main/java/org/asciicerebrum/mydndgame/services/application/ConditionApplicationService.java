package org.asciicerebrum.mydndgame.services.application;

import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.mechanics.WorldDate;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.Conditions;

/**
 *
 * @author species8472
 */
public interface ConditionApplicationService {

    /**
     * Applies the given conditions on the dnd character.
     *
     * @param dndCharacter the character to apply the conditions on.
     * @param conditions the conditions to apply.
     */
    void applyCondition(DndCharacter dndCharacter, Conditions conditions);

    /**
     * Takes away expired conditions from a dnd character.
     *
     * @param dndCharacter the dnd character.
     * @param currentDate the world date that defines the current date.
     */
    void removeExpiredConditions(DndCharacter dndCharacter,
            WorldDate currentDate);
}
