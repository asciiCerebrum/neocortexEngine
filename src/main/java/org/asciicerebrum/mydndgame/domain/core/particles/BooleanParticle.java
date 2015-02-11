package org.asciicerebrum.mydndgame.domain.core.particles;

/**
 *
 * @author species8472
 */
public class BooleanParticle {

    /**
     * Wrapped primitive defaulting to false.
     */
    private boolean value = false;

    /**
     * Default constructor with false value.
     */
    public BooleanParticle() {

    }

    /**
     * Create instance from a boolean primitive.
     *
     * @param booleanValue the primitive to create the instance from.
     */
    public BooleanParticle(final boolean booleanValue) {
        this.value = booleanValue;
    }

    /**
     * @return the value
     */
    public final boolean isValue() {
        return this.value;
    }

    /**
     * @param valueInput the value to set
     */
    public final void setValue(final boolean valueInput) {
        this.value = valueInput;
    }

}
