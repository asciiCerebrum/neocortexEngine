package org.asciicerebrum.neocortexengine.domain.core.particles;

/**
 *
 * @author species8472
 */
public class Cost extends LongParticle {

    /**
     * Basic constructor for cost.
     *
     * @param costInput the numerical cost value.
     */
    public Cost(final long costInput) {
        this.setValue(costInput);
    }

    /**
     * Basic constructor for cost with default value.
     *
     */
    public Cost() {
    }

    @Override
    public final boolean equals(final Object o) {
        if (!(o instanceof Cost)) {
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
        return new Cost();
    }
}
