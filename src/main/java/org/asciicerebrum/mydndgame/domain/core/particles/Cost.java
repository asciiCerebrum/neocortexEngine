package org.asciicerebrum.mydndgame.domain.core.particles;

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

    @Override
    public final boolean equals(final Object o) {
        return this.equalsHelper(o);
    }

    @Override
    public final int hashCode() {
        return this.hashCodeHelper();
    }
}
