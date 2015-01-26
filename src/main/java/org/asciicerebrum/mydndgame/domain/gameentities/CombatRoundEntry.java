package org.asciicerebrum.mydndgame.domain.gameentities;

import org.asciicerebrum.mydndgame.domain.core.particles.CombatRoundPosition;

/**
 *
 * @author species8472
 */
public class CombatRoundEntry {

    private DndCharacter participant;

    private CombatRoundPosition combatRoundPosition;

    /**
     * @return the participant
     */
    public DndCharacter getParticipant() {
        return participant;
    }

    /**
     * @param participant the participant to set
     */
    public final void setParticipant(final DndCharacter participant) {
        this.participant = participant;
    }

    /**
     * @return the combatRoundPosition
     */
    public final CombatRoundPosition getCombatRoundPosition() {
        return combatRoundPosition;
    }

    /**
     * @param combatRoundPosition the combatRoundPosition to set
     */
    public final void setCombatRoundPosition(
            final CombatRoundPosition combatRoundPosition) {
        this.combatRoundPosition = combatRoundPosition;
    }

    public final boolean isCombatRoundPosition(
            final CombatRoundPosition crPosition) {
        return this.combatRoundPosition.equals(crPosition);
    }

}
