package org.asciicerebrum.mydndgame.facades.gameentities;

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

    Cost getCost(Cost baseCost, InventoryItem inventoryItem,
            DndCharacter dndCharacter);

    BodySlotTypes getDesignatedBodySlotTypes(BodySlotTypes baseBodySlotTypes,
            InventoryItem inventoryItem, DndCharacter dndCharacter);

    SizeCategory getSize(SizeCategory baseValue,
            InventoryItem inventoryItem, DndCharacter dndCharacter);

}
