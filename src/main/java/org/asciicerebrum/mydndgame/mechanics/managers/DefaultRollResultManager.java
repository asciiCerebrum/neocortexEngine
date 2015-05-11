package org.asciicerebrum.mydndgame.mechanics.managers;

import java.util.List;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.core.particles.DiceRoll;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueIds;
import org.asciicerebrum.mydndgame.domain.game.Campaign;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.mechanics.WorldDate;
import org.asciicerebrum.mydndgame.domain.ruleentities.DiceAction;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.RollHistoryEntry;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.RollResult;
import org.asciicerebrum.mydndgame.mechanics.rollhistorylisteners.RollHistoryListener;

/**
 *
 * @author species8472
 */
public class DefaultRollResultManager implements RollResultManager {

    /**
     * The dice roll manager for randomized results.
     */
    private DiceRollManager diceRollManager;

    /**
     * List of listeners for broadcasting the dice results.
     */
    private List<RollHistoryListener> rollHistoryListeners;

    @Override
    public final RollResult retrieveRollResult(final BonusValue bonusValue,
            final DiceAction diceAction, final UniqueEntity contextEntity,
            final DndCharacter sourceCharacter,
            final UniqueIds targetCharacterIds, final WorldDate worldDate,
            final Campaign campaign) {

        final DiceRoll diceRoll = this.getDiceRollManager()
                .rollDice(diceAction);

        final RollResult rollResult = new RollResult(diceRoll, bonusValue);

        final RollHistoryEntry rollHistory = new RollHistoryEntry();
        rollHistory.setBonusValue(bonusValue);
        if (contextEntity != null) {
            rollHistory.setContextEntityId(contextEntity.getUniqueId());
        }
        rollHistory.setDiceActionId(diceAction.getId());
        rollHistory.setDiceNumber(diceAction.getDiceNumber());
        rollHistory.setDiceSides(diceAction.getDiceType().getSides());
        rollHistory.setSourceDndCharacterId(sourceCharacter.getUniqueId());
        rollHistory.setTargetDndCharacterIds(targetCharacterIds);
        rollHistory.setTotalResult(rollResult.calcTotalResult());
        rollHistory.setWorldDate(worldDate);

        if (this.getRollHistoryListeners() != null) {
            for (final RollHistoryListener listener
                    : this.getRollHistoryListeners()) {
                listener.broadcast(rollHistory);
            }
        }

        //TODO put this into a separate listener: introduce a
        // rollHistoryToCampaignListener or so.
        campaign.addRollHistoryEntry(rollHistory);

        return rollResult;
    }

    /**
     * @return the diceRollManager
     */
    public final DiceRollManager getDiceRollManager() {
        return diceRollManager;
    }

    /**
     * @param diceRollManagerInput the diceRollManager to set
     */
    public final void setDiceRollManager(
            final DiceRollManager diceRollManagerInput) {
        this.diceRollManager = diceRollManagerInput;
    }

    /**
     * @return the rollHistoryListeners
     */
    public final List<RollHistoryListener> getRollHistoryListeners() {
        return rollHistoryListeners;
    }

    /**
     * @param rollHistoryListenersInput the rollHistoryListeners to set
     */
    public final void setRollHistoryListeners(
            final List<RollHistoryListener> rollHistoryListenersInput) {
        this.rollHistoryListeners = rollHistoryListenersInput;
    }

}
