package org.asciicerebrum.mydndgame.domain.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.asciicerebrum.mydndgame.domain.core.particles.CombatRoundPosition;
import org.asciicerebrum.mydndgame.domain.core.particles.CombatRoundPositions;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueIds;

/**
 *
 * @author species8472
 */
public class CombatRoundEntries {

    /**
     * List filtering predicate that compares participants with the one given in
     * the constructor.
     */
    private static class IdentifyParticipantPredicate implements Predicate {

        /**
         * The dnd character to compare the others with.
         */
        private final UniqueId dndCharacterId;

        /**
         * Constructing the predicate with a dnd character.
         *
         * @param dndCharacterIdInput the dnd character to rule them all.
         */
        public IdentifyParticipantPredicate(
                final UniqueId dndCharacterIdInput) {
            this.dndCharacterId = dndCharacterIdInput;
        }

        @Override
        public final boolean evaluate(final Object object) {
            final CombatRoundEntry entry
                    = (CombatRoundEntry) object;

            if (entry.getParticipantId() == null) {
                return false;
            }
            return entry.getParticipantId().equals(this.dndCharacterId);
        }
    }

    /**
     * Iterator over the uniqueIds of the participants.
     */
    private static class ParticipantsIterator implements Iterator<UniqueId> {

        /**
         * Subiterator for delegation.
         */
        private final Iterator<CombatRoundEntry> creIterator;

        /**
         * Constructor with the subiterator for delegation.
         *
         * @param creIteratorInput the subiterator.
         */
        public ParticipantsIterator(
                final Iterator<CombatRoundEntry> creIteratorInput) {
            this.creIterator = creIteratorInput;
        }

        @Override
        public final boolean hasNext() {
            return this.creIterator.hasNext();
        }

        @Override
        public final UniqueId next() {
            return this.creIterator.next().getParticipantId();
        }

        @Override
        public final void remove() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    /**
     * The central list of combat round entries.
     */
    private final List<CombatRoundEntry> elements
            = new ArrayList<CombatRoundEntry>();

    /**
     * Adds or updates a combat round entry. The update is performed if the
     * dndCharacter is already present in the list.
     *
     * @param crEntry the combat round entry to add or update.
     */
    public final void addCombatRoundEntry(final CombatRoundEntry crEntry) {
        final CombatRoundEntry existingCrEntry
                = this.getEntryByParticipant(crEntry.getParticipantId());

        if (existingCrEntry == null) {
            this.elements.add(crEntry);
        } else {
            existingCrEntry.setCombatRoundPosition(
                    crEntry.getCombatRoundPosition());
        }
    }

    /**
     * Retrieves the one combat round entry containing the given dnd character.
     *
     * @param dndCharacterId the dnd character in question.
     * @return the combat round entry with this character.
     */
    public final CombatRoundEntry getEntryByParticipant(
            final UniqueId dndCharacterId) {
        return (CombatRoundEntry) CollectionUtils
                .find(this.elements,
                        new IdentifyParticipantPredicate(dndCharacterId));
    }

    /**
     * Retrieves the one combat round position associated with the given dnd
     * character.
     *
     * @param dndCharacterId the dnd character in question.
     * @return his / her combat round position.
     */
    public final CombatRoundPosition getPositionForParticipant(
            final UniqueId dndCharacterId) {
        final CombatRoundEntry entry = this.getEntryByParticipant(
                dndCharacterId);
        if (entry != null) {
            return entry.getCombatRoundPosition();
        }
        return null;
    }

    /**
     * Retrieves all participants with the same given combat round position.
     *
     * @param roundPosition the position in the combat round the participants
     * should have.
     * @return the collection of participants with that position.
     */
    public final UniqueIds getParticipantsForPosition(
            final CombatRoundPosition roundPosition) {
        UniqueIds participants = new UniqueIds();

        for (CombatRoundEntry crEntry : this.elements) {

            if (crEntry.isCombatRoundPosition(roundPosition)) {
                participants.add(crEntry.getParticipantId());
            }
        }

        return participants;
    }

    /**
     * Extracts all the positions out of the collection of combat round entries.
     *
     * @return the combat round positions found in this collection.
     */
    final CombatRoundPositions getPositions() {
        CombatRoundPositions crPositions = new CombatRoundPositions();
        for (CombatRoundEntry crEntry : this.elements) {
            crPositions.addCombatRoundPosition(
                    crEntry.getCombatRoundPosition());
        }
        return crPositions;
    }

    /**
     * Extracts all the positions out of the collection of combat round entries
     * and sorts them in a natural alphanumeric way.
     *
     * @return the sorted collection of combat round positions found in this
     * collection.
     */
    public final CombatRoundPositions getOrderedPositions() {
        return this.getPositions().sort();
    }

    /**
     * Retrieves the very first combat round position of this collection.
     *
     * @return the first combat round position.
     */
    public final CombatRoundPosition getFirstRoundPosition() {
        return this.getOrderedPositions().first();
    }

    /**
     * Retrieves the one combat round position that directy follows the given
     * one.
     *
     * @param combatRoundPosition the previous combat round position.
     * @return the next combat round position.
     */
    public final CombatRoundPosition getRoundPositionFollowUp(
            final CombatRoundPosition combatRoundPosition) {
        return this.getPositions().getFollowUp(combatRoundPosition);
    }

    /**
     * Delegates all iterator calls to the corresponding iterator of the combat
     * round entries.
     *
     * @return iterator over only the characters in the combat round entries.
     */
    public final Iterator<UniqueId> participantsIterator() {
        return new ParticipantsIterator(this.elements.iterator());
    }

    /**
     * Determines emptyness of the underlying collection.
     *
     * @return true if there are no elements, false otherwise.
     */
    public final boolean isEmpty() {
        return this.elements.isEmpty();
    }

}
