package org.asciicerebrum.mydndgame.facades.gameentities;

import org.asciicerebrum.mydndgame.domain.core.attribution.BodySlotType;
import org.asciicerebrum.mydndgame.domain.core.attribution.BodySlotTypes;
import org.asciicerebrum.mydndgame.domain.core.attribution.SizeCategory;
import org.asciicerebrum.mydndgame.domain.core.particles.Cost;
import org.asciicerebrum.mydndgame.domain.gameentities.DndCharacter;
import org.asciicerebrum.mydndgame.domain.gameentities.InventoryItem;

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
