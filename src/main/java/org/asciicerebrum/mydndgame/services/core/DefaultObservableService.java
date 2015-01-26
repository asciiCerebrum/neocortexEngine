package org.asciicerebrum.mydndgame.services.core;

import java.util.Iterator;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverHooks;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSource;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSources;
import org.asciicerebrum.mydndgame.observers.Observers;
import org.asciicerebrum.mydndgame.domain.gameentities.DndCharacter;
import org.asciicerebrum.mydndgame.domain.gameentities.UniqueEntity;
import org.asciicerebrum.mydndgame.observers.IObserver;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverHook;

/**
 *
 * @author species8472
 */
public class DefaultObservableService implements ObservableService {

    /**
     * {@inheritDoc}
     */
    @Override
    public final Object triggerObservers(final Object observerTarget,
            final Observers observers,
            final DndCharacter dndCharacter) {

        return observers.trigger(observerTarget, dndCharacter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Object triggerObservers(final Object observerTarget,
            final UniqueEntity targetEntity,
            final ObserverSources observerSources,
            final ObserverHooks observerHooks,
            final DndCharacter dndCharacter) {

        final Observers observers = this.accumulateObserversByHooks(
                observerSources, observerHooks, targetEntity);
        return this.triggerObservers(
                observerTarget, observers, dndCharacter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Observers accumulateObservers(
            final ObserverSources observerSources,
            final UniqueEntity targetEntity) {
        final Observers observers = new Observers();

        final Iterator<ObserverSource> iterator
                = observerSources.iterator();
        while (iterator.hasNext()) {
            final ObserverSource observerSource = iterator.next();
            observers.add(this.accumulateObservers(observerSource,
                    targetEntity));
        }

        return observers;
    }

    /**
     * {@inheritDoc} Observers come in two scopes: observers that target
     * everything (ALL) and observers that target only the unique entity that
     * they are part of (SPECIFIC). So when accumulating these observers only
     * the ALL and SPECIFIC with the given unique entity as the specificum must
     * be taken into account!!!<br />
     * To further complicate the situation, the current specificum in the
     * accumulation recursion must be tracked, as it can change within the
     * hierarchy traversal. Keep in mind that not every source of observers is a
     * specificum - only the unique entities are! (E.g. a special ability is
     * not, but it can reside in a weapon, which is a specificum!)<br />
     * The same applies for boni!!!
     */
    @Override
    public final Observers accumulateObservers(
            final ObserverSource observerSource,
            final UniqueEntity targetEntity) {

        // Determining the currently tracked unique entity. It is the given one
        // (observerTarget) as long as the observerSource itself is not one as
        // the iteration processes down the inheritance tree.
        UniqueEntity trackedEntity = targetEntity;
        if (observerSource instanceof UniqueEntity) {
            trackedEntity = (UniqueEntity) observerSource;
        }
        // When both observerTarget and trackedTarget are the same, we need
        // the observers of both scopes! (ALL and SPECIFIC) - hence no
        // filtering.
        // When they differ, only the ALL-scopes observers can be used!
        final Observers observers = new Observers();
        if (trackedEntity != targetEntity) {
            observers.add(observerSource.getObservers()
                    .filterByScope(IObserver.ObserverScope.ALL));
        } else {
            observers.add(observerSource.getObservers());
        }

        final Iterator<ObserverSource> iterator
                = observerSource.getObserverSources().iterator();
        while (iterator.hasNext()) {
            final ObserverSource subObserverSource = iterator.next();
            if (subObserverSource == null) {
                continue;
            }
            observers.add(this.accumulateObservers(subObserverSource,
                    targetEntity));
        }
        return observers;
    }

    @Override
    public final Observers accumulateObserversByHook(
            final ObserverSource observerSource,
            final ObserverHook observerHook,
            final UniqueEntity targetEntity) {
        return this.accumulateObservers(observerSource, targetEntity)
                .filterByHook(observerHook);
    }

    @Override
    public final Observers accumulateObserversByHooks(
            final ObserverSources observerSources,
            final ObserverHooks observerHooks,
            final UniqueEntity targetEntity) {
        return this.accumulateObservers(observerSources, targetEntity)
                .filterByHooks(observerHooks);
    }

}
