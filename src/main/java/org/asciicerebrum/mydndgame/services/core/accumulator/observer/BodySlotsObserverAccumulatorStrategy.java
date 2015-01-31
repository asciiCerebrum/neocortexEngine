package org.asciicerebrum.mydndgame.services.core.accumulator.observer;

import java.util.Iterator;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSource;
import org.asciicerebrum.mydndgame.domain.gameentities.BodySlot;
import org.asciicerebrum.mydndgame.domain.gameentities.BodySlots;
import org.asciicerebrum.mydndgame.domain.gameentities.UniqueEntity;
import org.asciicerebrum.mydndgame.observers.Observers;

/**
 *
 * @author species8472
 */
public class BodySlotsObserverAccumulatorStrategy
        implements ObserverAccumulatorStrategy {

    /**
     * Accumulator strategy for the different slots.
     */
    private ObserverAccumulatorStrategy bodySlotsStrategy;

    @Override
    public final Observers getObservers(final ObserverSource observerSource,
            final UniqueEntity targetEntity) {
        final Observers observers = new Observers();
        final BodySlots bodySlots = (BodySlots) observerSource;
        final Iterator<BodySlot> slotIterator = bodySlots.iterator();

        while (slotIterator.hasNext()) {
            final BodySlot slot = slotIterator.next();

            observers.add(this.bodySlotsStrategy.getObservers(
                    slot, targetEntity));
        }

        return observers;
    }

    @Override
    public final boolean isApplicable(final ObserverSource observerSource) {
        return observerSource instanceof BodySlots;
    }

    /**
     * @param bodySlotsStrategyInput the bodySlotsStrategy to set
     */
    public final void setBodySlotsStrategy(
            final ObserverAccumulatorStrategy bodySlotsStrategyInput) {
        this.bodySlotsStrategy = bodySlotsStrategyInput;
    }

}
