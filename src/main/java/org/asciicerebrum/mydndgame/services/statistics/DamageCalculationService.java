package org.asciicerebrum.mydndgame.services.statistics;

import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.Weapon;

/**
 *
 * @author species8472
 */
public interface DamageCalculationService {

    /**
     * Calculates the damage bonus for a given weapon/character combination.
     *
     * @param weapon the weapon the damage bonus is needed for.
     * @param dndCharacter the character the damage bonus is needed for.
     * @return the damage bonus.
     */
    BonusValue calcDamageBonus(Weapon weapon, DndCharacter dndCharacter);
}
