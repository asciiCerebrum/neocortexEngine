package org.asciicerebrum.mydndgame.domain.rules;

import org.asciicerebrum.mydndgame.domain.core.particles.GenericName;

/**
 *
 * @author species8472
 */
public abstract class GameEntity {

    /**
     * The generic name of the item. Does not need to be unique.
     */
    private GenericName genericName;

    /**
     * @return the genericName
     */
    public final GenericName getGenericName() {
        return genericName;
    }

    /**
     * @param genericNameInput the genericName to set
     */
    public final void setGenericName(final GenericName genericNameInput) {
        this.genericName = genericNameInput;
    }

}
