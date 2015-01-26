package org.asciicerebrum.mydndgame.domain.core.particles;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

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
    public boolean equals(Object o) {
        if (!(o instanceof StringParticle)) {
            return false;
        }
        if (o == this) {
            return true;
        }
        StringParticle oParticle = (StringParticle) o;
        return new EqualsBuilder()
                .append(value, oParticle.value)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31)
                .append(value)
                .toHashCode();
    }

}
