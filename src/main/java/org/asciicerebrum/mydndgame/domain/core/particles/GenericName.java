package org.asciicerebrum.mydndgame.domain.core.particles;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 *
 * @author species8472
 */
public class GenericName extends StringParticle {

    /**
     * Initial value for hash code calculation.
     */
    private static final int INITIAL_NON_ZERO_ODD_NUMBER = 17;

    /**
     * Modifier for hash code calculation.
     */
    private static final int MULTIPLIER_NON_ZERO_ODD_NUMBER = 31;

    /**
     * Creates an instance from a string.
     *
     * @param genericNameInput the string to create the instance from.
     */
    public GenericName(final String genericNameInput) {
        this.setValue(genericNameInput);
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof GenericName)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        GenericName oName = (GenericName) obj;
        return new EqualsBuilder()
                .append(this.getValue(), oName.getValue())
                .isEquals();
    }

    @Override
    public final int hashCode() {
        return new HashCodeBuilder(INITIAL_NON_ZERO_ODD_NUMBER,
                MULTIPLIER_NON_ZERO_ODD_NUMBER)
                .append(this.getValue())
                .toHashCode();
    }

}
