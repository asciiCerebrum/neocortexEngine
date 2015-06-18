package org.asciicerebrum.mydndgame.domain.game;

import org.asciicerebrum.mydndgame.domain.events.RollHistory;
import org.asciicerebrum.mydndgame.domain.events.RollHistoryEntry;

/**
 *
 * @author species8472
 */
public class Campaign {

    /**
     * The most current combat round encounter currently happening in the
     * campaign.
     */
    private CombatRound combatRound;

    /**
     * The roll history. Contains all dice rolls ever made in the campaign.
     */
    private RollHistory rollHistory = new RollHistory();

    /**
     * @return the combatRound
     */
    public final CombatRound getCombatRound() {
        return combatRound;
    }

    /**
     * @param combatRoundInput the combatRound to set
     */
    public final void setCombatRound(final CombatRound combatRoundInput) {
        this.combatRound = combatRoundInput;
    }

    /**
     * @return the rollHistory
     */
    public final RollHistory getRollHistory() {
        return rollHistory;
    }

    /**
     * @param rollHistoryInput the rollHistory to set
     */
    public final void setRollHistory(final RollHistory rollHistoryInput) {
        this.rollHistory = rollHistoryInput;
    }

    /**
     * Adds a single roll history entry to the collection.
     *
     * @param entry the roll history entry to add.
     */
    public final void addRollHistoryEntry(final RollHistoryEntry entry) {
        this.rollHistory.add(entry);
    }

}
