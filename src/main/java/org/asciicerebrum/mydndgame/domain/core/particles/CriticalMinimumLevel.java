package org.asciicerebrum.mydndgame.domain.core.particles;

/**
 *
 * @author species8472
 */
public class CriticalMinimumLevel extends LongParticle {

    /**
     * Basic constructor for the long type.
     *
     * @param minLvlInput the critical minimum level.
     */
    public CriticalMinimumLevel(final long minLvlInput) {
        this.setValue(minLvlInput);
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
