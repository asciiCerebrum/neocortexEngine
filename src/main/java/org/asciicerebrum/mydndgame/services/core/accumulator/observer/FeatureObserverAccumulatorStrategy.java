package org.asciicerebrum.mydndgame.services.core.accumulator.observer;

import org.asciicerebrum.mydndgame.domain.rules.entities.Feature;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSource;
import org.asciicerebrum.mydndgame.domain.game.entities.UniqueEntity;
import org.asciicerebrum.mydndgame.observers.Observers;

/**
 *
 * @author species8472
 */
public class FeatureObserverAccumulatorStrategy
        implements ObserverAccumulatorStrategy {

    @Override
    public Observers getObservers(final ObserverSource observerSource,
            final UniqueEntity targetEntity) {
        return ((Feature) observerSource).getObservers();
    }

    @Override
    public boolean isApplicable(final ObserverSource observerSource) {
        return observerSource instanceof Feature;
    }

}
