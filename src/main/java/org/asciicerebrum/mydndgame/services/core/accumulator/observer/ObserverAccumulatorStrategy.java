package org.asciicerebrum.mydndgame.services.core.accumulator.observer;

import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.Observers;

/**
 *
 * @author species8472
 */
public interface ObserverAccumulatorStrategy {

    /**
     * Retrieves the observers from the source.
     *
     * @param observerSource the source to collect the observers from.
     * @param targetEntity the entity the observers are needed for.
     * @return the collection of observers.
     */
    Observers getObservers(ObserverSource observerSource,
            UniqueEntity targetEntity);

    /**
     * Tests if this strategy is applicable on the given observer source object.
     *
     * @param observerSource the source under test.
     * @return true if this is the correct strategy for the given observer
     * source, false otherwise.
     */
    boolean isApplicable(ObserverSource observerSource);

}
