package org.asciicerebrum.mydndgame.services.core.accumulator.observer;

import java.util.Iterator;
import org.asciicerebrum.mydndgame.domain.mechanics.ObserverSource;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.rules.composition.PersonalizedBodySlot;
import org.asciicerebrum.mydndgame.domain.rules.composition.PersonalizedBodySlots;
import org.asciicerebrum.mydndgame.domain.mechanics.interfaces.Observers;

/**
 *
 * @author species8472
 */
public class PersonalizedBodySlotsObserverAccumulatorStrategy
        implements ObserverAccumulatorStrategy {

    /**
     * Accumulator strategy for the different slots.
     */
    private ObserverAccumulatorStrategy personalizedBodySlotsStrategy;

    @Override
    public final Observers getObservers(final ObserverSource observerSource,
            final UniqueEntity targetEntity) {
        final Observers observers = new Observers();
        final PersonalizedBodySlots bodySlots
                = (PersonalizedBodySlots) observerSource;
        final Iterator<PersonalizedBodySlot> slotIterator = bodySlots.iterator();

        while (slotIterator.hasNext()) {
            final PersonalizedBodySlot slot = slotIterator.next();

            observers.add(this.personalizedBodySlotsStrategy.getObservers(
                    slot, targetEntity));
        }

        return observers;
    }

    @Override
    public final boolean isApplicable(final ObserverSource observerSource) {
        return observerSource instanceof PersonalizedBodySlots;
    }

    /**
     * @param personalizedBodySlotsStrategyIn the personalizedBodySlotsStrategy
     * to set
     */
    public final void setPersonalizedBodySlotsStrategy(
            final ObserverAccumulatorStrategy personalizedBodySlotsStrategyIn) {
        this.personalizedBodySlotsStrategy = personalizedBodySlotsStrategyIn;
    }

}
