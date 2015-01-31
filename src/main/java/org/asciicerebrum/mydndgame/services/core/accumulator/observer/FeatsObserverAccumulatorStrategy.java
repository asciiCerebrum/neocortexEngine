package org.asciicerebrum.mydndgame.services.core.accumulator.observer;

import java.util.Iterator;
import org.asciicerebrum.mydndgame.domain.core.attribution.Feats;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSource;
import org.asciicerebrum.mydndgame.domain.gameentities.FeatType;
import org.asciicerebrum.mydndgame.domain.gameentities.UniqueEntity;
import org.asciicerebrum.mydndgame.observers.Observers;

/**
 *
 * @author species8472
 */
public class FeatsObserverAccumulatorStrategy
        implements ObserverAccumulatorStrategy {

    private ObserverAccumulatorStrategy featStrategy;

    @Override
    public final Observers getObservers(final ObserverSource observerSource,
            final UniqueEntity targetEntity) {
        final Observers observers = new Observers();
        final Feats feats = (Feats) observerSource;
        final Iterator<FeatType> featIterator = feats.iterator();

        while (featIterator.hasNext()) {
            final FeatType featType = featIterator.next();

            observers.add(this.featStrategy.getObservers(
                    featType, targetEntity));
        }

        return observers;
    }

    @Override
    public final boolean isApplicable(final ObserverSource observerSource) {
        return observerSource instanceof Feats;
    }

    /**
     * @param featStrategyInput the featStrategy to set
     */
    public final void setFeatStrategy(
            final ObserverAccumulatorStrategy featStrategyInput) {
        this.featStrategy = featStrategyInput;
    }

}
