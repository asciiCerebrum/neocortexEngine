package org.asciicerebrum.mydndgame.domain.factories;

import org.asciicerebrum.mydndgame.domain.game.InventoryItem;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.infrastructure.ApplicationContextProvider;

/**
 * Builder for a unique weapon from the prototype.
 *
 * @author species8472
 */
public class WeaponFactory extends InventoryItemFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    protected final InventoryItem getConcreteInventoryItem() {

        return ApplicationContextProvider
                .getApplicationContext().getBean(Weapon.class);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected final void finalizeCreation(final InventoryItem inventoryItem) {
        // so far nothing to do here.
    }
}
