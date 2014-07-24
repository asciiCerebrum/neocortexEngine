package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.interfaces.entities.IBodySlotType;

/**
 *
 * @author species8472
 */
public class BodySlotType implements IBodySlotType {

    /**
     * The unique id of this body slot type. E.g. primaryHand.
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
