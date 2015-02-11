package org.asciicerebrum.mydndgame.domain.core.particles;

/**
 *
 * @author species8472
 */
public class CriticalMinimumLevel extends LongParticle {

    @Override
    public final boolean equals(final Object o) {
        return this.equalsHelper(o);
    }

    @Override
    public final int hashCode() {
        return this.hashCodeHelper();
    }
}
