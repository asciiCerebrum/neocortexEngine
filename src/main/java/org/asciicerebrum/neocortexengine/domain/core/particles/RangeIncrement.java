package org.asciicerebrum.neocortexengine.domain.core.particles;

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

    /**
     * Basic constructor for the long type with a default value.
     *
     */
    public RangeIncrement() {
    }

    @Override
    public final boolean equals(final Object o) {
        if (!(o instanceof RangeIncrement)) {
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
        return new RangeIncrement();
    }
}
