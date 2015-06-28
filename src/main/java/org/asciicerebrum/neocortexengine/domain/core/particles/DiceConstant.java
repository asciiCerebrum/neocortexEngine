package org.asciicerebrum.neocortexengine.domain.core.particles;

/**
 *
 * @author species8472
 */
public class DiceConstant extends LongParticle {

    /**
     * Creates an instance from a long primitive.
     *
     * @param longInput the long to create the dice constant from.
     */
    public DiceConstant(final long longInput) {
        this.setValue(longInput);
    }

    /**
     * Creates an instance from a long primitive with default value.
     *
     */
    public DiceConstant() {
    }

    @Override
    public final boolean equals(final Object o) {
        if (!(o instanceof DiceConstant)) {
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
        return new DiceConstant();
    }
}
