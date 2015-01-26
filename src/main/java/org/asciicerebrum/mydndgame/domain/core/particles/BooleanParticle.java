package org.asciicerebrum.mydndgame.domain.core.particles;

/**
 *
 * @author species8472
 */
public class BooleanParticle {

    private boolean value = false;

    public BooleanParticle() {

    }

    public BooleanParticle(final boolean booleanValue) {
        this.value = booleanValue;
    }

    /**
     * @return the value
     */
    public boolean isValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(boolean value) {
        this.value = value;
    }

}
