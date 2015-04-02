package org.asciicerebrum.mydndgame.domain.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntities;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;

/**
 *
 * @author species8472
 */
public class DndCharacters {

    /**
     * Central collection of dnd characters.
     */
    private final List<DndCharacter> elements
            = new ArrayList<DndCharacter>();

    /**
     * Adds a further dnd character to the collection.
     *
     * @param dndCharacter the character to add.
     */
    public final void addDndCharacter(final DndCharacter dndCharacter) {
        this.elements.add(dndCharacter);
    }

    /**
     * Adds a further collection of dnd characters to the list.
     *
     * @param dndCharactersInput the collection to add.
     */
    public final void addDndCharacters(final DndCharacters dndCharactersInput) {
        this.elements.addAll(dndCharactersInput.elements);
    }

    /**
     * Iterator over the collection of dnd characters.
     *
     * @return the iterator.
     */
    public final Iterator<DndCharacter> iterator() {
        return this.elements.iterator();
    }

    /**
     * Tests if there are entries in the collection.
     *
     * @return true if non emtpy, false otherwise.
     */
    public final boolean hasEntries() {
        return !this.elements.isEmpty();
    }

    /**
     * Tests if there are at least 2 elements in the collection.
     *
     * @return true if size limit is met, false otherwise.
     */
    public final boolean hasMultipleEntries() {
        return this.elements.size() > 1;
    }

    /**
     * Tests if a given character is part of this list.
     *
     * @param dndCharacter the character in question.
     * @return true if character is part of the list, false otherwise.
     */
    public final boolean contains(final DndCharacter dndCharacter) {
        return this.elements.contains(dndCharacter);
    }

    /**
     * Merges all dnd character class type intances of the entities collection
     * into this collection.
     *
     * @param uniqueEntities the collection of potential dnd characters..
     */
    public final void merge(final UniqueEntities uniqueEntities) {
        final Iterator<UniqueEntity> entityIterator
                = uniqueEntities.iterator();

        while (entityIterator.hasNext()) {
            final UniqueEntity entity = entityIterator.next();
            if (entity instanceof DndCharacter) {
                this.addDndCharacter((DndCharacter) entity);
            }
        }
    }

}
