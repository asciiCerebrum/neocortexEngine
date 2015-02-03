package org.asciicerebrum.mydndgame.services.core.accumulator.observer;

import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.mydndgame.domain.rules.Race;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.Observers;

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
        final Race race = (Race) observerSource;

        observers.add(this.sizeCategoryStrategy.getObservers(
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

}