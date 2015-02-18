package org.asciicerebrum.mydndgame.domain.ruleentities.composition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntities;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Boni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSource;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSources;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.mydndgame.domain.ruleentities.BodySlot;
import org.asciicerebrum.mydndgame.domain.ruleentities.BodySlots;

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
     * Retrives all unique entities in the collection of body slots that are of
     * a certain class.
     *
     * @param uniqueEntities the collection of entities to fill.
     * @param clazz the class of the items needed.
     * @return the filled collection of unique entities.
     */
    public final UniqueEntities getItemsByClass(
            final UniqueEntities uniqueEntities, final Class clazz) {
        for (final PersonalizedBodySlot slot : this.elements) {
            if (clazz.isInstance(slot.getItem())) {
                uniqueEntities.add(slot.getItem());
            }
        }
        return uniqueEntities;
    }

    /**
     * Sets the holder for all personalized body slots at once.
     *
     * @param uniqueEntity the holder.
     */
    public final void setHolder(final UniqueEntity uniqueEntity) {
        for (final PersonalizedBodySlot bodySlot : this.elements) {
            bodySlot.setHolder(uniqueEntity);
        }
    }

    /**
     * Retrieves the body slot that is associated with the given item.
     *
     * @param item the item to be found in one of the personalized body slots.
     * @return the slot holding this item.
     */
    public final PersonalizedBodySlot getSlotForItem(final UniqueEntity item) {
        for (final PersonalizedBodySlot bodySlot : this.elements) {
            if (bodySlot.containsItem(item)) {
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

    /**
     * Adds a collection of normal body slots to this collection while
     * transforming them to personalized body slots.
     *
     * @param bodySlots the body slots to transform and add.
     */
    public final void wrapSlots(final BodySlots bodySlots) {
        final Iterator<BodySlot> slotIterator = bodySlots.iterator();
        while (slotIterator.hasNext()) {
            final BodySlot slot = slotIterator.next();
            final PersonalizedBodySlot wrappedSlot = new PersonalizedBodySlot();
            wrappedSlot.setBodySlot(slot);
            this.add(wrappedSlot);
        }
    }

    @Override
    public final BonusSources getBonusSources() {
        BonusSources bonusSources = new BonusSources();

        for (PersonalizedBodySlot bodySlot : this.elements) {
            bonusSources.add(bodySlot);
        }

        return bonusSources;
    }

    @Override
    public final Boni getBoni() {
        return Boni.EMPTY_BONI;
    }

}
