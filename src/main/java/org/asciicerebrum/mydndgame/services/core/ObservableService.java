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

    Object triggerObservers(Object observerTarget, UniqueEntity targetEntity,
            ObserverSources observerSources, ObserverHooks observerHooks,
            DndCharacter dndCharacter);

    Observers accumulateObservers(ObserverSources observerSources,
            UniqueEntity targetEntity);

    Observers accumulateObservers(ObserverSource observerSource,
            UniqueEntity targetEntity);

    Observers accumulateObserversByHook(ObserverSource observerSource,
            ObserverHook observerHook, UniqueEntity targetEntity);

    Observers accumulateObserversByHooks(ObserverSources observerSources,
            ObserverHooks observerHooks, UniqueEntity targetEntity);
}
