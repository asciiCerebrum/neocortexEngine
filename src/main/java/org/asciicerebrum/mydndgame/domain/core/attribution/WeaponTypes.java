package org.asciicerebrum.mydndgame.domain.core.attribution;

import java.util.List;

/**
 *
 * @author species8472
 */
public class WeaponTypes {

    /**
     * List of weapon types.
     */
    private List<WeaponType> weaponTypes;

    /**
     * @return the weaponTypes
     */
    public final List<WeaponType> getWeaponTypes() {
        return weaponTypes;
    }

    /**
     * @param weaponTypesInput the weaponTypes to set
     */
    public final void setWeaponTypes(final List<WeaponType> weaponTypesInput) {
        this.weaponTypes = weaponTypesInput;
    }

    public final boolean contains(final WeaponType weaponType) {
        return this.weaponTypes.contains(weaponType);
    }

}
