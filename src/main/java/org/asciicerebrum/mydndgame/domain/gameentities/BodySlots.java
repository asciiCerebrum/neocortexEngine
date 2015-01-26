package org.asciicerebrum.mydndgame.domain.gameentities;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.asciicerebrum.mydndgame.domain.core.attribution.BodySlotType;
import org.asciicerebrum.mydndgame.domain.core.mechanics.Boni;
import org.asciicerebrum.mydndgame.domain.core.mechanics.BonusSource;
import org.asciicerebrum.mydndgame.domain.core.mechanics.BonusSources;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSource;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSources;
import org.asciicerebrum.mydndgame.observers.Observers;

/**
 *
 * @author species8472
 */
public class BodySlots implements BonusSource, ObserverSource {

    /**
     * The list of body slots.
     */
    private List<BodySlot> bodySlots = new ArrayList<BodySlot>();

    /**
     * @param bodySlotsInput the bodySlots to set
     */
    public final void setBodySlots(final List<BodySlot> bodySlotsInput) {
        this.bodySlots = bodySlotsInput;
    }

    public final boolean contains(final BodySlot bodySlot) {
        return this.bodySlots.contains(bodySlot);
    }

    public final boolean containsType(final BodySlotType bodySlotType) {
        for (BodySlot bodySlot : bodySlots) {
            if (bodySlot.isOfType(bodySlotType)) {
                return true;
            }
        }
        return false;
    }

    public final void add(final BodySlot bodySlot) {
        this.bodySlots.add(bodySlot);
    }

    @Override
    public final BonusSources getBonusSources() {
        BonusSources bonusSources = new BonusSources();

        for (BodySlot bodySlot : this.bodySlots) {
            bonusSources.add(bodySlot);
        }

        return bonusSources;
    }

    @Override
    public final Boni getBoni() {
        return Boni.EMPTY_BONI;
    }

    @Override
    public final Observers getObservers() {
        return Observers.EMPTY_OBSERVERS;
    }

    @Override
    public final ObserverSources getObserverSources() {
        ObserverSources observerSources = new ObserverSources();

        for (final BodySlot bodySlot : this.bodySlots) {
            observerSources.add(bodySlot);
        }

        return observerSources;
    }

    public final Armors getArmorWorn() {
        final Armors armors = new Armors();

        for (final BodySlot slot : this.bodySlots) {
            if (slot.getItem() instanceof Armor) {
                armors.add((Armor) slot.getItem());
            }
        }
        return armors;
    }

    public final Weapons getWeaponsWielded() {
        final Weapons weapons = new Weapons();

        for (final BodySlot slot : this.bodySlots) {
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

        for (final BodySlot originalSlot : this.bodySlots) {
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
        for (final BodySlot bodySlot : this.bodySlots) {
            bodySlot.setHolder(uniqueEntity);
        }
    }

    public final BodySlot findFirstSimilar(final BodySlot bluePrint,
            final BodySlot.Facet... facets) {
        for (BodySlot candidate : this.bodySlots) {
            if (candidate.isSimilar(bluePrint, facets)) {
                return candidate;
            }
        }
        return null;
    }

    public final BodySlot getSlotForItem(final InventoryItem item) {
        for (final BodySlot bodySlot : this.bodySlots) {
            if (bodySlot.containsItem(item)) {
                return bodySlot;
            }
        }
        return null;
    }

}
