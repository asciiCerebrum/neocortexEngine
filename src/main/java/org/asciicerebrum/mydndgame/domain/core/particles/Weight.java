package org.asciicerebrum.mydndgame.domain.core.particles;

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

    @Override
    public final boolean equals(final Object o) {
        return this.equalsHelper(o);
    }

    @Override
    public final int hashCode() {
        return this.hashCodeHelper();
    }
}
