package org.asciicerebrum.mydndgame.services.core.accumulator.observer;

import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSource;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.rules.entities.DiceAction;
import org.asciicerebrum.mydndgame.domain.core.mechanics.Observers;

/**
 *
 * @author species8472
 */
public class DiceActionObserverAccumulatorStrategy
        implements ObserverAccumulatorStrategy {

    @Override
    public final Observers getObservers(final ObserverSource observerSource,
            final UniqueEntity targetEntity) {
        return ((DiceAction) observerSource).getObservers();
    }

    @Override
    public final boolean isApplicable(final ObserverSource observerSource) {
        return observerSource instanceof DiceAction;
    }

}
