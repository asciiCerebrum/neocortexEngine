package org.asciicerebrum.mydndgame.domain.game.aspect;

import java.util.Iterator;
import org.asciicerebrum.mydndgame.domain.core.particles.CombatRoundNumber;
import org.asciicerebrum.mydndgame.domain.core.particles.CombatRoundPosition;
import org.asciicerebrum.mydndgame.domain.game.entities.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.entities.DndCharacters;

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
    private CombatRoundEntries combatRoundEntries;

    /**
     * The current date of this particular combat round encounter.
     */
    private WorldDate currentDate;

    public final WorldDate getCurrentDate() {
        if (this.currentDate == null) {
            this.moveToNextPosition();
        }
        return this.currentDate;
    }

    public final void setCombatRoundEntries(
            final CombatRoundEntries combatRoundEntriesInput) {
        this.combatRoundEntries = combatRoundEntriesInput;
    }

    public final DndCharacter getCurrentParticipant() {
        if (this.currentDate == null) {
            throw new IllegalStateException("Current date of combat round "
                    + "not yet set up.");
        }
        DndCharacters currentParticipants
                = this.combatRoundEntries.getParticipantsForPosition(
                        this.currentDate.getCombatRoundPosition());

        return currentParticipants.iterator().next();
    }

    public final boolean isCurrentParticipant(final DndCharacter dndCharacter) {
        return dndCharacter.equals(this.getCurrentParticipant());
    }

    final void initializeDate() {
        this.setCurrentDate(new WorldDate());
        this.currentDate.setCombatRoundNumber(new CombatRoundNumber(0L));
        this.currentDate.setCombatRoundPosition(
                this.combatRoundEntries.getFirstRoundPosition());
    }

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

    public final Iterator<DndCharacter> participantsIterator() {
        return this.combatRoundEntries.participantsIterator();
    }

    /**
     * @param currentDateInput the currentDate to set
     */
    public final void setCurrentDate(final WorldDate currentDateInput) {
        this.currentDate = currentDateInput;
    }

    public final void addParticipant(final DndCharacter participant,
            final CombatRoundPosition roundPosition) {
        final CombatRoundEntry entry = new CombatRoundEntry();
        entry.setParticipant(participant);
        entry.setCombatRoundPosition(roundPosition);

        this.combatRoundEntries.addCombatRoundEntry(entry);
    }

    public final CombatRoundPosition getPositionForParticipant(
            final DndCharacter participant) {
        return this.combatRoundEntries.getPositionForParticipant(participant);
    }

    public final WorldDate getNextParticipationDate(
            final DndCharacter dndCharacter) {
        final CombatRoundEntry entry
                = this.combatRoundEntries.getEntryByParticipant(dndCharacter);
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

    public final Iterator<CombatRoundPosition> getOrderedPositions() {
        return this.combatRoundEntries.getOrderedPositions().iterator();
    }

    public final DndCharacters getParticipantsForPosition(
            final CombatRoundPosition roundPosition) {
        return this.combatRoundEntries
                .getParticipantsForPosition(roundPosition);
    }

}
