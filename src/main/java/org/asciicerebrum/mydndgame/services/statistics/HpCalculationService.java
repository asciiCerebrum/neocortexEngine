package org.asciicerebrum.mydndgame.services.statistics;

import org.asciicerebrum.mydndgame.domain.core.particles.HitPoints;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;

/**
 *
 * @author species8472
 */
public interface HpCalculationService {

    /**
     * Calculates the current active hitpoints of a given dnd character.
     *
     * @param dndCharacter the character the hp are needed for.
     * @return the hit points.
     */
    HitPoints calcCurrentHp(DndCharacter dndCharacter);

    /**
     * The maximum hit points the character can currently have. E.g. when
     * magical effects or other stuff is applied (false life, rage, effects on
     * abilities or on hp directly, etc.) which also affects the maximum hp.
     *
     * @param dndCharacter the character the max hp are needed for.
     * @return the max hit points.
     */
    HitPoints calcCurrentMaxHp(DndCharacter dndCharacter);

    /**
     * The maximum hit points the character normally has. Without any special
     * effects in action.
     *
     * @param dndCharacter the character the max hit points are needed for.
     * @return the max hit points.
     */
    HitPoints calcMaxHp(DndCharacter dndCharacter);
}
