package org.asciicerebrum.mydndgame.facades.game;

import org.asciicerebrum.mydndgame.domain.ruleentities.SizeCategory;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;

/**
 *
 * @author species8472
 */
public interface CharacterServiceFacade {

    /**
     * Calculates the modified size value of a character.
     *
     * @param dndCharacter the character in question.
     * @return the modified value.
     */
    SizeCategory getSize(DndCharacter dndCharacter);
}
