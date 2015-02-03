package org.asciicerebrum.mydndgame.services.core.accumulator.observer;

import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;

/**
 *
 * @author species8472
 */
public class ObserverAccumulatorStrategies {

    private final List<ObserverAccumulatorStrategy> elements
            = new ArrayList<ObserverAccumulatorStrategy>();

    public ObserverAccumulatorStrategies(
            final List<ObserverAccumulatorStrategy> elementsInput) {
        this.elements.addAll(elementsInput);
    }

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
