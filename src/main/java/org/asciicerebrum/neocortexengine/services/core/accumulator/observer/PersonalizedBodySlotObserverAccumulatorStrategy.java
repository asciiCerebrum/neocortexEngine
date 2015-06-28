package org.asciicerebrum.neocortexengine.services.core.accumulator.observer;

import org.asciicerebrum.neocortexengine.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.ruleentities.composition.PersonalizedBodySlot;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.Observers;
import org.asciicerebrum.neocortexengine.services.context.EntityPoolService;

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

    /**
     * The entity pool service.
     */
    private EntityPoolService entityPoolService;

    @Override
    public final Observers getObservers(final ObserverSource observerSource,
            final UniqueEntity targetEntity) {
        final Observers observers = new Observers();
        if (!(observerSource instanceof PersonalizedBodySlot)) {
            return observers;
        }
        final PersonalizedBodySlot bodySlot
                = (PersonalizedBodySlot) observerSource;

        final UniqueEntity bodySlotItem = this.getEntityPoolService()
                .getEntityById(bodySlot.getItemId());

        observers.add(this.getItemStrategy().getObservers(
                (ObserverSource) bodySlotItem, targetEntity));

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

    /**
     * @return the itemStrategy
     */
    public final ObserverAccumulatorStrategy getItemStrategy() {
        return itemStrategy;
    }

    /**
     * @return the entityPoolService
     */
    public final EntityPoolService getEntityPoolService() {
        return entityPoolService;
    }

    /**
     * @param entityPoolServiceInput the entityPoolService to set
     */
    public final void setEntityPoolService(
            final EntityPoolService entityPoolServiceInput) {
        this.entityPoolService = entityPoolServiceInput;
    }

}
