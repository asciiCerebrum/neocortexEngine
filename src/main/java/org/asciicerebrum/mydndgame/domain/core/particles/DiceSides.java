package org.asciicerebrum.mydndgame.domain.core.particles;

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

    @Override
    public final boolean equals(final Object o) {
        return this.equalsHelper(o);
    }

    @Override
    public final int hashCode() {
        return this.hashCodeHelper();
    }
}
