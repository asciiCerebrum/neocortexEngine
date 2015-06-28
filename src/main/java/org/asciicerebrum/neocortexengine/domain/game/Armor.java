package org.asciicerebrum.neocortexengine.domain.game;

import org.asciicerebrum.neocortexengine.domain.core.particles.BonusValue;
import org.asciicerebrum.neocortexengine.domain.ruleentities.ArmorCategory;
import org.asciicerebrum.neocortexengine.domain.ruleentities.ArmorPrototype;
import org.asciicerebrum.neocortexengine.domain.ruleentities.Proficiency;

/**
 *
 * @author species8472
 */
public class Armor extends InventoryItem {

    /**
     * Retrieves the basic value of the maximum dexterity bonus this armor can
     * provide. It is not modified by any further characteristics.
     *
     * @return the basic max dex bonus.
     */
    public final BonusValue getBaseMaxDexBonus() {
        return this.getArmorPrototype().getMaxDexBonus();
    }

    /**
     * Retrieves the basic value of the armor check penalty this armor can
     * provide. It is not modified by any further characteristics.
     *
     * @return the basic armor check penalty.
     */
    public final BonusValue getBaseArmorCheckPenalty() {
        return this.getArmorPrototype().getArmorCheckPenalty();
    }

    /**
     * @return the armor prototype.
     */
    public final ArmorPrototype getArmorPrototype() {
        return (ArmorPrototype) super.getInventoryItemPrototype();
    }

    /**
     * Determines if the given proficiency is basically supported by this armor.
     * It is not modified by any further characteristics.
     *
     * @param proficiency the proficiency in question.
     * @return true, if proficiency is supported, false otherwise.
     */
    public final boolean hasProficiency(final Proficiency proficiency) {
        return proficiency.equals(this.getArmorPrototype().getProficiency());
    }

    /**
     * Determines if the given armor category is basically supported by this
     * armor. It is not modified by any further characteristics.
     *
     * @param armorCategory the armor category in question.
     * @return true, if armor category is supported, false otherwise.
     */
    public final boolean hasArmorCategory(final ArmorCategory armorCategory) {
        return armorCategory.equals(this.getArmorPrototype()
                .getArmorCategory());
    }

}
