package org.asciicerebrum.mydndgame.domain.core.particles;

/**
 *
 * @author species8472
 */
public class Percentage extends LongParticle {

    /**
     * Constructs the percentage from a long.
     *
     * @param percentageLong the percentage as long.
     */
    public Percentage(final long percentageLong) {
        this.setValue(percentageLong);
    }

    /**
     * Constructs the percentage from a default value.
     *
     */
    public Percentage() {
    }

    @Override
    public final boolean equals(final Object o) {
        if (!(o instanceof Percentage)) {
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
        return new Percentage();
    }
}
