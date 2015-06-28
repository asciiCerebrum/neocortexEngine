package org.asciicerebrum.neocortexengine.domain.ruleentities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.ContextBoni;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.source.BonusSource;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.source.UniqueEntityResolver;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.source.ObserverSource;

/**
 *
 * @author species8472
 */
public class Feats implements BonusSource, ObserverSource {

    /**
     * Central collection of feats.
     */
    private final List<Feat> elements = new ArrayList<Feat>();

    /**
     * Standard constructor for an empty list of feat types.
     */
    public Feats() {

    }

    /**
     * Constructor for a given list of feat types.
     *
     * @param feats the feat to add to the collection from the beginning.
     */
    public Feats(final List<Feat> feats) {
        this.elements.addAll(feats);
    }

    /**
     * Adds a further feat to the collection.
     *
     * @param feat the feat to add.
     */
    public final void addFeat(final Feat feat) {
        this.elements.add(feat);
    }

    @Override
    public final ContextBoni getBoni(final UniqueEntity context,
            final UniqueEntityResolver resolver) {
        final ContextBoni ctxBoni = new ContextBoni();

        for (final Feat feat : this.elements) {
            ctxBoni.add(feat.getBoni(context, resolver));
        }

        return ctxBoni;
    }

    /**
     * Iterator over the collection of feats.
     *
     * @return the iterator.
     */
    public final Iterator<Feat> iterator() {
        return this.elements.iterator();
    }

    /**
     * Gathers all feat bindings within this collection that correspond to a
     * feat of the given type.
     *
     * @param featType the feat type in question.
     * @return the collection of gathered feat bindings associated with the
     * type.
     */
    public final FeatBindings getFeatBindingsByFeatType(
            final FeatType featType) {
        final FeatBindings bindings = new FeatBindings();

        for (final Feat feat : this.elements) {
            if (featType.equals(feat.getFeatType())
                    && feat.getFeatBinding() != null) {
                bindings.add(feat.getFeatBinding());
            }
        }
        return bindings;
    }

}
