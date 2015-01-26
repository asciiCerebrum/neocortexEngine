package org.asciicerebrum.mydndgame.domain.core.attribution;

import java.util.List;

/**
 *
 * @author species8472
 */
public class WeaponCategories {

    /**
     * The list of weapon categories.
     */
    private List<WeaponCategory> weaponCategories;

    /**
     * @return the weaponCategories
     */
    public final List<WeaponCategory> getWeaponCategories() {
        return weaponCategories;
    }

    /**
     * @param weaponCategoriesInput the weaponCategories to set
     */
    public final void setWeaponCategories(
            final List<WeaponCategory> weaponCategoriesInput) {
        this.weaponCategories = weaponCategoriesInput;
    }

    public final boolean contains(final WeaponCategory weaponCategory) {
        return this.weaponCategories.contains(weaponCategory);
    }
}
