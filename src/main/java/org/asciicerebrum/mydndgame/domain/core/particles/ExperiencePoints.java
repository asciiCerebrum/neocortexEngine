package org.asciicerebrum.mydndgame.domain.core.particles;

/**
 *
 * @author species8472
 */
public class ExperiencePoints extends LongParticle {

    /**
     * Creates the instance from a string.
     *
     * @param xpString the string.
     */
    public ExperiencePoints(final String xpString) {
        this.setValue(xpString);
    }

    /**
     * Creates the instance with a default value.
     *
     */
    public ExperiencePoints() {
    }

    /**
     * Sets the value from a string.
     *
     * @param xpString the string.
     */
    public final void setValue(final String xpString) {
        super.setValue(Long.parseLong(xpString));
    }

    @Override
    public final boolean equals(final Object o) {
        return this.equalsHelper(o);
    }

    @Override
    public final int hashCode() {
        return this.hashCodeHelper();
    }

    @Override
    public final LongParticle getNewInstanceOfSameType() {
        return new ExperiencePoints();
    }
}
