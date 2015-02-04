package org.asciicerebrum.mydndgame.domain.ruleentities;

import java.util.List;

/**
 *
 * @author species8472
 */
public class WeaponCategories {

    /**
     * The list of weapon categories.
     */
    private List<WeaponCategory> elements;

    /**
     * @return the weaponCategories
     */
    public final List<WeaponCategory> getWeaponCategories() {
        return elements;
    }

    /**
     * @param weaponCategoriesInput the weaponCategories to set
     */
    public final void setWeaponCategories(
            final List<WeaponCategory> weaponCategoriesInput) {
        this.elements = weaponCategoriesInput;
    }

    public final boolean contains(final WeaponCategory weaponCategory) {
        return this.elements.contains(weaponCategory);
    }
}
