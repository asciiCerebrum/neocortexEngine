package org.asciicerebrum.mydndgame.facades.game;

import org.asciicerebrum.mydndgame.domain.ruleentities.BodySlotType;
import org.asciicerebrum.mydndgame.domain.ruleentities.BodySlotTypes;
import org.asciicerebrum.mydndgame.domain.ruleentities.SizeCategory;
import org.asciicerebrum.mydndgame.domain.core.particles.Cost;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.InventoryItem;

/**
 *
 * @author species8472
 */
public interface InventoryItemServiceFacade {

    /**
     * Calculates the modified cost value of a given inventory item in the
     * context of a dnd character.
     *
     * @param inventoryItem the inventory item in question.
     * @param dndCharacter the context.
     * @return the modified value.
     */
    Cost getCost(InventoryItem inventoryItem, DndCharacter dndCharacter);

    /**
     * Calculates the modified value for the designated body slot types of a
     * given inventory item in the context of a dnd character.
     *
     * @param inventoryItem the inventory item in question.
     * @param dndCharacter the context.
     * @return the modified value.
     */
    BodySlotTypes getDesignatedBodySlotTypes(InventoryItem inventoryItem,
            DndCharacter dndCharacter);

    /**
     * Calculates the modified size value of a given inventory item in the
     * context of a dnd character.
     *
     * @param inventoryItem the inventory item in question.
     * @param dndCharacter the context.
     * @return the modified value.
     */
    SizeCategory getSize(InventoryItem inventoryItem,
            DndCharacter dndCharacter);

    /**
     * Tests if a given inventory item is correctly wielded within the given
     * body slot type in the context of a dnd character.
     *
     * @param bodySlotType the body slot type containing the item.
     * @param inventoryItem the item in question.
     * @param dndCharacter the context.
     * @return true if wielded correctyl, false otherwise.
     */
    boolean isCorrectlyWielded(BodySlotType bodySlotType,
            InventoryItem inventoryItem, DndCharacter dndCharacter);

}
