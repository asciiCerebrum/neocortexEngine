package org.asciicerebrum.neocortexengine.mechanics.managers;

import java.util.List;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusValue;
import org.asciicerebrum.neocortexengine.domain.core.particles.DiceRoll;
import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueIds;
import org.asciicerebrum.neocortexengine.domain.game.Campaign;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.mechanics.WorldDate;
import org.asciicerebrum.neocortexengine.domain.ruleentities.DiceAction;
import org.asciicerebrum.neocortexengine.domain.events.RollHistoryEntry;
import org.asciicerebrum.neocortexengine.domain.ruleentities.composition.RollResult;
import org.asciicerebrum.neocortexengine.mechanics.eventlisteners.RollHistoryListener;

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
