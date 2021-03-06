package org.asciicerebrum.neocortexengine.domain.core.particles;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 *
 * @author species8472
 */
public class BooleanParticle {

    /**
     * Initial value for hash code calculation.
     */
    private static final int INITIAL_NON_ZERO_ODD_NUMBER = 17;

    /**
     * Modifier for hash code calculation.
     */
    private static final int MULTIPLIER_NON_ZERO_ODD_NUMBER = 31;

    /**
     * Wrapped primitive defaulting to false.
     */
    private boolean value = false;

    /**
     * Default constructor with false value.
     */
    public BooleanParticle() {

    }

    /**
     * Create instance from a boolean primitive.
     *
     * @param booleanValue the primitive to create the instance from.
     */
    public BooleanParticle(final boolean booleanValue) {
        this.value = booleanValue;
    }

    /**
     * Create instance from a Boolean object.
     *
     * @param booleanValue the object to create the instance from.
     */
    public BooleanParticle(final Boolean booleanValue) {
        if (booleanValue == null) {
            this.value = false;
        } else {
            this.value = booleanValue;
        }
    }

    /**
     * @return the value
     */
    public final boolean isValue() {
        return this.value;
    }

    /**
     * @param valueInput the value to set
     */
    public final void setValue(final boolean valueInput) {
        this.value = valueInput;
    }

    @Override
    public final boolean equals(final Object o) {
        if (!(o instanceof BooleanParticle)) {
            return false;
        }
        if (o == this) {
            return true;
        }
        BooleanParticle oParticle = (BooleanParticle) o;
        return new EqualsBuilder()
                .append(this.value, oParticle.value)
                .isEquals();
    }

    @Override
    public final int hashCode() {
        return new HashCodeBuilder(INITIAL_NON_ZERO_ODD_NUMBER,
                MULTIPLIER_NON_ZERO_ODD_NUMBER)
                .append(this.value)
                .toHashCode();
    }

    @Override
    public final String toString() {
        return Boolean.toString(this.isValue());
    }

}
