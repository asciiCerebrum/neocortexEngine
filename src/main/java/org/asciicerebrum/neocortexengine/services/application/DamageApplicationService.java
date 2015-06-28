package org.asciicerebrum.neocortexengine.services.application;

import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.mechanics.damage.Damages;

/**
 *
 * @author species8472
 */
public interface DamageApplicationService {

    /**
     * Applies damages on the given dnd character.
     *
     * @param dndCharacter the character to apply the damage on.
     * @param damages the damages collection to apply.
     */
    void applyDamage(DndCharacter dndCharacter, Damages damages);

}
