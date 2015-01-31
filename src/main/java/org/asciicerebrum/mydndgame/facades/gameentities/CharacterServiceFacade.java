package org.asciicerebrum.mydndgame.facades.gameentities;

import org.asciicerebrum.mydndgame.domain.rules.entities.SizeCategory;
import org.asciicerebrum.mydndgame.domain.game.entities.DndCharacter;

/**
 *
 * @author species8472
 */
public interface CharacterServiceFacade {

    SizeCategory getSize(DndCharacter dndCharacter);
}
