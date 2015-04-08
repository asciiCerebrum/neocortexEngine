package org.asciicerebrum.mydndgame.domain.ruleentities.composition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.ContextBoni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSource;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.UniqueEntityResolver;
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
    public final ContextBoni getBoni(final UniqueEntity context,
            final UniqueEntityResolver resolver) {
        final ContextBoni ctxBoni = new ContextBoni();

        for (final Condition condition : this.elements) {
            ctxBoni.add(condition.getBoni(context, resolver));
        }

        return ctxBoni;
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
