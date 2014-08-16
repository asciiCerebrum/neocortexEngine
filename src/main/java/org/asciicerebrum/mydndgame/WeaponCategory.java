package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.interfaces.entities.IWeaponCategory;

/**
 *
 * @author species8472
 */
public class WeaponCategory implements IWeaponCategory {

    /**
     * Unique id of the weapon category.
     */
    private String id;

    /**
     * {@inheritDoc}
     */
    @Override
    public final String getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setId(final String idInput) {
        this.id = idInput;
    }

}
