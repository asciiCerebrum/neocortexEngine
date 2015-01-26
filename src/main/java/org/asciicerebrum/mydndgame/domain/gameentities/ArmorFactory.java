package org.asciicerebrum.mydndgame.domain.gameentities;

/**
 *
 * @author species8472
 */
public class ArmorFactory extends InventoryItemFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    protected InventoryItem getConcreteInventoryItem() {

        Armor concreteArmor = this.getContext().getBean(Armor.class);

        return concreteArmor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void finalizeCreation(InventoryItem inventoryItem) {
        // so far nothing to do here.
    }

}
