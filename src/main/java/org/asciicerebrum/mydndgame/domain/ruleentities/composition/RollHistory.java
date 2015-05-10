package org.asciicerebrum.mydndgame.domain.ruleentities.composition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author species8472
 */
public class RollHistory {

    /**
     * The central collection of roll history entry elements.
     */
    private final List<RollHistoryEntry> elements
            = new ArrayList<RollHistoryEntry>();

    /**
     * Adds a further element of entries to the roll history.
     *
     * @param rollHistoryEntry the entry to add.
     */
    public final void add(final RollHistoryEntry rollHistoryEntry) {
        this.elements.add(rollHistoryEntry);
    }

    /**
     * Iterator over the collection of entries.
     *
     * @return the iterator.
     */
    public final Iterator<RollHistoryEntry> iterator() {
        return this.elements.iterator();
    }

}
