package org.asciicerebrum.mydndgame.domain.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author species8472
 * @param <T> the concrete class of the unique entity.
 */
public abstract class UniqueEntities<T extends UniqueEntity> {

    protected final List<T> elements = new ArrayList<T>();

    public final void add(final T uniqueEntityInput) {
        this.elements.add(uniqueEntityInput);
    }

    public final void add(final UniqueEntities<T> uniqueEntitiesInput) {
        this.elements.addAll(uniqueEntitiesInput.elements);
    }

    public final Iterator<T> iterator() {
        return this.elements.iterator();
    }

}
