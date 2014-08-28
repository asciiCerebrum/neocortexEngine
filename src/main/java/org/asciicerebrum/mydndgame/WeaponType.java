package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.interfaces.entities.IWeaponType;

/**
 *
 * @author species8472
 */
public class WeaponType implements IWeaponType {

    /**
     * Unique id of the weapon type.
     */
    private String id;

    /**
     * @return the id
     */
    public final String getId() {
        return id;
    }

    /**
     * @param idInput the id to set
     */
    public final void setId(final String idInput) {
        this.id = idInput;
    }

}
