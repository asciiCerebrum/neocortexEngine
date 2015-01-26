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

    private final List<CombatRoundPosition> combatRoundPositions
            = new ArrayList<CombatRoundPosition>();

    public final void addCombatRoundPosition(
            final CombatRoundPosition crPosition) {
        this.combatRoundPositions.add(crPosition);
    }

    public final CombatRoundPositions sort() {
        Collections.sort(this.combatRoundPositions);
        return this;
    }

    public CombatRoundPosition first() {
        this.sort();
        return this.combatRoundPositions.get(0);
    }

    public CombatRoundPosition getFollowUp(final CombatRoundPosition crPos) {
        this.sort();
        int idx = this.combatRoundPositions.indexOf(crPos);
        // when at the end, the follow up is the first again.
        if (idx == this.combatRoundPositions.size() - 1) {
            return this.combatRoundPositions.get(0);
        }
        return this.combatRoundPositions.get(idx + 1);
    }

    public final Iterator<CombatRoundPosition> iterator() {
        return this.combatRoundPositions.iterator();
    }

}
