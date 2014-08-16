package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.interfaces.entities.IProficiency;

/**
 *
 * @author species8472
 */
public class Proficiency implements IProficiency {

    /**
     * Unique id of the proficiency.
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
