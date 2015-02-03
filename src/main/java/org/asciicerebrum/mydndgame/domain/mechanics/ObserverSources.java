package org.asciicerebrum.mydndgame.domain.mechanics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author species8472
 */
public class ObserverSources {

    public static final ObserverSources EMPTY_OBSERVERSOURCES
            = new ObserverSources();

    private final List<ObserverSource> elements
            = new ArrayList<ObserverSource>();

    public ObserverSources() {
    }

    public ObserverSources(final ObserverSource... observerSources) {
        this.elements.addAll(Arrays.asList(observerSources));
    }

    public void add(final ObserverSource observerSource) {
        this.elements.add(observerSource);
    }

    /**
     * @return the bonusSources
     */
    public Iterator<ObserverSource> iterator() {
        return this.elements.iterator();
    }
}
