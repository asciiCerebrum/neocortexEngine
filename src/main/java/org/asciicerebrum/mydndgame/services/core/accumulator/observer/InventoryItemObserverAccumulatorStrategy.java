package org.asciicerebrum.mydndgame.services.core.accumulator.observer;

import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.Observer;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.Observers;
import org.asciicerebrum.mydndgame.domain.game.InventoryItem;

/**
 *
 * @author species8472
 */
public class InventoryItemObserverAccumulatorStrategy
        implements ObserverAccumulatorStrategy {

    private ObserverAccumulatorStrategy specialAbilitiesStrategy;

    @Override
    public final Observers getObservers(final ObserverSource observerSource,
            final UniqueEntity targetEntity) {
        final Observers observers = new Observers();

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
        observers.add(this.specialAbilitiesStrategy.getObservers(
                observerSource, targetEntity));

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

}
