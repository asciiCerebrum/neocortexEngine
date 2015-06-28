package org.asciicerebrum.neocortexengine.domain.ruleentities;

import org.asciicerebrum.neocortexengine.domain.core.particles.DiceSides;
import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueId;

/**
 *
 * @author species8472
 */
public class Dice {

    /**
     * The unique id of this die type.
     */
    private UniqueId id;
    /**
     * The number of sides this die has.
     */
    private DiceSides sides;

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
     * @return the sides
     */
    public final DiceSides getSides() {
        return sides;
    }

    /**
     * @param sidesInput the sides to set
     */
    public final void setSides(final DiceSides sidesInput) {
        this.sides = sidesInput;
    }

}
