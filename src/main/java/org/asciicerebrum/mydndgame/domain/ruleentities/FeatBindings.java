package org.asciicerebrum.mydndgame.domain.ruleentities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author species8472
 */
public class FeatBindings {

    /**
     * Central collection of feat bindings.
     */
    private final List<FeatBinding> elements = new ArrayList<FeatBinding>();

    /**
     * Adds a further feat binding to the collection.
     *
     * @param featBinding the feat binding to add.
     */
    public final void add(final FeatBinding featBinding) {
        this.elements.add(featBinding);
    }

    /**
     * Tests if a given feat binding is part of the collection.
     *
     * @param featBinding the feat binding to check.
     * @return true if part of the list, false otherwise.
     */
    public final boolean contains(final FeatBinding featBinding) {
        return this.elements.contains(featBinding);
    }

    /**
     * Iterator over the collection of feat bindings.
     *
     * @return the iterator.
     */
    public final Iterator<FeatBinding> iterator() {
        return this.elements.iterator();
    }

}
