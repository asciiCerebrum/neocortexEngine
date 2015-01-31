package org.asciicerebrum.mydndgame.services.core.accumulator.observer;

import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSource;
import org.asciicerebrum.mydndgame.domain.gameentities.UniqueEntity;
import org.asciicerebrum.mydndgame.observers.Observers;

/**
 *
 * @author species8472
 */
public interface ObserverAccumulatorStrategy {

    public Observers getObservers(ObserverSource observerSource,
            UniqueEntity targetEntity);

    boolean isApplicable(ObserverSource observerSource);

}
