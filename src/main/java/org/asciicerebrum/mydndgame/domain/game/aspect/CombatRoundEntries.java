package org.asciicerebrum.mydndgame.domain.game.aspect;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.asciicerebrum.mydndgame.domain.core.particles.CombatRoundPosition;
import org.asciicerebrum.mydndgame.domain.core.particles.CombatRoundPositions;
import org.asciicerebrum.mydndgame.domain.game.entities.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.entities.DndCharacters;

/**
 *
 * @author species8472
 */
public class CombatRoundEntries {

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
                = this.getEntryByParticipant(crEntry.getParticipant());

        if (existingCrEntry == null) {
            this.elements.add(crEntry);
        } else {
            existingCrEntry.setCombatRoundPosition(
                    crEntry.getCombatRoundPosition());
        }
    }

    public final CombatRoundEntry getEntryByParticipant(
            final DndCharacter dndCharacter) {
        return (CombatRoundEntry) CollectionUtils
                .find(this.elements, new Predicate() {

                    public boolean evaluate(Object object) {
                        final CombatRoundEntry entry
                        = (CombatRoundEntry) object;

                        return entry.getParticipant().equals(dndCharacter);
                    }
                });
    }

    public final CombatRoundPosition getPositionForParticipant(
            final DndCharacter dndCharacter) {
        final CombatRoundEntry entry = this.getEntryByParticipant(dndCharacter);
        if (entry != null) {
            return entry.getCombatRoundPosition();
        }
        return null;
    }

    public final DndCharacters getParticipantsForPosition(
            final CombatRoundPosition roundPosition) {
        DndCharacters participants = new DndCharacters();

        for (CombatRoundEntry crEntry : this.elements) {

            if (crEntry.isCombatRoundPosition(roundPosition)) {
                participants.addDndCharacter(crEntry.getParticipant());
            }
        }

        return participants;
    }

    final CombatRoundPositions getPositions() {
        CombatRoundPositions crPositions = new CombatRoundPositions();
        for (CombatRoundEntry crEntry : this.elements) {
            crPositions.addCombatRoundPosition(
                    crEntry.getCombatRoundPosition());
        }
        return crPositions;
    }

    public final CombatRoundPositions getOrderedPositions() {
        return this.getPositions().sort();
    }

    public final CombatRoundPosition getFirstRoundPosition() {
        return this.getPositions().first();
    }

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
    public final Iterator<DndCharacter> participantsIterator() {
        return new Iterator<DndCharacter>() {

            private Iterator<CombatRoundEntry> creIterator
                    = elements.iterator();

            public boolean hasNext() {
                return this.creIterator.hasNext();
            }

            public DndCharacter next() {
                return this.creIterator.next().getParticipant();
            }

            public void remove() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };
    }

}
