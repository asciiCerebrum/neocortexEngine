package org.asciicerebrum.mydndgame.domain.game;

import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.ruleentities.ArmorCategory;
import org.asciicerebrum.mydndgame.domain.ruleentities.ArmorPrototype;
import org.asciicerebrum.mydndgame.domain.ruleentities.Proficiency;

/**
 *
 * @author species8472
 */
public class Armor extends InventoryItem {

    public final BonusValue getBaseMaxDexBonus() {
        return this.getInventoryItemPrototype().getMaxDexBonus();
    }

    public final BonusValue getBaseArmorCheckPenalty() {
        return this.getInventoryItemPrototype().getArmorCheckPenalty();
    }

    @Override
    protected final ArmorPrototype getInventoryItemPrototype() {
        return (ArmorPrototype) super.getInventoryItemPrototype();
    }

    public final boolean hasProficiency(final Proficiency proficiency) {
        return proficiency.equals(this.getInventoryItemPrototype()
                .getProficiency());
    }

    public final boolean hasArmorCategory(final ArmorCategory armorCategory) {
        return armorCategory.equals(this.getInventoryItemPrototype()
                .getArmorCategory());
    }

}
