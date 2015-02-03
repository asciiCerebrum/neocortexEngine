package org.asciicerebrum.mydndgame.services.core.accumulator.observer;

import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.rules.composition.PersonalizedBodySlot;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.Observers;

/**
 *
 * @author species8472
 */
public class PersonalizedBodySlotObserverAccumulatorStrategy
        implements ObserverAccumulatorStrategy {

    /**
     * Accumulator strategy for the item inside the body slot.
     */
    private ObserverAccumulatorStrategy itemStrategy;

    @Override
    public final Observers getObservers(final ObserverSource observerSource,
            final UniqueEntity targetEntity) {
        final Observers observers = new Observers();
        final PersonalizedBodySlot bodySlot
                = (PersonalizedBodySlot) observerSource;

        if (bodySlot.getItem() instanceof ObserverSource) {
            observers.add(this.itemStrategy.getObservers(
                    (ObserverSource) bodySlot.getItem(), targetEntity));
        }

        return observers;
    }

    @Override
    public final boolean isApplicable(final ObserverSource observerSource) {
        return observerSource instanceof PersonalizedBodySlot;
    }

    /**
     * @param itemStrategyInput the itemStrategy to set
     */
    public final void setItemStrategy(
            final ObserverAccumulatorStrategy itemStrategyInput) {
        this.itemStrategy = itemStrategyInput;
    }

}
