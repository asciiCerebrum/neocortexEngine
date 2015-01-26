package org.asciicerebrum.mydndgame.domain.gameentities;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.asciicerebrum.mydndgame.domain.core.attribution.ArmorCategory;
import org.asciicerebrum.mydndgame.domain.core.attribution.Proficiency;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;

/**
 *
 * @author species8472
 */
public class Armors extends InventoryItems<Armor> {

    public final boolean containsArmorCategory(
            final ArmorCategory armorCategory) {
        for (final Armor armor : this.elements) {
            if (armor.hasArmorCategory(armorCategory)) {
                return true;
            }
        }
        return false;
    }

    public final boolean containsProficiency(final Proficiency proficiency) {
        for (final Armor armor : this.elements) {
            if (armor.hasProficiency(proficiency)) {
                return true;
            }
        }
        return false;
    }

    public final BonusValue getMinimumArmorCheckPenalty(
            final DndCharacter dndCharacter) {

        BonusValue minimumPenalty = new BonusValue();
        for (Armor armor : this.elements) {
            final BonusValue singlePenalty
                    = armor.getArmorCheckPenalty(dndCharacter);
            if (singlePenalty.lessThan(minimumPenalty)) {
                minimumPenalty = singlePenalty;
            }
        }

        return minimumPenalty;
    }

    public final BonusValue getMinimumMaxDexBonus(
            final DndCharacter dndCharacter) {

        BonusValue minimumMaxDex = new BonusValue(999l);
        for (Armor armor : this.elements) {
            final BonusValue singleMaxDex
                    = armor.getMaxDexBonus(dndCharacter);
            if (singleMaxDex.lessThan(minimumMaxDex)) {
                minimumMaxDex = singleMaxDex;
            }
        }
        return minimumMaxDex;
    }

    public Armors filterByArmorCateogry(final ArmorCategory armorCategory) {

        List<Armor> armorList = new ArrayList<Armor>();

        CollectionUtils.select(this.elements, new Predicate() {

            @Override
            public final boolean evaluate(final Object object) {
                Armor armor = (Armor) object;
                return armor.hasArmorCategory(armorCategory);
            }
        }, armorList);

        final Armors armors = new Armors();
        armors.add(armorList);
        return armors;
    }
}
