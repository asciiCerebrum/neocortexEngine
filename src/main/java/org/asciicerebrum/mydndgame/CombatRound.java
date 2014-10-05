package org.asciicerebrum.mydndgame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import org.apache.commons.lang.StringUtils;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.ICombatRound;

/**
 *
 * @author species8472
 */
public class CombatRound implements ICombatRound {

    /**
     * Map of participants and their position in the combat round as a string
     * identifiert. The order of the participants corresponds to the
     * alpha-numerical order of the string values of this map.
     */
    private final Map<ICharacter, String> participantPositionMap
            = new HashMap<ICharacter, String>();

    /**
     * The currently active position in the combat round.
     */
    private String currentPosition;

    /**
     * {@inheritDoc}
     */
    @Override
    public final Set<ICharacter> getParticipants() {
        return this.participantPositionMap.keySet();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Set<String> getOrderedPositions() {
        return new TreeSet<String>(this.participantPositionMap.values());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void addParticipant(
            final ICharacter participant, final String roundPosition) {
        this.participantPositionMap.put(participant, roundPosition);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String getPositionForParticipant(
            final ICharacter participant) {
        return this.participantPositionMap.get(participant);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Set<ICharacter> getParticipantsForPosition(
            final String roundPosition) {
        Set<ICharacter> participants = new HashSet<ICharacter>();

        if (StringUtils.isBlank(roundPosition)) {
            return participants;
        }

        for (Entry<ICharacter, String> entry
                : this.participantPositionMap.entrySet()) {

            if (StringUtils.isNotBlank(entry.getValue())
                    && entry.getValue().equals(roundPosition)) {
                participants.add(entry.getKey());
            }
        }

        return participants;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String getCurrentPosition() {
        if (StringUtils.isBlank(this.currentPosition)) {
            return this.moveToNextPosition();
        }
        return currentPosition;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setCurrentPosition(final String currentPositionInput) {
        this.currentPosition = currentPositionInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String moveToNextPosition() {

        if (StringUtils.isBlank(this.currentPosition)) {
            this.currentPosition = this.getOrderedPositions().iterator().next();
            return this.currentPosition;
        }

        List<String> orderedPositions
                = new ArrayList<String>(this.getOrderedPositions());
        int curIdx = orderedPositions.indexOf(this.currentPosition);

        // at the end we have to start at the beginning again.
        //TODO if this occurs, increment the round number
        if (curIdx == orderedPositions.size() - 1) {
            this.currentPosition = null;
            return this.moveToNextPosition();
        }
        return orderedPositions.get(curIdx + 1);
    }

}
