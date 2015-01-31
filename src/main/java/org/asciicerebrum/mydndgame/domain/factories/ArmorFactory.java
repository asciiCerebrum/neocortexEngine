package org.asciicerebrum.mydndgame.domain.factories;

import org.asciicerebrum.mydndgame.domain.game.entities.Armor;
import org.asciicerebrum.mydndgame.domain.game.entities.InventoryItem;

/**
 *
 * @author species8472
 */
public class ArmorFactory extends InventoryItemFactory<Armor> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected InventoryItem getConcreteInventoryItem() {

        return this.getContext().getBean(Armor.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void finalizeCreation(InventoryItem inventoryItem) {
        // so far nothing to do here.
    }

}
