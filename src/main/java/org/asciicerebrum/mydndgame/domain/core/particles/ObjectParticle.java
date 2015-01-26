package org.asciicerebrum.mydndgame.domain.core.particles;

/**
 *
 * @author species8472
 * @param <T> the type of the object.
 */
public abstract class ObjectParticle<T> {

    private final T value;

    public ObjectParticle(final T valueInput) {
        this.value = valueInput;
    }

    /**
     * @return the value
     */
    public final T getValue() {
        return value;
    }

}
