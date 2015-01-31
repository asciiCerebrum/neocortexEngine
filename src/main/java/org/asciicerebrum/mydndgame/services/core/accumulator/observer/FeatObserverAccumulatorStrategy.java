package org.asciicerebrum.mydndgame.services.core.accumulator.observer;

import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSource;
import org.asciicerebrum.mydndgame.domain.ruleentities.Feat;
import org.asciicerebrum.mydndgame.domain.ruleentities.FeatType;
import org.asciicerebrum.mydndgame.domain.gameentities.UniqueEntity;
import org.asciicerebrum.mydndgame.observers.Observers;

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
