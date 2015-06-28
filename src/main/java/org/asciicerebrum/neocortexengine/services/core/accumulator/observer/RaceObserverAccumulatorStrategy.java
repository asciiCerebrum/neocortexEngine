package org.asciicerebrum.neocortexengine.services.core.accumulator.observer;

import org.asciicerebrum.neocortexengine.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.neocortexengine.domain.ruleentities.Race;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.Observers;

/**
 *
 * @author species8472
 */
public class RaceObserverAccumulatorStrategy
        implements ObserverAccumulatorStrategy {

    /**
     * Accumulator strategy for the size category.
     */
    private ObserverAccumulatorStrategy sizeCategoryStrategy;

    @Override
    public final Observers getObservers(final ObserverSource observerSource,
            final UniqueEntity targetEntity) {
        final Observers observers = new Observers();
        if (!(observerSource instanceof Race)) {
            return observers;
        }
        final Race race = (Race) observerSource;

        observers.add(this.getSizeCategoryStrategy().getObservers(
                race.getSize(), targetEntity));

        return observers;
    }

    @Override
    public final boolean isApplicable(final ObserverSource observerSource) {
        return observerSource instanceof Race;
    }

    /**
     * @param sizeCategoryStrategyInput the sizeCategoryStrategy to set
     */
    public final void setSizeCategoryStrategy(
            final ObserverAccumulatorStrategy sizeCategoryStrategyInput) {
        this.sizeCategoryStrategy = sizeCategoryStrategyInput;
    }

    /**
     * @return the sizeCategoryStrategy
     */
    public final ObserverAccumulatorStrategy getSizeCategoryStrategy() {
        return sizeCategoryStrategy;
    }

}
