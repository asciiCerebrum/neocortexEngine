package org.asciicerebrum.mydndgame.services.core.accumulator.observer;

import org.asciicerebrum.mydndgame.domain.core.attribution.Ability;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSource;
import org.asciicerebrum.mydndgame.domain.gameentities.UniqueEntity;
import org.asciicerebrum.mydndgame.observers.Observers;

/**
 *
 * @author species8472
 */
public class AbilityObserverAccumulatorStrategy
        implements ObserverAccumulatorStrategy {

    @Override
    public final Observers getObservers(final ObserverSource observerSource,
            final UniqueEntity targetEntity) {
        return Observers.EMPTY_OBSERVERS;
    }

    @Override
    public final boolean isApplicable(final ObserverSource observerSource) {
        return observerSource instanceof Ability;
    }

}
