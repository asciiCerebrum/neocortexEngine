package org.asciicerebrum.neocortexengine.services.core.accumulator.observer;

import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.Observer;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.Observers;
import org.asciicerebrum.neocortexengine.domain.game.InventoryItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author species8472
 */
public class InventoryItemObserverAccumulatorStrategy
        implements ObserverAccumulatorStrategy {

    /**
     * The logger instance.
     */
    private static final Logger LOG = LoggerFactory.getLogger(
            InventoryItemObserverAccumulatorStrategy.class);

    /**
     * The observer accumulator strategy for the special abilities.
     */
    private ObserverAccumulatorStrategy specialAbilitiesStrategy;

    /**
     * The observer accumulator strategy for the prototype.
     */
    private ObserverAccumulatorStrategy inventoryItemPrototypeStrategy;

    /**
     * The observer accumulator strategy for the conditions.
     */
    private ObserverAccumulatorStrategy conditionsStrategy;

    @Override
    public final Observers getObservers(final ObserverSource observerSource,
            final UniqueEntity targetEntity) {
        final Observers observers = new Observers();

        if (!(observerSource instanceof InventoryItem)) {
            return observers;
        }

        final InventoryItem item = (InventoryItem) observerSource;

        LOG.debug("Retrieving observers for observer source {} and target "
                + "entity {}.",
                item.getUniqueId().getValue(),
                targetEntity.getUniqueId().getValue());

        // the inventory item is a unique entity, so it is VERY RELEVANT,
        // if the target entity is identical to this inventory item!!!
        // E.g.: In one hand you have a mwk weapon A, in the other a mwk weapon
        // B. So in order to calculate the price of weapon A, you collect
        // price-observers through the complete object graph of the character.
        // But you do not want the price-observer of weapon B to influence the
        // calculation. So these observers have to be marked with scope
        // SPECIFIC.
        // When both observerTarget and trackedTarget are the same, we need
        // the observers of both scopes! (ALL and SPECIFIC) - hence no
        // filtering.
        // When they differ, only the ALL-scopes observers must be used!
        observers.add(this.getSpecialAbilitiesStrategy().getObservers(
                item.getSpecialAbilities(), targetEntity));
        observers.add(this.getInventoryItemPrototypeStrategy().getObservers(
                item.getInventoryItemPrototype(), targetEntity));
        observers.add(this.getConditionsStrategy().getObservers(
                item.getConditions(), targetEntity));

        LOG.debug("Retrieved {} observers for the item.", observers.size());

        if (observerSource != targetEntity) {
            // Here the observers with scope SPECIFIC are filtered out, because
            // they do not aim at the given targetEntity, which would be
            // Weapon A. We must be inside the unique entity Weapon B!
            return observers.filterByScope(Observer.ObserverScope.ALL);
        }

        return observers;
    }

    @Override
    public final boolean isApplicable(final ObserverSource observerSource) {
        return observerSource instanceof InventoryItem;
    }

    /**
     * @param specialAbilitiesStrategyInput the specialAbilitiesStrategy to set
     */
    public final void setSpecialAbilitiesStrategy(
            final ObserverAccumulatorStrategy specialAbilitiesStrategyInput) {
        this.specialAbilitiesStrategy = specialAbilitiesStrategyInput;
    }

    /**
     * @return the specialAbilitiesStrategy
     */
    public final ObserverAccumulatorStrategy getSpecialAbilitiesStrategy() {
        return specialAbilitiesStrategy;
    }

    /**
     * @return the inventoryItemPrototypeStrategy
     */
    public final ObserverAccumulatorStrategy
            getInventoryItemPrototypeStrategy() {
        return inventoryItemPrototypeStrategy;
    }

    /**
     * @param invItemPrototypeStrategyInpput the inventoryItemPrototypeStrategy
     * to set
     */
    public final void setInventoryItemPrototypeStrategy(
            final ObserverAccumulatorStrategy invItemPrototypeStrategyInpput) {
        this.inventoryItemPrototypeStrategy = invItemPrototypeStrategyInpput;
    }

    /**
     * @return the conditionsStrategy
     */
    public final ObserverAccumulatorStrategy getConditionsStrategy() {
        return conditionsStrategy;
    }

    /**
     * @param conditionsStrategyInput the conditionsStrategy to set
     */
    public final void setConditionsStrategy(
            final ObserverAccumulatorStrategy conditionsStrategyInput) {
        this.conditionsStrategy = conditionsStrategyInput;
    }

}
