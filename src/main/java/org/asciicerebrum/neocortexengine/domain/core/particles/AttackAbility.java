package org.asciicerebrum.neocortexengine.domain.core.particles;

import org.apache.commons.lang3.BooleanUtils;

/**
 *
 * @author species8472
 */
public class AttackAbility extends BooleanParticle {

    /**
     * Constructs this ability from a boolean value.
     *
     * @param value the boolean value.
     */
    public AttackAbility(final boolean value) {
        this.setValue(value);
    }

    /**
     * Constructs this ability from a string value.
     *
     * @param stringValue the string value.
     */
    public AttackAbility(final String stringValue) {
        this.setValue(stringValue);
    }

    /**
     *
     * @param stringValue the value as string.
     */
    public final void setValue(final String stringValue) {
        this.setValue(BooleanUtils.toBoolean(stringValue));
    }

    /**
     * Generates a new instance with the same value. It is a clone.
     *
     * @return the newly generated instance.
     */
    public final AttackAbility getClone() {
        return new AttackAbility(this.isValue());
    }
}
