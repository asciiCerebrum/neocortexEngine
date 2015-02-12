package org.asciicerebrum.mydndgame.domain.factories;

import org.asciicerebrum.mydndgame.domain.game.Armor;
import org.asciicerebrum.mydndgame.domain.game.InventoryItem;

/**
 *
 * @author species8472
 */
public class ArmorFactory extends InventoryItemFactory<Armor> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected final InventoryItem getConcreteInventoryItem() {

        return this.getContext().getBean(Armor.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected final void finalizeCreation(final InventoryItem inventoryItem) {
        // so far nothing to do here.
    }

}
