package org.asciicerebrum.mydndgame.domain.core.particles;

/**
 *
 * @author species8472
 */
public class CriticalFactor extends LongParticle {

    /**
     * Basic constructor for the long type.
     *
     * @param critFactorInput the critical factor.
     */
    public CriticalFactor(final long critFactorInput) {
        this.setValue(critFactorInput);
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
