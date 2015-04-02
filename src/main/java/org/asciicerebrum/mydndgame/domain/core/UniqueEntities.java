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

    /**
     * The single elements of the unique entities.
     */
    private List<T> elements = new ArrayList<T>();

    /**
     * Adds a single entry to the elements.
     *
     * @param uniqueEntityInput the single entry to add.
     */
    public final void add(final T uniqueEntityInput) {
        if (uniqueEntityInput != null) {
            this.getElements().add(uniqueEntityInput);
        }
    }

    /**
     * Adds multiple entries to the list of elements.
     *
     * @param uniqueEntitiesInput the container of the entries.
     */
    public final void add(final UniqueEntities<T> uniqueEntitiesInput) {
        this.getElements().addAll(uniqueEntitiesInput.getElements());
    }

    /**
     * Iterator over the container.
     *
     * @return the iterator.
     */
    public final Iterator<T> iterator() {
        return this.getElements().iterator();
    }

    /**
     * @return the elements
     */
    protected final List<T> getElements() {
        return elements;
    }

    /**
     * @param elementsInput the elements to set
     */
    protected final void setElements(final List<T> elementsInput) {
        this.elements = elementsInput;
    }

}
