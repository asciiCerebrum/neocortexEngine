package org.asciicerebrum.mydndgame.facades.game;

import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.game.Armor;
import org.asciicerebrum.mydndgame.domain.game.Armors;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;

/**
 *
 * @author species8472
 */
public interface ArmorServiceFacade extends InventoryItemServiceFacade {

    BonusValue getArmorCheckPenalty(Armor armor, DndCharacter dndcharacter);

    BonusValue getMaxDexBonus(Armor armor, DndCharacter dndCharacter);

    BonusValue getMinimumArmorCheckPenalty(Armors armors,
            DndCharacter dndCharacter);

    BonusValue getMinimumMaxDexBonus(Armors armors, DndCharacter dndCharacter);
}
