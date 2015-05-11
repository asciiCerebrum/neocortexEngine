package org.asciicerebrum.mydndgame.domain.core.particles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author species8472
 */
public class CombatRoundPositions {

    /**
     * The list of combat round positions.
     */
    private final List<CombatRoundPosition> elements
            = new ArrayList<CombatRoundPosition>();

    /**
     * Adds another instance to the list.
     *
     * @param crPosition the combat round position instance to add.
     */
    public final void addCombatRoundPosition(
            final CombatRoundPosition crPosition) {
        this.elements.add(crPosition);
    }

    /**
     * Sort the list reverse-alphanumerically and return it.
     *
     * @return the sorted list.
     */
    public final CombatRoundPositions sort() {
        Collections.sort(this.elements);
        Collections.reverse(this.elements);
        return this;
    }

    /**
     * Returns the first element of the sorted list.
     *
     * @return the first element in the sorted list.
     */
    public final CombatRoundPosition first() {
        this.sort();
        return this.elements.get(0);
    }

    /**
     * Returns the combat round position following the given one in the sorted
     * list.
     *
     * @param crPos the instance the follow up is needed for.
     * @return the follow up combat round position.
     */
    public final CombatRoundPosition getFollowUp(
            final CombatRoundPosition crPos) {
        this.sort();
        int idx = this.elements.indexOf(crPos);
        // when at the end, the follow up is the first again.
        if (idx == this.elements.size() - 1) {
            return this.elements.get(0);
        }
        return this.elements.get(idx + 1);
    }

    /**
     * Iterator of the combat round positions.
     *
     * @return the iterator.
     */
    public final Iterator<CombatRoundPosition> iterator() {
        return this.elements.iterator();
    }

}
