package org.asciicerebrum.mydndgame.facades.game;

import org.asciicerebrum.mydndgame.domain.rules.SizeCategory;
import org.asciicerebrum.mydndgame.domain.game.dndcharacter.DndCharacter;

/**
 *
 * @author species8472
 */
public interface CharacterServiceFacade {

    SizeCategory getSize(DndCharacter dndCharacter);
}
