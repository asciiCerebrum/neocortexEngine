package org.asciicerebrum.neocortexengine.facades.game;

import org.asciicerebrum.neocortexengine.domain.game.Armors;
import org.asciicerebrum.neocortexengine.domain.ruleentities.SizeCategory;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.game.InventoryItems;

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

    /**
     * All armor the character is currently wearing.
     *
     * @param dndCharacter the character in question.
     * @return the collection of armor.
     */
    Armors getArmorWorn(DndCharacter dndCharacter);

    /**
     * Retrives all unique entities in the collection of body slots.
     *
     * @param dndCharacter the character in question.
     * @return the filled collection of unique entities.
     */
    InventoryItems getItemsWorn(DndCharacter dndCharacter);
}
