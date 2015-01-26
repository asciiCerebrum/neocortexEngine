package org.asciicerebrum.mydndgame.domain.core.particles;

/**
 *
 * @author species8472
 */
public class Level extends LongParticle {

    public Level(final long valueInput) {
        this.setValue(valueInput);
    }

    public Level decrement() {
        return new Level(this.getValue() - 1);
    }

}
