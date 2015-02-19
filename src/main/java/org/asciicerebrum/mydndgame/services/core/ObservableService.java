package org.asciicerebrum.mydndgame.services.core;

import org.asciicerebrum.mydndgame.domain.mechanics.ObserverHooks;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSources;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.Observers;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.mechanics.ObserverHook;

/**
 *
 * @author species8472
 */
public interface ObservableService {

    /**
     * Runs the list of overservers associated with the given hook.
     *
     * @param observerTarget the object to modify and return again.
     * @param targetEntity the context item.
     * @param observers the list of observers to trigger.
     * @param dndCharacter the context needed to make the correct modifications.
     * @return the modified object.
     */
    Object triggerObservers(Object observerTarget, UniqueEntity targetEntity,
            Observers observers, DndCharacter dndCharacter);

    /**
     * Runs the list of observers indirectly given by the observer sources.
     *
     * @param observerTarget the object being modified by the observers.
     * @param targetEntity the entity the target is changed for.
     * @param observerSources the collection of observer sources.
     * @param observerHooks the collection of hooks for the observers to take
     * into account.
     * @param dndCharacter the character giving the context.
     * @return the modified object.
     */
    Object triggerObservers(Object observerTarget, UniqueEntity targetEntity,
            ObserverSources observerSources, ObserverHooks observerHooks,
            DndCharacter dndCharacter);

    /**
     * Retrieves the observers from the tree of observer sources.
     *
     * @param observerSources the starting points of the accumulation process.
     * @param targetEntity the entity the observers are needed for.
     * @return the collection of collected observers.
     */
    Observers accumulateObservers(ObserverSources observerSources,
            UniqueEntity targetEntity);

    /**
     * Retrieves the observers from the tree of observer sources.
     *
     * @param observerSource the starting point of the accumulation process.
     * @param targetEntity the entity the observers are needed for.
     * @return the collection of collected observers.
     */
    Observers accumulateObservers(ObserverSource observerSource,
            UniqueEntity targetEntity);

    /**
     * Retrieves the observers from the tree of observer sources and filters
     * them by the given hook.
     *
     * @param observerSource the starting point of the accumulation process.
     * @param observerHook the filter for the correct observers.
     * @param targetEntity the entity the observers are needed for.
     * @return the collection of collected observers.
     */
    Observers accumulateObserversByHook(ObserverSource observerSource,
            ObserverHook observerHook, UniqueEntity targetEntity);

    /**
     * Retrieves the observers from the tree of observer sources and filters
     * them by the given hooks.
     *
     * @param observerSources the starting points of the accumulation process.
     * @param observerHooks the filters for the correct observers.
     * @param targetEntity the entity the observers are needed for.
     * @return the collection of collected observers.
     */
    Observers accumulateObserversByHooks(ObserverSources observerSources,
            ObserverHooks observerHooks, UniqueEntity targetEntity);
}
