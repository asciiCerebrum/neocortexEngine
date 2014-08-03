package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.interfaces.entities.IEncumbrance;

/**
 *
 * @author species8472
 */
public class Encumbrance implements IEncumbrance {

    /**
     * Unique id of the encumbrance.
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
