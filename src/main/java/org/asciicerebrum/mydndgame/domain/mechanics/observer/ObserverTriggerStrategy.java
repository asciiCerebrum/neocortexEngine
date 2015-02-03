package org.asciicerebrum.mydndgame.domain.mechanics.observer;

import org.asciicerebrum.mydndgame.domain.core.ICharacter;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;

/**
 *
 * @author species8472
 */
public interface ObserverTriggerStrategy {

    Object trigger(Object object, ICharacter dndCharacter,
            UniqueEntity contextItem);

}
