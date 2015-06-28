package org.asciicerebrum.neocortexengine.mechanics.managers;

import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusValue;
import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueIds;
import org.asciicerebrum.neocortexengine.domain.game.Campaign;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.mechanics.WorldDate;
import org.asciicerebrum.neocortexengine.domain.ruleentities.DiceAction;
import org.asciicerebrum.neocortexengine.domain.ruleentities.composition.RollResult;

/**
 *
 * @author species8472
 */
public interface RollResultManager {

    /**
     * Determines the result of a dice roll and firing related events.
     *
     * @param bonusValue the value of the roll bonus.
     * @param diceAction the dice action executed.
     * @param contextEntity the context of the action.
     * @param sourceCharacter the character throwing the dice.
     * @param targetCharacterIds the collection of characters targeted by the
     * throw.
     * @param worldDate the current date of the dice roll.
     * @param campaign the campaign the action takes place in.
     * @return the roll result.
     */
    RollResult retrieveRollResult(BonusValue bonusValue, DiceAction diceAction,
            UniqueEntity contextEntity, DndCharacter sourceCharacter,
            UniqueIds targetCharacterIds, WorldDate worldDate,
            Campaign campaign);

}
