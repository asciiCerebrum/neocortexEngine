package org.asciicerebrum.mydndgame.domain.mechanics.transfer;

import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.game.dndcharacter.DndCharacter;

/**
 *
 * @author species8472
 */
public interface ObserverTriggerStrategy {

    Object trigger(Object object, DndCharacter dndCharacter,
            UniqueEntity contextItem);

}
