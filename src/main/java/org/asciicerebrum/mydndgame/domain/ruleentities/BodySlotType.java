package org.asciicerebrum.mydndgame.domain.ruleentities;

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
     * @param idInput the id to set
     */
    public final void setId(final UniqueId idInput) {
        this.id = idInput;
    }

}
