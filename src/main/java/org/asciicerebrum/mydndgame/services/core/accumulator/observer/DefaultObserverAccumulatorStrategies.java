package org.asciicerebrum.mydndgame.services.core.accumulator.observer;

import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;

/**
 *
 * @author species8472
 */
public class DefaultObserverAccumulatorStrategies
        implements ObserverAccumulatorStrategies {

    private final List<ObserverAccumulatorStrategy> elements
            = new ArrayList<ObserverAccumulatorStrategy>();

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
