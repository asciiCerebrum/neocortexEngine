package org.asciicerebrum.neocortexengine.domain.factories;

import org.asciicerebrum.neocortexengine.domain.game.Armor;
import org.asciicerebrum.neocortexengine.domain.game.InventoryItem;
import org.asciicerebrum.neocortexengine.infrastructure.ApplicationContextProvider;

/**
 *
 * @author species8472
 */
public class ArmorFactory extends InventoryItemFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    protected final InventoryItem getConcreteInventoryItem() {

        return ApplicationContextProvider
                .getApplicationContext().getBean(Armor.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected final void finalizeCreation(final InventoryItem inventoryItem) {
        // so far nothing to do here.
    }

}
