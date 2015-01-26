package org.asciicerebrum.mydndgame.facades.gameentities;

import org.asciicerebrum.mydndgame.domain.core.attribution.SizeCategory;
import org.asciicerebrum.mydndgame.domain.gameentities.DndCharacter;

/**
 *
 * @author species8472
 */
public interface CharacterServiceFacade {

    SizeCategory getSize(SizeCategory baseValue, DndCharacter dndCharacter);
}
