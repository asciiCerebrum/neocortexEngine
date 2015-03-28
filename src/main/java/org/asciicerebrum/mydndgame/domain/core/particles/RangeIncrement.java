package org.asciicerebrum.mydndgame.domain.core.particles;

/**
 *
 * @author species8472
 */
public class RangeIncrement extends LongParticle {

    /**
     * Basic constructor for the long type.
     *
     * @param rangeIncrementInput the range increment.
     */
    public RangeIncrement(final long rangeIncrementInput) {
        this.setValue(rangeIncrementInput);
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
