package org.asciicerebrum.mydndgame.domain.core.particles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author species8472
 */
public class UniqueIds {

    /**
     * The central list of unique ids.
     */
    private final List<UniqueId> elements = new ArrayList<UniqueId>();

    /**
     * Default constructor for empty collection of unique ids.
     */
    public UniqueIds() {

    }

    /**
     * Constructs a unique id collection out of a given list.
     *
     * @param uniqueIds the list to construct the collection from.
     */
    public UniqueIds(final UniqueId... uniqueIds) {
        this.elements.addAll(Arrays.asList(uniqueIds));
    }

    /**
     * Adds a further element to the collection.
     *
     * @param uniqueId the element to add.
     */
    public final void add(final UniqueId uniqueId) {
        this.elements.add(uniqueId);
    }

    /**
     * Merges a given collection into this collection.
     *
     * @param uniqueIds the collection to merge.
     */
    public final void add(final UniqueIds uniqueIds) {
        this.elements.addAll(uniqueIds.elements);
    }

    /**
     * Tests if a given element is part of the collection.
     *
     * @param uniqueId the element to test.
     * @return true if part of the collection, false otherwise.
     */
    public final boolean contains(final UniqueId uniqueId) {
        return this.elements.contains(uniqueId);
    }

    /**
     * @return the iterator over the collection of unique ids.
     */
    public final Iterator<UniqueId> iterator() {
        return this.elements.iterator();
    }

    /**
     * Tests if there are at least 2 elements in the collection.
     *
     * @return true if size limit is met, false otherwise.
     */
    public final boolean hasMultipleEntries() {
        return this.elements.size() > 1;
    }

}
