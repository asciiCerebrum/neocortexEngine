package org.asciicerebrum.mydndgame.domain.game.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author species8472
 */
public class InventoryItems<T extends InventoryItem> {

    protected final List<T> elements = new ArrayList<T>();

    public final void add(final T inventoryItem) {
        this.elements.add(inventoryItem);
    }

    public final void add(final List<T> inventoryItems) {
        this.elements.addAll(inventoryItems);
    }

    public Iterator<T> iterator() {
        return this.elements.iterator();
    }

}
