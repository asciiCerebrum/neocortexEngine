package org.asciicerebrum.mydndgame.domain.game;

/**
 *
 * @author species8472
 */
public class Campaign {

    /**
     * The most current combat round currently happening in the campaign.
     */
    private CombatRound combatRound;

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

}
