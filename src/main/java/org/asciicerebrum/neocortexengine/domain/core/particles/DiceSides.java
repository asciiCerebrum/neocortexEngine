package org.asciicerebrum.neocortexengine.domain.core.particles;

/**
 *
 * @author species8472
 */
public class DiceSides extends LongParticle {

    /**
     * Creates the dice sides instance from a long primitive.
     *
     * @param longValue the long to create the instance from.
     */
    public DiceSides(final long longValue) {
        this.setValue(longValue);
    }

    /**
     * Creates the dice sides instance from a long primitive with default value.
     *
     */
    public DiceSides() {
    }

    /**
     * Creates the instance from a string.
     *
     * @param longString the string.
     */
    public DiceSides(final String longString) {
        this.setValue(longString);
    }

    /**
     * Sets the value from a string.
     *
     * @param longString the string.
     */
    public final void setValue(final String longString) {
        super.setValue(Long.parseLong(longString));
    }

    @Override
    public final boolean equals(final Object o) {
        if (!(o instanceof DiceSides)) {
            return false;
        }
        return this.equalsHelper(o);
    }

    @Override
    public final int hashCode() {
        return this.hashCodeHelper();
    }

    @Override
    public final LongParticle getNewInstanceOfSameType() {
        return new DiceSides();
    }
}
