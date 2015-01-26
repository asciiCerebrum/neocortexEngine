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

    private final List<CombatRoundPosition> elements
            = new ArrayList<CombatRoundPosition>();

    public final void addCombatRoundPosition(
            final CombatRoundPosition crPosition) {
        this.elements.add(crPosition);
    }

    public final CombatRoundPositions sort() {
        Collections.sort(this.elements);
        return this;
    }

    public CombatRoundPosition first() {
        this.sort();
        return this.elements.get(0);
    }

    public CombatRoundPosition getFollowUp(final CombatRoundPosition crPos) {
        this.sort();
        int idx = this.elements.indexOf(crPos);
        // when at the end, the follow up is the first again.
        if (idx == this.elements.size() - 1) {
            return this.elements.get(0);
        }
        return this.elements.get(idx + 1);
    }

    public final Iterator<CombatRoundPosition> iterator() {
        return this.elements.iterator();
    }

}
