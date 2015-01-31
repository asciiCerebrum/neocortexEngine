package org.asciicerebrum.mydndgame.domain.rules.composition;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.asciicerebrum.mydndgame.domain.core.mechanics.Boni;
import org.asciicerebrum.mydndgame.domain.core.mechanics.BonusSource;
import org.asciicerebrum.mydndgame.domain.core.mechanics.BonusSources;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSource;
import org.asciicerebrum.mydndgame.domain.game.entities.Armor;
import org.asciicerebrum.mydndgame.domain.game.entities.Armors;
import org.asciicerebrum.mydndgame.domain.game.entities.InventoryItem;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.game.entities.Weapon;
import org.asciicerebrum.mydndgame.domain.game.entities.Weapons;

/**
 *
 * @author species8472
 */
public class BodySlots implements BonusSource, ObserverSource {

    /**
     * The list of body slots.
     */
    private List<BodySlot> elements = new ArrayList<BodySlot>();

    /**
     * @param bodySlotsInput the bodySlots to set
     */
    public final void setBodySlots(final List<BodySlot> bodySlotsInput) {
        this.elements = bodySlotsInput;
    }

    public final boolean contains(final BodySlot bodySlot) {
        return this.elements.contains(bodySlot);
    }

    public final boolean containsType(final BodySlotType bodySlotType) {
        for (BodySlot bodySlot : elements) {
            if (bodySlot.isOfType(bodySlotType)) {
                return true;
            }
        }
        return false;
    }

    public final void add(final BodySlot bodySlot) {
        this.elements.add(bodySlot);
    }

    public final Iterator<BodySlot> iterator() {
        return this.elements.iterator();
    }

    @Override
    public final BonusSources getBonusSources() {
        BonusSources bonusSources = new BonusSources();

        for (BodySlot bodySlot : this.elements) {
            bonusSources.add(bodySlot);
        }

        return bonusSources;
    }

    @Override
    public final Boni getBoni() {
        return Boni.EMPTY_BONI;
    }

    public final Armors getArmorWorn() {
        final Armors armors = new Armors();

        for (final BodySlot slot : this.elements) {
            if (slot.getItem() instanceof Armor) {
                armors.add((Armor) slot.getItem());
            }
        }
        return armors;
    }

    public final Weapons getWeaponsWielded() {
        final Weapons weapons = new Weapons();

        for (final BodySlot slot : this.elements) {
            if (slot.getItem() instanceof Weapon) {
                weapons.add((Weapon) slot.getItem());
            }
        }
        return weapons;
    }

    public final BodySlots cloneSlots() {
        final BodySlots clonedSlots = new BodySlots();

        final Map<BodySlot, BodySlot> counterMap
                = new IdentityHashMap<BodySlot, BodySlot>();
        final Map<BodySlot, BodySlot> cloneMap
                = new IdentityHashMap<BodySlot, BodySlot>();

        for (final BodySlot originalSlot : this.elements) {
            final BodySlot cloneSlot = originalSlot.cloneSlot();
            clonedSlots.add(cloneSlot);
            if (originalSlot.getCounterSlot() != null) {
                counterMap.put(originalSlot, originalSlot.getCounterSlot());
            }
            cloneMap.put(originalSlot, cloneSlot);
        }

        // resolving cyclic counter slot references
        for (Entry<BodySlot, BodySlot> counterEntry : counterMap.entrySet()) {
            final BodySlot counterSourceOriginal = counterEntry.getKey();
            final BodySlot counterTargetOriginal = counterEntry.getValue();

            final BodySlot counterSourceClone
                    = cloneMap.get(counterSourceOriginal);
            final BodySlot counterTargetClone
                    = cloneMap.get(counterTargetOriginal);

            counterSourceClone.setCounterSlot(counterTargetClone);
        }

        return clonedSlots;
    }

    public final void setHolder(final UniqueEntity uniqueEntity) {
        for (final BodySlot bodySlot : this.elements) {
            bodySlot.setHolder(uniqueEntity);
        }
    }

    public final BodySlot findFirstSimilar(final BodySlot bluePrint,
            final BodySlot.Facet... facets) {
        for (BodySlot candidate : this.elements) {
            if (candidate.isSimilar(bluePrint, facets)) {
                return candidate;
            }
        }
        return null;
    }

    public final BodySlot getSlotForItem(final InventoryItem item) {
        for (final BodySlot bodySlot : this.elements) {
            if (bodySlot.containsItem(item)) {
                return bodySlot;
            }
        }
        return null;
    }

}
