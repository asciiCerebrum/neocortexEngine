package org.asciicerebrum.mydndgame.facades.gameentities;

import java.util.Iterator;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverHooks;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSources;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.game.entities.Armor;
import org.asciicerebrum.mydndgame.domain.game.entities.DndCharacter;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverHook;
import org.asciicerebrum.mydndgame.domain.game.entities.Armors;

/**
 *
 * @author species8472
 */
public class DefaultArmorServiceFacade extends DefaultInventoryItemServiceFacade
        implements ArmorServiceFacade {

    /**
     * {@inheritDoc }
     */
    @Override
    public final BonusValue getArmorCheckPenalty(final Armor armor,
            final DndCharacter dndCharacter) {

        final BonusValue baseArmorCheckPenalty
                = armor.getBaseArmorCheckPenalty();

        return (BonusValue) this.observableService.triggerObservers(
                baseArmorCheckPenalty, armor,
                new ObserverSources(dndCharacter),
                new ObserverHooks(ObserverHook.ARMOR_CHECK_PENALTY),
                dndCharacter);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public final BonusValue getMaxDexBonus(final Armor armor,
            final DndCharacter dndCharacter) {

        final BonusValue baseMaxDexBonus = armor.getBaseMaxDexBonus();

        return (BonusValue) this.observableService.triggerObservers(
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

        BonusValue minimumMaxDex = new BonusValue(999L);
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
