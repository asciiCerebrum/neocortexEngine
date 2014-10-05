package org.asciicerebrum.mydndgame;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author species8472
 */
public class CombatRoundSetup {

    /**
     * Map of participants and their position in the combat round. The
     * character's spring bean identifiert is the key, the position is the
     * value.
     */
    private Map<String, String> participantIdPositionMap
            = new HashMap<String, String>();

    /**
     * The currently active position in the combat round.
     */
    private String currentPosition;

    /**
     * @return the participantIdPositionMap
     */
    public final Map<String, String> getParticipantIdPositionMap() {
        return participantIdPositionMap;
    }

    /**
     * @param participantIdPositionMapInput the participantIdPositionMap to set
     */
    public final void setParticipantIdPositionMap(
            final Map<String, String> participantIdPositionMapInput) {
        this.participantIdPositionMap = participantIdPositionMapInput;
    }

    /**
     * @return the currentPosition
     */
    public final String getCurrentPosition() {
        return currentPosition;
    }

    /**
     * @param currentPositionInput the currentPosition to set
     */
    public final void setCurrentPosition(final String currentPositionInput) {
        this.currentPosition = currentPositionInput;
    }

}
