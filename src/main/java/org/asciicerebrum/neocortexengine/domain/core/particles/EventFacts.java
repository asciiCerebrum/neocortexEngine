package org.asciicerebrum.neocortexengine.domain.core.particles;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author species8472
 */
public class EventFacts {

    /**
     * Central collection of event facts.
     */
    private final List<EventFact> elements = new ArrayList<EventFact>();

    /**
     * Iterator over the collection of event facts.
     *
     * @return the iterator.
     */
    public final Iterator<EventFact> iterator() {
        return this.elements.iterator();
    }

    /**
     * Adding a single event fact to the underlying collection.
     *
     * @param eventFact the fact to add.
     */
    public final void add(final EventFact eventFact) {
        this.elements.add(eventFact);
    }

}
