package org.asciicerebrum.mydndgame.mechanics.managers;

import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueIds;
import org.asciicerebrum.mydndgame.domain.game.Campaign;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.mechanics.WorldDate;
import org.asciicerebrum.mydndgame.domain.ruleentities.DiceAction;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.RollResult;

/**
 *
 * @author species8472
 */
public interface RollResultManager {

    RollResult retrieveRollResult(BonusValue bonusValue, DiceAction diceAction,
            UniqueEntity contextEntity, DndCharacter sourceCharacter,
            UniqueIds targetCharacterIds, WorldDate worldDate,
            Campaign campaign);

}
