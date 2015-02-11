package org.asciicerebrum.mydndgame.domain.core.particles;

/**
 *
 * @author species8472
 */
public abstract class StringParticle {

    /**
     * The value of this string instance.
     */
    private String value;

    /**
     * @return the value
     */
    public final String getValue() {
        return value;
    }

    /**
     * @param valueInput the value to set
     */
    public final void setValue(final String valueInput) {
        this.value = valueInput;
    }

    @Override
    public abstract boolean equals(final Object o);

    @Override
    public abstract int hashCode();

}
