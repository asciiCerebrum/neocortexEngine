package org.asciicerebrum.mydndgame.domain.core.particles;

/**
 *
 * @author species8472
 */
public class Level extends LongParticle {

    /**
     * Creates an instance from a long primitive.
     *
     * @param valueInput the long to create the instance from.
     */
    public Level(final long valueInput) {
        this.setValue(valueInput);
    }

    /**
     * Creates an instance from a long primitive with a default value.
     *
     */
    public Level() {
    }

    /**
     * Creates a new level with a decremented value of the original instance.
     * The decrementation is 1.
     *
     * @return the new level with decremented value.
     */
    public final Level decrement() {
        return new Level(this.getValue() - 1);
    }

    @Override
    public final boolean equals(final Object o) {
        return this.equalsHelper(o);
    }

    @Override
    public final int hashCode() {
        return this.hashCodeHelper();
    }

    @Override
    public final LongParticle getNewInstanceOfSameType() {
        return new Level();
    }

}
