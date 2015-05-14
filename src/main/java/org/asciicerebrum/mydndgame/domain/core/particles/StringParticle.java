package org.asciicerebrum.mydndgame.domain.core.particles;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 *
 * @author species8472
 */
public abstract class StringParticle {

    /**
     * Initial value for hash code calculation.
     */
    private static final int INITIAL_NON_ZERO_ODD_NUMBER = 17;

    /**
     * Modifier for hash code calculation.
     */
    private static final int MULTIPLIER_NON_ZERO_ODD_NUMBER = 31;

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

    /**
     * Helper method for the equals method of the subclasses.
     *
     * @param o the object to compre.
     * @return true if equals, false otherwise.
     */
    protected final boolean equalsHelper(final Object o) {
        if (!(o instanceof StringParticle)) {
            return false;
        }
        if (o == this) {
            return true;
        }
        final StringParticle stringParticleO = (StringParticle) o;
        return new EqualsBuilder()
                .append(this.getValue(), stringParticleO.getValue())
                .isEquals();
    }

    /**
     * Helper method for the hash sum calculation of the subclasses.
     *
     * @return the hash sum of the object instance.
     */
    protected final int hashCodeHelper() {
        return new HashCodeBuilder(INITIAL_NON_ZERO_ODD_NUMBER,
                MULTIPLIER_NON_ZERO_ODD_NUMBER)
                .append(this.getValue())
                .hashCode();
    }

}
