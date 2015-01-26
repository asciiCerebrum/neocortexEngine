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
