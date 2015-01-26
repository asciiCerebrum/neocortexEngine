package org.asciicerebrum.mydndgame.domain.core.attribution;

import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;

/**
 *
 * @author species8472
 */
public class BodySlotType {

    /**
     * The unique id of this body slot type. E.g. primaryHand.
     */
    private UniqueId id;

    /**
     * @return the id
     */
    public final UniqueId getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public final void setId(final UniqueId id) {
        this.id = id;
    }

}
