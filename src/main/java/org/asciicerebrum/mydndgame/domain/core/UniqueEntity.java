package org.asciicerebrum.mydndgame.domain.core;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.asciicerebrum.mydndgame.domain.mechanics.interfaces.BonusSource;
import org.asciicerebrum.mydndgame.domain.mechanics.ObserverSource;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;

/**
 *
 * @author species8472
 */
public abstract class UniqueEntity implements BonusSource, ObserverSource {

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
    public int hashCode() {
        return new HashCodeBuilder(17, 31)
                .append(this.getUniqueId())
                .hashCode();
    }

}
