package org.asciicerebrum.neocortexengine.domain.mechanics.observer;

import org.asciicerebrum.neocortexengine.domain.core.ICharacter;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;

/**
 *
 * @author species8472
 */
public interface ObserverTriggerStrategy {

    /**
     * Activates the changes an observer makes.
     *
     * @param object the object to make changes upon.
     * @param dndCharacter the context of the changes.
     * @param contextItem the item the changes are made for.
     * @return the changed object.
     */
    Object trigger(Object object, ICharacter dndCharacter,
            UniqueEntity contextItem);

}
