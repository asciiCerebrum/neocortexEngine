package org.asciicerebrum.neocortexengine.domain.game;

import org.asciicerebrum.neocortexengine.domain.mechanics.WorldDate;
import java.util.Iterator;
import org.asciicerebrum.neocortexengine.domain.core.particles.CombatRoundPosition;
import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueId;
import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueIds;

/**
 *
 * @author species8472
 */
public class CombatRound {

    /**
     * List of participants and their position in the combat round as a string
     * identifiert. The order of the participants corresponds to the
     * alpha-numerical order of the string values of this map.
     */
    private final CombatRoundEntries combatRoundEntries
            = new CombatRoundEntries();

    /**
     * The current date of this particular combat round encounter.
     */
    private WorldDate currentDate;

    /**
     * Retrieves the current date of the most actual move in the encounter.
     *
     * @return the date of the current move.
     */
    public final WorldDate getCurrentDate() {
        if (this.combatRoundEntries.isEmpty()) {
            // TODO introduce a global date here (year, day of year, hour of
            // day, minutes and seconds as an offset for when the combat round
            // starts. Then the null-return here can be replaced by that date!
            return null;
        }

        if (this.currentDate == null) {
            this.moveToNextPosition();
        }
        return this.currentDate;
    }

    /**
     * Retrieves the current participant of the most actual move in the
     * encounter.
     *
     * @return the participant of the current move.
     */
    public final UniqueId getCurrentParticipantId() {
        final WorldDate currDate = this.getCurrentDate();
        final UniqueIds currentParticipants
                = this.combatRoundEntries.getParticipantsForPosition(
                        currDate.getCombatRoundPosition());

        return currentParticipants.iterator().next();
    }

    /**
     * Tests if the given character is the current participant of the move.
     *
     * @param dndCharacterId the character in question.
     * @return true if it this character is on the move, false otherwise.
     */
    public final boolean isCurrentParticipant(final UniqueId dndCharacterId) {
        return dndCharacterId.equals(this.getCurrentParticipantId());
    }

    /**
     * Initializes the combat round. The current date is set along with its
     * combat round number and position.
     */
    final void initializeDate() {
        this.setCurrentDate(new WorldDate());
        final CombatRoundPosition initPosition
                = this.combatRoundEntries.getFirstRoundPosition();
        this.getCurrentDate().initializeDate(initPosition);
    }

    /**
     * Tells the combat round that this move is over and that the encounter can
     * now proceed to the next position.
     */
    public final void moveToNextPosition() {

        if (this.currentDate == null) {
            this.initializeDate();
            return;
        }

        // at the end we have to start at the beginning again.
        // if this occurs, increment the round number
        this.currentDate.setCombatRoundPosition(
                this.combatRoundEntries.getRoundPositionFollowUp(
                        this.currentDate.getCombatRoundPosition()));

        if (this.currentDate.getCombatRoundPosition().equals(
                this.combatRoundEntries.getFirstRoundPosition())) {
            this.currentDate.incrementRoundNumber();
        }
    }

    /**
     * Iterator over the participants of the encounter.
     *
     * @return the iterator.
     */
    public final Iterator<UniqueId> participantsIterator() {
        return this.combatRoundEntries.participantsIterator();
    }

    /**
     * @param currentDateInput the currentDate to set
     */
    public final void setCurrentDate(final WorldDate currentDateInput) {
        this.currentDate = currentDateInput;
    }

    /**
     * Adds another participant to the combat round, along with its combat round
     * position.
     *
     * @param participantId the dnd character to add.
     * @param roundPosition the position of the character within the combat
     * encounter round.
     */
    public final void addParticipant(final UniqueId participantId,
            final CombatRoundPosition roundPosition) {
        final CombatRoundEntry entry = new CombatRoundEntry();
        entry.setParticipantId(participantId);
        entry.setCombatRoundPosition(roundPosition);

        this.combatRoundEntries.addCombatRoundEntry(entry);
    }

    /**
     * Retrieves the position of a given dnd character participant within the
     * combat round.
     *
     * @param participantId the participant the position is required for.
     * @return its position in the combat round.
     */
    public final CombatRoundPosition getPositionForParticipant(
            final UniqueId participantId) {
        return this.combatRoundEntries.getPositionForParticipant(participantId);
    }

    /**
     * Retrieves the upcoming next participation date of a given participant.
     *
     * @param dndCharacterId the character in question.
     * @return its next participation date in the queue.
     */
    public final WorldDate getNextParticipationDate(
            final UniqueId dndCharacterId) {
        final CombatRoundEntry entry
                = this.combatRoundEntries.getEntryByParticipant(dndCharacterId);
        if (entry == null) {
            return null;
        }
        final WorldDate nextParticipationDate = new WorldDate();
        nextParticipationDate.setCombatRoundPosition(
                entry.getCombatRoundPosition());
        nextParticipationDate.setCombatRoundNumber(
                this.getCurrentDate().getCombatRoundNumber());

        // if nextParticipationDate is in the past, we have to increment the
        // round number!
        if (this.getCurrentDate().isAfter(nextParticipationDate)) {
            nextParticipationDate.incrementRoundNumber();
        }
        return nextParticipationDate;
    }

    /**
     * Retrieves the positions of the combat round in a naturally ordered way.
     *
     * @return the iterator of the ordered positions.
     */
    public final Iterator<CombatRoundPosition> getOrderedPositions() {
        return this.combatRoundEntries.getOrderedPositions().iterator();
    }

    /**
     * Retrieves all participants in the order they can act.
     *
     * @return the collection of ordered participant ids.
     */
    public final UniqueIds getOrderedParticipantIds() {
        return this.combatRoundEntries.getOrderedParticipantIds();
    }

    /**
     * Retrieves the participants for a given position in the combat round.
     *
     * @param roundPosition the combat round position the participants shall
     * have.
     * @return the collection of participants.
     */
    public final UniqueIds getParticipantsForPosition(
            final CombatRoundPosition roundPosition) {
        return this.combatRoundEntries
                .getParticipantsForPosition(roundPosition);
    }

    /**
     * Adding new combat round entries to the list.
     *
     * @param combatRoundEntry the combat round entry to add.
     */
    public final void addCombatRoundEntry(
            final CombatRoundEntry combatRoundEntry) {
        this.combatRoundEntries.addCombatRoundEntry(combatRoundEntry);
    }

}
