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

    /**
     * Calculates the modified value for the armor check penalty of a given
     * armor in the context of a dnd character.
     *
     * @param armor the amor in question.
     * @param dndcharacter the context.
     * @return the modified value.
     */
    BonusValue getArmorCheckPenalty(Armor armor, DndCharacter dndcharacter);

    /**
     * Calculates the modified value for the maximum dexterity bonus of a given
     * armor in the context of a dnd character.
     *
     * @param armor the amor in question.
     * @param dndCharacter the context.
     * @return the modified value.
     */
    BonusValue getMaxDexBonus(Armor armor, DndCharacter dndCharacter);

    /**
     * Calculates the modified value for the minimum armor check penalty of a
     * given armor collection in the context of a dnd character.
     *
     * @param armors the amor collection in question.
     * @param dndCharacter the context.
     * @return the modified value.
     */
    BonusValue getMinimumArmorCheckPenalty(Armors armors,
            DndCharacter dndCharacter);

    /**
     * Calculates the modified value for the minimum max dexterity bonus of a
     * given armor collection in the context of a dnd character.
     *
     * @param armors the amor collection in question.
     * @param dndCharacter the context.
     * @return the modified value.
     */
    BonusValue getMinimumMaxDexBonus(Armors armors, DndCharacter dndCharacter);
}
