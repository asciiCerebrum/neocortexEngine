package org.asciicerebrum.mydndgame.domain.ruleentities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author species8472
 */
public class WeaponTypes {

    /**
     * List of weapon types.
     */
    private final List<WeaponType> elements = new ArrayList<WeaponType>();

    /**
     * @return the weaponTypes
     */
    public final List<WeaponType> getWeaponTypes() {
        return elements;
    }

    /**
     * @param weaponTypesInput the weaponTypes to set
     */
    public final void add(final List<WeaponType> weaponTypesInput) {
        this.elements.addAll(weaponTypesInput);
    }

    /**
     * Tests if given weapon type is part of the collection.
     *
     * @param weaponType the weapon type in question.
     * @return true if part of the collection, false otherwise.
     */
    public final boolean contains(final WeaponType weaponType) {
        return this.elements.contains(weaponType);
    }

}
