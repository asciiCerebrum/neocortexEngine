package org.asciicerebrum.mydndgame.services.core.accumulator.observer;

import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.mydndgame.domain.rules.Feat;
import org.asciicerebrum.mydndgame.domain.rules.FeatType;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.Observers;

/**
 *
 * @author species8472
 */
public class FeatObserverAccumulatorStrategy
        implements ObserverAccumulatorStrategy {

    /**
     * Accumulator strategy for the corresponding feat type.
     */
    private ObserverAccumulatorStrategy featTypeStrategy;

    @Override
    public final Observers getObservers(final ObserverSource observerSource,
            final UniqueEntity targetEntity) {
        final Observers observers = new Observers();
        final Feat feat = (Feat) observerSource;
        final FeatType featType = feat.getFeatType();

        observers.add(this.featTypeStrategy.getObservers(
                featType, targetEntity));

        return observers;
    }

    @Override
    public final boolean isApplicable(final ObserverSource observerSource) {
        return observerSource instanceof Feat;
    }

    /**
     * @param featTypeStrategyInput the featTypeStrategy to set
     */
    public final void setFeatTypeStrategy(
            final ObserverAccumulatorStrategy featTypeStrategyInput) {
        this.featTypeStrategy = featTypeStrategyInput;
    }

}
