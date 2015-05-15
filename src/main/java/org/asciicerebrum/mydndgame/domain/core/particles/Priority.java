package org.asciicerebrum.mydndgame.domain.core.particles;

/**
 *
 * @author species8472
 */
public class Priority extends LongParticle {

    @Override
    public final boolean equals(final Object o) {
        if (!(o instanceof Priority)) {
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
        return new Priority();
    }
}
