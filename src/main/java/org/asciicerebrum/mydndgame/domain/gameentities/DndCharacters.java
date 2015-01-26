package org.asciicerebrum.mydndgame.domain.gameentities;

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

}
