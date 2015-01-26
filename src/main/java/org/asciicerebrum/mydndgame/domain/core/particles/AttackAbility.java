package org.asciicerebrum.mydndgame.domain.core.particles;

/**
 *
 * @author species8472
 */
public class AttackAbility extends BooleanParticle {

    public AttackAbility(final boolean value) {
        this.setValue(value);
    }

    public AttackAbility(final String stringValue) {
        this.setValue(stringValue);
    }

    public final void setValue(final String stringValue) {
        this.setValue(Boolean.parseBoolean(stringValue));
    }

    public final AttackAbility getClone() {
        return new AttackAbility(this.isValue());
    }
}
