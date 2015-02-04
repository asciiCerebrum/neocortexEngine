package org.asciicerebrum.mydndgame.domain.game;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.asciicerebrum.mydndgame.domain.ruleentities.ArmorCategory;
import org.asciicerebrum.mydndgame.domain.ruleentities.Proficiency;

/**
 *
 * @author species8472
 */
public class Armors extends InventoryItems<Armor> {

    private static class HasArmorCategoryPredicate implements Predicate {

        private final ArmorCategory armorCategory;

        public HasArmorCategoryPredicate(
                final ArmorCategory armorCategoryInput) {
            this.armorCategory = armorCategoryInput;
        }

        @Override
        public final boolean evaluate(final Object object) {
            Armor armor = (Armor) object;
            return armor.hasArmorCategory(this.armorCategory);
        }
    }

    public Armors() {

    }

    public Armors(final List<Armor> armorList) {
        this.elements.addAll(armorList);
    }

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

    public Armors filterByArmorCateogry(final ArmorCategory armorCategory) {

        List<Armor> armorList = new ArrayList<Armor>();

        CollectionUtils.select(this.elements,
                new HasArmorCategoryPredicate(armorCategory), armorList);

        return new Armors(armorList);
    }
}
