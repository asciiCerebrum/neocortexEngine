package org.asciicerebrum.neocortexengine.domain.core.particles;

/**
 *
 * @author species8472
 */
public class EventFact extends StringParticle {

    /**
     * Creates an instance from a string.
     *
     * @param eventFactInput the string to create the instance from.
     */
    public EventFact(final String eventFactInput) {
        this.setValue(eventFactInput);
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof EventFact)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        return this.equalsHelper(obj);
    }

    @Override
    public final int hashCode() {
        return this.hashCodeHelper();
    }

}
