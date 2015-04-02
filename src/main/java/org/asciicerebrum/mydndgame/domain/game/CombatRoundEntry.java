package org.asciicerebrum.mydndgame.domain.game;

import org.asciicerebrum.mydndgame.domain.core.particles.CombatRoundPosition;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;

/**
 *
 * @author species8472
 */
public class CombatRoundEntry {

    /**
     * The participating dnd character in this entry.
     */
    private UniqueId participantId;

    /**
     * The position associated with the participant.
     */
    private CombatRoundPosition combatRoundPosition;

    /**
     * @return the combatRoundPosition
     */
    public final CombatRoundPosition getCombatRoundPosition() {
        return combatRoundPosition;
    }

    /**
     * @param combatRoundPositionInput the combatRoundPosition to set
     */
    public final void setCombatRoundPosition(
            final CombatRoundPosition combatRoundPositionInput) {
        this.combatRoundPosition = combatRoundPositionInput;
    }

    /**
     * Tests if current entry has the same combat round position.
     *
     * @param crPosition the combat round position in question.
     * @return tests if equals, false otherwise.
     */
    public final boolean isCombatRoundPosition(
            final CombatRoundPosition crPosition) {
        return this.getCombatRoundPosition().equals(crPosition);
    }

    /**
     * @return the participantId
     */
    public final UniqueId getParticipantId() {
        return participantId;
    }

    /**
     * @param participantIdInput the participantId to set
     */
    public final void setParticipantId(final UniqueId participantIdInput) {
        this.participantId = participantIdInput;
    }

}
