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
import org.asciicerebrum.mydndgame.interfaces.entities.IWorldDate;

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
     * The current date of this particular combat round encounter.
     */
    private IWorldDate currentDate;

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

        return new TreeSet<String>(this.participantPositionMap.values())
                .descendingSet();
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
    public final IWorldDate getCurrentDate() {
        if (this.currentDate == null) {
            this.moveToNextPosition();
        }
        return this.currentDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setCurrentDate(final IWorldDate currentDateInput) {
        this.currentDate = currentDateInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final ICharacter getCurrentParticipant() {
        if (this.currentDate == null) {
            throw new IllegalStateException("Current date of combat round "
                    + "not yet set up.");
        }
        Set<ICharacter> currentParticipants
                = this.getParticipantsForPosition(
                        this.currentDate.getCombatRoundPosition());
        if (currentParticipants.size() > 1) {
            throw new IllegalStateException("Current combat round seems to"
                    + " have more than one current participants.");
        }
        return currentParticipants.iterator().next();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void moveToNextPosition() {

        if (this.currentDate == null) {
            this.currentDate = new WorldDate(0L,
                    this.getOrderedPositions().iterator().next());
            return;
        }

        List<String> orderedPositions
                = new ArrayList<String>(this.getOrderedPositions());
        int curIdx = orderedPositions.indexOf(
                this.currentDate.getCombatRoundPosition());

        // at the end we have to start at the beginning again.
        // if this occurs, increment the round number
        if (curIdx == orderedPositions.size() - 1) {
            this.currentDate.setCombatRoundNumber(
                    this.currentDate.getCombatRoundNumber() + 1L);
            this.currentDate.setCombatRoundPosition(orderedPositions.get(0));
            return;
        }
        this.currentDate.setCombatRoundPosition(
                orderedPositions.get(curIdx + 1));
    }

}
