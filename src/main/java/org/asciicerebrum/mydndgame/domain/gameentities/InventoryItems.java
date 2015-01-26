package org.asciicerebrum.mydndgame.domain.gameentities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author species8472
 */
public class InventoryItems<T extends InventoryItem> {

    protected final List<T> inventoryItems = new ArrayList<T>();

    public final void add(final T inventoryItem) {
        this.inventoryItems.add(inventoryItem);
    }

    public final void add(final List<T> inventoryItems) {
        this.inventoryItems.addAll(inventoryItems);
    }

    public Iterator<T> iterator() {
        return this.inventoryItems.iterator();
    }

}
