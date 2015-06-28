package org.asciicerebrum.neocortexengine.domain.mechanics;

import org.asciicerebrum.neocortexengine.domain.core.particles.Stackability;
import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueId;

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
     * Determines, if boni of this type are stackable. Default is false as most
     * boni do not stack.
     */
    private Stackability doesStack = new Stackability(false);

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

    /**
     * @return the doesStack
     */
    public final Stackability getDoesStack() {
        return doesStack;
    }

    /**
     * @param doesStackInput the doesStack to set
     */
    public final void setDoesStack(final Stackability doesStackInput) {
        this.doesStack = doesStackInput;
    }

}
