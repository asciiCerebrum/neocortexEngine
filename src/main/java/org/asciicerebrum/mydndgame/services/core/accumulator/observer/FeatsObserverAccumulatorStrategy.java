package org.asciicerebrum.mydndgame.services.core.accumulator.observer;

import java.util.Iterator;
import org.asciicerebrum.mydndgame.domain.ruleentities.Feats;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.Observers;
import org.asciicerebrum.mydndgame.domain.ruleentities.Feat;

/**
 *
 * @author species8472
 */
public class FeatsObserverAccumulatorStrategy
        implements ObserverAccumulatorStrategy {

    /**
     * The observer accumulator strategy for the feat.
     */
    private ObserverAccumulatorStrategy featStrategy;

    @Override
    public final Observers getObservers(final ObserverSource observerSource,
            final UniqueEntity targetEntity) {
        final Observers observers = new Observers();
        if (!(observerSource instanceof Feats)) {
            return observers;
        }
        final Feats feats = (Feats) observerSource;
        final Iterator<Feat> featIterator = feats.iterator();

        while (featIterator.hasNext()) {
            final Feat feat = featIterator.next();

            observers.add(this.getFeatStrategy().getObservers(
                    feat, targetEntity));
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

    /**
     * @return the featStrategy
     */
    public final ObserverAccumulatorStrategy getFeatStrategy() {
        return featStrategy;
    }

}
