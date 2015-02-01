package org.asciicerebrum.mydndgame.observers;

import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverHook;
import org.asciicerebrum.mydndgame.domain.game.entities.DndCharacter;

/**
 *
 * @author species8472
 */
public interface IObserver {

    public enum ObserverScope {

        /**
         * Targets everything.
         */
        ALL,
        /**
         * Targets only the unique entity the observer is part of.
         */
        SPECIFIC;
    }

    /**
     *
     * @return the hook enum this observer is associated with.
     */
    ObserverHook getHook();

    /**
     * Sets the hook enum for the observer.
     *
     * @param hookInput the hook to set.
     */
    void setHook(ObserverHook hookInput);

    /**
     * Makes the observer fulfill its destiny.
     *
     * @param object the object to act and modify on.
     * @param dndCharacter the situation character to act upon.
     * @return the modified object.
     */
    Object trigger(Object object, DndCharacter dndCharacter);

    ObserverScope getScope();

    void setScope(ObserverScope scope);

}
