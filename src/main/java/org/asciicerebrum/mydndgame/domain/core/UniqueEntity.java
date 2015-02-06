package org.asciicerebrum.mydndgame.domain.core;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;

/**
 *
 * @author species8472
 */
public abstract class UniqueEntity {

    /**
     * Initial value for hash code calculation.
     */
    private static final int INITIAL_NON_ZERO_ODD_NUMBER = 17;

    /**
     * Modifier for hash code calculation.
     */
    private static final int MULTIPLIER_NON_ZERO_ODD_NUMBER = 31;

    /**
     * The id that makes it unique.
     */
    private UniqueId uniqueId;

    /**
     * @return the uniqueId
     */
    public final UniqueId getUniqueId() {
        return uniqueId;
    }

    /**
     * @param uniqueIdInput the uniqueId to set
     */
    public final void setUniqueId(final UniqueId uniqueIdInput) {
        this.uniqueId = uniqueIdInput;
    }

    @Override
    public final boolean equals(final Object o) {
        if (!(o instanceof UniqueEntity)) {
            return false;
        }
        if (o == this) {
            return true;
        }
        UniqueEntity uniqueO = (UniqueEntity) o;
        return new EqualsBuilder()
                .append(this.getUniqueId(), uniqueO.getUniqueId())
                .isEquals();
    }

    @Override
    public final int hashCode() {
        return new HashCodeBuilder(INITIAL_NON_ZERO_ODD_NUMBER,
                MULTIPLIER_NON_ZERO_ODD_NUMBER)
                .append(this.getUniqueId())
                .hashCode();
    }

}
