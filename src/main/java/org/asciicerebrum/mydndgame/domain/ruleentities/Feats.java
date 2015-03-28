package org.asciicerebrum.mydndgame.domain.ruleentities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Boni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSource;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSources;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;

/**
 *
 * @author species8472
 */
public class Feats implements BonusSource, ObserverSource {

    /**
     * Central collection of feats.
     */
    private final List<FeatType> elements = new ArrayList<FeatType>();

    /**
     * Standard constructor for an empty list of feat types.
     */
    public Feats() {

    }

    /**
     * Constructor for a given list of feat types.
     *
     * @param featTypes the feat types to add to the collection from the
     * beginning.
     */
    public Feats(final List<FeatType> featTypes) {
        this.elements.addAll(featTypes);
    }

    /**
     * Adds a further feat to the collection.
     *
     * @param feat the feat to add.
     */
    public final void addFeat(final FeatType feat) {
        this.elements.add(feat);
    }

    @Override
    public final Boni getBoni() {
        return Boni.EMPTY_BONI;
    }

    @Override
    public final BonusSources getBonusSources() {
        final BonusSources bonusSources = new BonusSources();

        for (final FeatType feat : this.elements) {
            bonusSources.add(feat);
        }

        return bonusSources;
    }

    /**
     * Iterator over the collection of feats.
     *
     * @return the iterator.
     */
    public final Iterator<FeatType> iterator() {
        return this.elements.iterator();
    }

}
