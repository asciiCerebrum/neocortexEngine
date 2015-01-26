package org.asciicerebrum.mydndgame.domain.core.particles;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *
 * @author species8472
 */
public class UniqueId extends StringParticle {

    public UniqueId(final String uniqueId) {
        this.setValue(uniqueId);
    }

    @Override
    public boolean equals(final Object o) {
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
    public int hashCode() {
        return new HashCodeBuilder(17, 31)
                .append(this.getValue())
                .hashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append(this.getValue()).toString();
    }
}
