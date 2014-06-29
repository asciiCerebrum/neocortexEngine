package org.asciicerebrum.mydndgame;

/**
 *
 * @author species8472
 */
public class Dice {

    /**
     * The unique id of this die type.
     */
    private String id;
    /**
     * The number of sides this die has.
     */
    private int sides;

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

    /**
     * @return the sides
     */
    public final int getSides() {
        return sides;
    }

    /**
     * @param sidesInput the sides to set
     */
    public final void setSides(final int sidesInput) {
        this.sides = sidesInput;
    }

}
