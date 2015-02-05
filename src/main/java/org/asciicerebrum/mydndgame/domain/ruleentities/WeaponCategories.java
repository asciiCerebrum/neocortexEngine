package org.asciicerebrum.mydndgame.domain.ruleentities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author species8472
 */
public class WeaponCategories {

    /**
     * The list of weapon categories.
     */
    private final List<WeaponCategory> elements
            = new ArrayList<WeaponCategory>();

    /**
     * @return the weaponCategories
     */
    public final List<WeaponCategory> getWeaponCategories() {
        return elements;
    }

    /**
     * @param weaponCategoriesInput the weaponCategories to set
     */
    public final void add(final List<WeaponCategory> weaponCategoriesInput) {
        this.elements.addAll(weaponCategoriesInput);
    }
    
    public final boolean contains(final WeaponCategory weaponCategory) {
        return this.elements.contains(weaponCategory);
    }
}
