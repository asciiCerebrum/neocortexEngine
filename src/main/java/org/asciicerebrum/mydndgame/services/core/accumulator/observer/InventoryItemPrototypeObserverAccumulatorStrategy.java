package org.asciicerebrum.mydndgame.services.core.accumulator.observer;

import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.Observers;
import org.asciicerebrum.mydndgame.domain.ruleentities.InventoryItemPrototype;

/**
 *
 * @author species8472
 */
public class InventoryItemPrototypeObserverAccumulatorStrategy
        implements ObserverAccumulatorStrategy {

    /**
     * The observer accumulator strategy for the special abilities.
     */
    private ObserverAccumulatorStrategy specialAbilitiesStrategy;

    @Override
    public final Observers getObservers(final ObserverSource observerSource,
            final UniqueEntity targetEntity) {
        final Observers observers = new Observers();
        if (!(observerSource instanceof InventoryItemPrototype)) {
            return observers;
        }

        final InventoryItemPrototype prototype
                = (InventoryItemPrototype) observerSource;

        observers.add(this.getSpecialAbilitiesStrategy().getObservers(
                prototype.getSpecialAbilities(), targetEntity));

        return observers;
    }

    @Override
    public final boolean isApplicable(final ObserverSource observerSource) {
        return observerSource instanceof InventoryItemPrototype;
    }

    /**
     * @return the specialAbilitiesStrategy
     */
    public final ObserverAccumulatorStrategy getSpecialAbilitiesStrategy() {
        return specialAbilitiesStrategy;
    }

    /**
     * @param specialAbilitiesStrategyInput the specialAbilitiesStrategy to set
     */
    public final void setSpecialAbilitiesStrategy(
            final ObserverAccumulatorStrategy specialAbilitiesStrategyInput) {
        this.specialAbilitiesStrategy = specialAbilitiesStrategyInput;
    }

}
