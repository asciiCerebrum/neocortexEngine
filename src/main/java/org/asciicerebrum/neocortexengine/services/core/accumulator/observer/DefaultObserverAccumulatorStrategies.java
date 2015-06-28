package org.asciicerebrum.neocortexengine.services.core.accumulator.observer;

import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.source.ObserverSource;

/**
 *
 * @author species8472
 */
public class DefaultObserverAccumulatorStrategies
        implements ObserverAccumulatorStrategies {

    /**
     * Central collection of observer accumulator strategies.
     */
    private final List<ObserverAccumulatorStrategy> elements
            = new ArrayList<ObserverAccumulatorStrategy>();

    /**
     * Constructs the collection from a given list.
     *
     * @param elementsInput the list of strategies.
     */
    public DefaultObserverAccumulatorStrategies(
            final List<ObserverAccumulatorStrategy> elementsInput) {
        this.elements.addAll(elementsInput);
    }

    @Override
    public final ObserverAccumulatorStrategy findForSource(
            final ObserverSource observerSource) {
        for (ObserverAccumulatorStrategy strategy : elements) {
            if (strategy.isApplicable(observerSource)) {
                return strategy;
            }
        }
        return null;
    }

}
