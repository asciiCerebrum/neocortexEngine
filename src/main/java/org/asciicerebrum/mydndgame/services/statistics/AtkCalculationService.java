package org.asciicerebrum.mydndgame.services.statistics;

import org.asciicerebrum.mydndgame.domain.core.particles.BonusValueTuple;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.Weapon;

/**
 *
 * @author species8472
 */
public interface AtkCalculationService {

    /**
     * For statistical (and other) purposes it is necessary to calculate the
     * attack boni for a given weapon inside a given body slot with a given
     * attack mode (ranged or melee). E.g. I want to know the attack boni of my
     * magic dagger inside my backpack when thrown with my off-hand. So I can
     * print out this value on my character sheet.<br>
     * Another method can then determine the current situation - which weapon do
     * I currently have wielded, what attack mode have I chosen, etc. (by
     * querying the state registry of the character).
     *
     * @param weapon the weapon the boni are needed for.
     * @param dndCharacter the dnd character the boni are needed for.
     * @return the attack boni.
     */
    BonusValueTuple calcAtkBoni(Weapon weapon, DndCharacter dndCharacter);
}
