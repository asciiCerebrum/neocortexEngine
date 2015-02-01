package org.asciicerebrum.mydndgame.services.core.accumulator.observer;

import org.asciicerebrum.mydndgame.domain.rules.entities.Ability;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSource;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.mechanics.Observers;

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
