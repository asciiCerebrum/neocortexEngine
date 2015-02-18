package org.asciicerebrum.mydndgame.domain.mechanics.bonus.source;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author species8472
 */
public class BonusSources {

    /**
     * Ready to use empty collection of bonus sources.
     */
    public static final BonusSources EMPTY_BONUSSOURCES = new BonusSources();

    /**
     * Central collection of bonus sources.
     */
    private final List<BonusSource> elements = new ArrayList<BonusSource>();

    /**
     * Default constructor.
     */
    public BonusSources() {
    }

    /**
     * Constructs collection of bonus sources by a given list.
     *
     * @param bonusSources the list of bonus sources to transform to this
     * collection.
     */
    public BonusSources(final BonusSource... bonusSources) {
        this.elements.addAll(Arrays.asList(bonusSources));
    }

    /**
     * Adds a further bonus source to the collection.
     *
     * @param bonusSource the item to add.
     */
    public final void add(final BonusSource bonusSource) {
        this.elements.add(bonusSource);
    }

    /**
     * Iterator over the collection of bonus sources.
     *
     * @return the iterator.
     */
    public final Iterator<BonusSource> iterator() {
        return this.elements.iterator();
    }

}
