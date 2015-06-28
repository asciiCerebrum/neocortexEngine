package org.asciicerebrum.neocortexengine.domain.mechanics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author species8472
 */
public class BonusTargets {

    /**
     * Central list for the bonus targets.
     */
    private final List<BonusTarget> elements
            = new ArrayList<BonusTarget>();

    /**
     * Adds further bonus targets to the collection.
     *
     * @param bonusTargets the bonus targets to add.
     */
    public BonusTargets(final BonusTarget... bonusTargets) {
        this.elements.addAll(Arrays.asList(bonusTargets));
    }

    /**
     * Tests if the collection contains the given element.
     *
     * @param bonusTarget the bonus target to check.
     * @return true if collection contains the element, false otherwise.
     */
    public final boolean contains(final BonusTarget bonusTarget) {
        return this.elements.contains(bonusTarget);
    }

}
