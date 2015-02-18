package org.asciicerebrum.mydndgame.domain.ruleentities.composition;

import java.util.ArrayList;
import java.util.Arrays;
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
public class Conditions implements BonusSource, ObserverSource {

    /**
     * The central collection of conditions.
     */
    private final List<Condition> elements = new ArrayList<Condition>();

    /**
     * Default constructor.
     */
    public Conditions() {

    }

    /**
     * Constructs a collection of conditions by a given list.
     *
     * @param conditionsInput the list of conditions.
     */
    public Conditions(final Condition... conditionsInput) {
        this.elements.addAll(Arrays.asList(conditionsInput));
    }

    /**
     * Adds a further condition to the collection.
     *
     * @param condition the condition to add.
     */
    public final void add(final Condition condition) {
        this.elements.add(condition);
    }

    @Override
    public final Boni getBoni() {
        return Boni.EMPTY_BONI;
    }

    @Override
    public final BonusSources getBonusSources() {
        final BonusSources bonusSources = new BonusSources();

        for (final Condition condition : this.elements) {
            bonusSources.add(condition);
        }

        return bonusSources;
    }

    /**
     * Iterator over the collection of conditions.
     *
     * @return the iterator.
     */
    public final Iterator<Condition> iterator() {
        return this.elements.iterator();
    }

}
