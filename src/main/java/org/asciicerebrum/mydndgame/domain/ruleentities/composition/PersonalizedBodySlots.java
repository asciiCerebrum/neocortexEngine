package org.asciicerebrum.mydndgame.domain.ruleentities.composition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.ContextBoni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSource;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.UniqueEntityResolver;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.mydndgame.domain.ruleentities.BodySlot;

/**
 *
 * @author species8472
 */
public class PersonalizedBodySlots implements BonusSource, ObserverSource {

    /**
     * Central list of personalized body slots.
     */
    private final List<PersonalizedBodySlot> elements
            = new ArrayList<PersonalizedBodySlot>();

    /**
     * Adds a further personalized body slot to the collection.
     *
     * @param slot the slot to add.
     */
    public final void add(final PersonalizedBodySlot slot) {
        this.elements.add(slot);
    }

    /**
     * Iterator over the collection of personalized body slots.
     *
     * @return the iterator.
     */
    public final Iterator<PersonalizedBodySlot> iterator() {
        return this.elements.iterator();
    }

    /**
     * Retrieves the body slot that is associated with the given item.
     *
     * @param itemId the item to be found in one of the personalized body slots.
     * @return the slot holding this item.
     */
    public final PersonalizedBodySlot getSlotForItem(final UniqueId itemId) {
        for (final PersonalizedBodySlot bodySlot : this.elements) {
            if (bodySlot.containsItem(itemId)) {
                return bodySlot;
            }
        }
        return null;
    }

    /**
     * Retrieves the one personalized body slot that is characterised as the
     * counter slot of the given slot.
     *
     * @param personalizedSlot the slot the counter slot is needed for.
     * @return its counter slot.
     */
    public final PersonalizedBodySlot getCounterSlotForSlot(
            final PersonalizedBodySlot personalizedSlot) {
        final BodySlot counterSlot = personalizedSlot.getCounterBodySlot();
        for (final PersonalizedBodySlot bodySlot : this.elements) {
            if (bodySlot.getBodySlot() == counterSlot) {
                return bodySlot;
            }
        }
        return null;
    }

    /**
     * Retrieves the first personalized body slot of this collection, that
     * resembles the given one in all of the given facets.
     *
     * @param bluePrint the resemblence template.
     * @param facets the facets that need to be identical.
     * @return the first similar personalized body slot.
     */
    public final PersonalizedBodySlot findFirstSimilar(
            final PersonalizedBodySlot bluePrint,
            final PersonalizedBodySlot.Facet... facets) {
        for (PersonalizedBodySlot candidate : this.elements) {
            if (candidate.isSimilar(bluePrint, facets)) {
                return candidate;
            }
        }
        return null;
    }

    @Override
    public final ContextBoni getBoni(final UniqueEntity context,
            final UniqueEntityResolver resolver) {
        final ContextBoni ctxBoni = new ContextBoni();
        for (PersonalizedBodySlot bodySlot : this.elements) {
            ctxBoni.add(bodySlot.getBoni(context, resolver));
        }
        return ctxBoni;
    }

}
