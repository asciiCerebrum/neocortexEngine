package org.asciicerebrum.neocortexengine.domain.core.particles;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 *
 * @author species8472
 */
public class UniqueId extends StringParticle {

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
     * @param uniqueId the string to create the instance from.
     */
    public UniqueId(final String uniqueId) {
        this.setValue(uniqueId);
    }

    @Override
    public final boolean equals(final Object o) {
        if (!(o instanceof UniqueId)) {
            return false;
        }
        if (o == this) {
            return true;
        }
        UniqueId uniqueO = (UniqueId) o;
        return new EqualsBuilder()
                .append(this.getValue(), uniqueO.getValue())
                .isEquals();
    }

    @Override
    public final int hashCode() {
        return new HashCodeBuilder(INITIAL_NON_ZERO_ODD_NUMBER,
                MULTIPLIER_NON_ZERO_ODD_NUMBER)
                .append(this.getValue())
                .hashCode();
    }

    @Override
    public final String toString() {
        return this.getValue();
    }
}
