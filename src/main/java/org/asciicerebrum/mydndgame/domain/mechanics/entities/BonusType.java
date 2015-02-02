package org.asciicerebrum.mydndgame.domain.mechanics.entities;

import org.asciicerebrum.mydndgame.domain.core.particles.Stackability;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;

/**
 *
 * @author species8472
 */
public class BonusType {

    /**
     * The unique id of the bonus type.
     */
    private UniqueId id;

    /**
     * Determines, if boni of this type are stackable.
     */
    private Stackability doesStack;

    /**
     * @return the id
     */
    public UniqueId getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(UniqueId id) {
        this.id = id;
    }

    /**
     * @return the doesStack
     */
    public Stackability getDoesStack() {
        return doesStack;
    }

    /**
     * @param doesStack the doesStack to set
     */
    public void setDoesStack(Stackability doesStack) {
        this.doesStack = doesStack;
    }

}
