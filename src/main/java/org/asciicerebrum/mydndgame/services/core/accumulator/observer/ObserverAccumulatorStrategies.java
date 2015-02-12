package org.asciicerebrum.mydndgame.services.core.accumulator.observer;

import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;

/**
 *
 * @author t.raab
 */
public interface ObserverAccumulatorStrategies {

    /**
     * Retrieves the fitting accumulator strategy for observers by a given
     * observer source.
     *
     * @param observerSource the source the accumulator is needed for.
     * @return the accumulator for the given source.
     */
    ObserverAccumulatorStrategy findForSource(ObserverSource observerSource);

}
