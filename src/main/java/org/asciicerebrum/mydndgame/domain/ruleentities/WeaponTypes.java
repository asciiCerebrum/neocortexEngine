package org.asciicerebrum.mydndgame.domain.ruleentities;

import java.util.List;

/**
 *
 * @author species8472
 */
public class WeaponTypes {

    /**
     * List of weapon types.
     */
    private List<WeaponType> elements;

    /**
     * @return the weaponTypes
     */
    public final List<WeaponType> getWeaponTypes() {
        return elements;
    }

    /**
     * @param weaponTypesInput the weaponTypes to set
     */
    public final void setWeaponTypes(final List<WeaponType> weaponTypesInput) {
        this.elements = weaponTypesInput;
    }

    public final boolean contains(final WeaponType weaponType) {
        return this.elements.contains(weaponType);
    }

}
