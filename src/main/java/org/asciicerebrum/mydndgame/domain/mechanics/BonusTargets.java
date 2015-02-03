package org.asciicerebrum.mydndgame.domain.mechanics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author species8472
 */
public class BonusTargets {

    private final List<BonusTarget> elements
            = new ArrayList<BonusTarget>();

    public BonusTargets(final BonusTarget... bonusTargets) {
        this.elements.addAll(Arrays.asList(bonusTargets));
    }

    public final boolean contains(final BonusTarget bonusTarget) {
        return this.elements.contains(bonusTarget);
    }

}
