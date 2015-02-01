package org.asciicerebrum.mydndgame.domain.rules.entities;

import org.asciicerebrum.mydndgame.domain.core.particles.DiceSides;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;

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
     * @return the sides
     */
    public DiceSides getSides() {
        return sides;
    }

    /**
     * @param sides the sides to set
     */
    public void setSides(DiceSides sides) {
        this.sides = sides;
    }

}
