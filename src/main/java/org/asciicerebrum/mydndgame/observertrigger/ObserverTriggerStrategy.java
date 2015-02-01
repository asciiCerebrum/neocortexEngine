package org.asciicerebrum.mydndgame.observertrigger;

import org.asciicerebrum.mydndgame.domain.game.entities.DndCharacter;

/**
 *
 * @author species8472
 */
public interface ObserverTriggerStrategy {

    Object trigger(Object object, DndCharacter dndCharacter);

}
