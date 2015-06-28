package org.asciicerebrum.neocortexengine.domain.ruleentities;

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
     * Clears the list first.
     *
     * @param categories the collection of weapon categories to set.
     */
    public final void setWeaponCategories(
            final List<WeaponCategory> categories) {
        this.elements.clear();
        this.elements.addAll(categories);
    }

    /**
     * @param weaponCategoriesInput the weaponCategories to set
     */
    public final void add(final List<WeaponCategory> weaponCategoriesInput) {
        this.elements.addAll(weaponCategoriesInput);
    }

    /**
     * Tests if given weapon category is part of the collection.
     *
     * @param weaponCategory the weapon category in question.
     * @return true if part of the collection, false otherwise.
     */
    public final boolean contains(final WeaponCategory weaponCategory) {
        return this.elements.contains(weaponCategory);
    }
}
