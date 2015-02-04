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

    Cost getCost(InventoryItem inventoryItem, DndCharacter dndCharacter);

    BodySlotTypes getDesignatedBodySlotTypes(InventoryItem inventoryItem,
            DndCharacter dndCharacter);

    SizeCategory getSize(InventoryItem inventoryItem,
            DndCharacter dndCharacter);

    boolean isCorrectlyWielded(BodySlotType bodySlotType,
            InventoryItem inventoryItem, DndCharacter dndCharacter);

}
