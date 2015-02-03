package org.asciicerebrum.mydndgame.domain.rules.composition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntities;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.mechanics.interfaces.Boni;
import org.asciicerebrum.mydndgame.domain.mechanics.interfaces.BonusSource;
import org.asciicerebrum.mydndgame.domain.mechanics.interfaces.BonusSources;
import org.asciicerebrum.mydndgame.domain.mechanics.ObserverSource;
import org.asciicerebrum.mydndgame.domain.rules.BodySlot;
import org.asciicerebrum.mydndgame.domain.rules.BodySlots;

/**
 *
 * @author species8472
 */
public class PersonalizedBodySlots implements BonusSource, ObserverSource {

    private final List<PersonalizedBodySlot> elements
            = new ArrayList<PersonalizedBodySlot>();

    public final void add(final PersonalizedBodySlot slot) {
        this.elements.add(slot);
    }

    public final Iterator<PersonalizedBodySlot> iterator() {
        return this.elements.iterator();
    }

    public final UniqueEntities getItemsByClass(
            final UniqueEntities uniqueEntities, final Class clazz) {
        for (final PersonalizedBodySlot slot : this.elements) {
            if (clazz.isInstance(slot.getItem())) {
                uniqueEntities.add(slot.getItem());
            }
        }
        return uniqueEntities;
    }

    public final void setHolder(final UniqueEntity uniqueEntity) {
        for (final PersonalizedBodySlot bodySlot : this.elements) {
            bodySlot.setHolder(uniqueEntity);
        }
    }

    public final PersonalizedBodySlot getSlotForItem(final UniqueEntity item) {
        for (final PersonalizedBodySlot bodySlot : this.elements) {
            if (bodySlot.containsItem(item)) {
                return bodySlot;
            }
        }
        return null;
    }

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
