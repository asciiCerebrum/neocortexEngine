package org.asciicerebrum.neocortexengine.services.core.accumulator.observer;

import org.asciicerebrum.neocortexengine.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.ruleentities.DiceAction;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.Observers;

/**
 *
 * @author species8472
 */
public class DiceActionObserverAccumulatorStrategy
        implements ObserverAccumulatorStrategy {

    @Override
    public final Observers getObservers(final ObserverSource observerSource,
            final UniqueEntity targetEntity) {
        if (!(observerSource instanceof DiceAction)) {
            return new Observers();
        }
        return ((DiceAction) observerSource).getObservers();
    }

    @Override
    public final boolean isApplicable(final ObserverSource observerSource) {
        return observerSource instanceof DiceAction;
    }

}
