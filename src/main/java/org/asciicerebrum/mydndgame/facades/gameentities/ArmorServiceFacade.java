package org.asciicerebrum.mydndgame.facades.gameentities;

import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.gameentities.Armor;
import org.asciicerebrum.mydndgame.domain.gameentities.DndCharacter;

/**
 *
 * @author species8472
 */
public interface ArmorServiceFacade {

    BonusValue getArmorCheckPenalty(BonusValue baseArmorCheckPenalty,
            Armor armor, DndCharacter dndcharacter);

    BonusValue getMaxDexBonus(BonusValue baseArmorCheckPenalty,
            Armor armor, DndCharacter dndCharacter);
}
