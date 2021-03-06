package org.asciicerebrum.neocortexengine.facades.game;

import java.util.Iterator;
import org.asciicerebrum.neocortexengine.domain.mechanics.ObserverHooks;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.source.ObserverSources;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusValue;
import org.asciicerebrum.neocortexengine.domain.game.Armor;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.mechanics.ObserverHook;
import org.asciicerebrum.neocortexengine.domain.game.Armors;

/**
 *
 * @author species8472
 */
public class DefaultArmorServiceFacade extends DefaultInventoryItemServiceFacade
        implements ArmorServiceFacade {

    /**
     * Maximum thinkable bonus value. It is considered unreachable in practice.
     */
    private static final long MAX_BONUS_VALUE = 999L;

    @Override
    public final BonusValue getArmorCheckPenalty(final Armor armor,
            final DndCharacter dndCharacter) {

        final BonusValue baseArmorCheckPenalty
                = armor.getBaseArmorCheckPenalty();

        return (BonusValue) this.getObservableService().triggerObservers(
                baseArmorCheckPenalty, armor,
                new ObserverSources(dndCharacter),
                new ObserverHooks(ObserverHook.ARMOR_CHECK_PENALTY),
                dndCharacter);
    }

    @Override
    public final BonusValue getMaxDexBonus(final Armor armor,
            final DndCharacter dndCharacter) {

        final BonusValue baseMaxDexBonus = armor.getBaseMaxDexBonus();

        return (BonusValue) this.getObservableService().triggerObservers(
                baseMaxDexBonus, armor,
                new ObserverSources(dndCharacter),
                new ObserverHooks(ObserverHook.ARMOR_MAX_DEX_BONUS),
                dndCharacter);
    }

    @Override
    public final BonusValue getMinimumArmorCheckPenalty(final Armors armors,
            final DndCharacter dndCharacter) {

        BonusValue minimumPenalty = new BonusValue();
        final Iterator<Armor> armorIterator = armors.iterator();
        while (armorIterator.hasNext()) {
            final Armor armor = armorIterator.next();
            final BonusValue singlePenalty
                    = this.getArmorCheckPenalty(armor, dndCharacter);
            if (singlePenalty.lessThan(minimumPenalty)) {
                minimumPenalty = singlePenalty;
            }
        }

        return minimumPenalty;
    }

    @Override
    public final BonusValue getMinimumMaxDexBonus(final Armors armors,
            final DndCharacter dndCharacter) {

        BonusValue minimumMaxDex = new BonusValue(MAX_BONUS_VALUE);
        final Iterator<Armor> armorIterator = armors.iterator();
        while (armorIterator.hasNext()) {
            final Armor armor = armorIterator.next();
            final BonusValue singleMaxDex
                    = this.getMaxDexBonus(armor, dndCharacter);
            if (singleMaxDex.lessThan(minimumMaxDex)) {
                minimumMaxDex = singleMaxDex;
            }
        }
        return minimumMaxDex;
    }

}
