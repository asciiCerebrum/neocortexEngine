package org.asciicerebrum.mydndgame.domain.core.mechanics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author species8472
 */
public class ObserverSources {

    public final static ObserverSources EMPTY_OBSERVERSOURCES
            = new ObserverSources();

    private final List<ObserverSource> observerSources
            = new ArrayList<ObserverSource>();

    public ObserverSources() {
    }

    public ObserverSources(final ObserverSource... observerSources) {
        this.observerSources.addAll(Arrays.asList(observerSources));
    }

    public void add(final ObserverSource observerSource) {
        this.observerSources.add(observerSource);
    }

    /**
     * @return the bonusSources
     */
    public Iterator<ObserverSource> iterator() {
        return this.observerSources.iterator();
    }
}
