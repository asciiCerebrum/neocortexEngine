package org.asciicerebrum.mydndgame.domain.core.particles;

/**
 *
 * @author species8472
 */
public class DiceNumber extends LongParticle {

    /**
     * Creates a dice number instance from a long primitive.
     *
     * @param longValue the long to create the dice number from.
     */
    public DiceNumber(final long longValue) {
        this.setValue(longValue);
    }

    /**
     * Creates a dice number instance from a long primitive with default value.
     *
     */
    public DiceNumber() {
    }

    @Override
    public final boolean equals(final Object o) {
        return this.equalsHelper(o);
    }

    @Override
    public final int hashCode() {
        return this.hashCodeHelper();
    }

    @Override
    public final LongParticle getNewInstanceOfSameType() {
        return new DiceNumber();
    }
}
