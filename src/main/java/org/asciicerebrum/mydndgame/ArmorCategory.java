package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.interfaces.entities.IArmorCategory;

/**
 *
 * @author species8472
 */
public class ArmorCategory implements IArmorCategory {

    /**
     * Unique id of the armor category.
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
