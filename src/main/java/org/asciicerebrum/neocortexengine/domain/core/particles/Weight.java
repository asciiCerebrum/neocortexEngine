package org.asciicerebrum.neocortexengine.domain.core.particles;

/**
 *
 * @author species8472
 */
public class Weight extends LongParticle {

    /**
     * Basic constructor for weight.
     *
     * @param weightInput the numerical weight value.
     */
    public Weight(final long weightInput) {
        this.setValue(weightInput);
    }

    /**
     * Basic constructor for weight with a default value.
     *
     */
    public Weight() {
    }

    @Override
    public final boolean equals(final Object o) {
        if (!(o instanceof Weight)) {
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
        return new Weight();
    }
}
