package org.asciicerebrum.mydndgame.domain.gameentities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author species8472
 */
public class DndCharacters {

    private final List<DndCharacter> dndCharacters
            = new ArrayList<DndCharacter>();

    public final void addDndCharacter(final DndCharacter dndCharacter) {
        this.dndCharacters.add(dndCharacter);
    }

    public final void addDndCharacters(final DndCharacters dndCharactersInput) {
        this.dndCharacters.addAll(dndCharactersInput.dndCharacters);
    }

    public final Iterator<DndCharacter> iterator() {
        return this.dndCharacters.iterator();
    }

    public final boolean hasEntries() {
        return !this.dndCharacters.isEmpty();
    }

    public final boolean hasMultipleEntries() {
        return this.dndCharacters.size() > 1;
    }

}
