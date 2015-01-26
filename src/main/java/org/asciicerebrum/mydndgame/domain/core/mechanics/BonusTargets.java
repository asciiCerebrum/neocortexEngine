package org.asciicerebrum.mydndgame.domain.core.mechanics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author species8472
 */
public class BonusTargets {

    private final List<BonusTarget> bonusTargets
            = new ArrayList<BonusTarget>();

    public BonusTargets(final BonusTarget... bonusTargets) {
        this.bonusTargets.addAll(Arrays.asList(bonusTargets));
    }

    public final boolean contains(final BonusTarget bonusTarget) {
        return this.bonusTargets.contains(bonusTarget);
    }

}
