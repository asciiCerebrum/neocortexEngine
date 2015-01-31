package org.asciicerebrum.mydndgame.services.core;

import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverHooks;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSource;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSources;
import org.asciicerebrum.mydndgame.observers.Observers;
import org.asciicerebrum.mydndgame.domain.game.entities.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.entities.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverHook;

/**
 *
 * @author species8472
 */
public interface ObservableService {

    /**
     * Runs the list of overservers associated with the given hook.
     *
     * @param observerTarget the object to modify and return again.
     * @param observers the list of observers to trigger.
     * @param dndCharacter the context needed to make the correct modifications.
     * @return the modified object.
     */
    Object triggerObservers(Object observerTarget, Observers observers,
            DndCharacter dndCharacter);

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
