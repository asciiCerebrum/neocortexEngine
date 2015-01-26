package org.asciicerebrum.mydndgame.domain.gameentities;

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
    protected InventoryItem getConcreteInventoryItem() {

        Weapon concreteWeapon = this.getContext().getBean(Weapon.class);

        return concreteWeapon;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void finalizeCreation(InventoryItem inventoryItem) {
        // so far nothing to do here.
    }
}
