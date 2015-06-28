package org.asciicerebrum.neocortexengine.domain.mechanics.observer.source;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author species8472
 */
public class ObserverSources {

    /**
     * Ready to use empty collection of observer sources.
     */
    public static final ObserverSources EMPTY_OBSERVERSOURCES
            = new ObserverSources();

    /**
     * The central collection of observer sources.
     */
    private final List<ObserverSource> elements
            = new ArrayList<ObserverSource>();

    /**
     * Default constructor.
     */
    public ObserverSources() {
    }

    /**
     * Constructs the collection out of a given list.
     *
     * @param observerSources the list of observer sources.
     */
    public ObserverSources(final ObserverSource... observerSources) {
        this.elements.addAll(Arrays.asList(observerSources));
    }

    /**
     * Adds a further observer source to the collection.
     *
     * @param observerSource the observer source to add.
     */
    public final void add(final ObserverSource observerSource) {
        this.elements.add(observerSource);
    }

    /**
     * Iterator over the collection of observer sources.
     *
     * @return the iterator.
     */
    public final Iterator<ObserverSource> iterator() {
        return this.elements.iterator();
    }
}
