package org.asciicerebrum.mydndgame.domain.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author species8472
 */
public class DndCharacters {

    private final List<DndCharacter> elements
            = new ArrayList<DndCharacter>();

    public final void addDndCharacter(final DndCharacter dndCharacter) {
        this.elements.add(dndCharacter);
    }

    public final void addDndCharacters(final DndCharacters dndCharactersInput) {
        this.elements.addAll(dndCharactersInput.elements);
    }

    public final Iterator<DndCharacter> iterator() {
        return this.elements.iterator();
    }

    public final boolean hasEntries() {
        return !this.elements.isEmpty();
    }

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

}
