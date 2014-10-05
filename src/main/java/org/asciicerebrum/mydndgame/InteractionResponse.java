package org.asciicerebrum.mydndgame;

import java.util.EnumMap;
import java.util.Map;
import org.asciicerebrum.mydndgame.interfaces.entities.IInteractionResponse;
import org.asciicerebrum.mydndgame.interfaces.entities.InteractionResponseKey;

/**
 *
 * @author species8472
 */
public class InteractionResponse implements IInteractionResponse {

    /**
     * Map that saves results from workflows.
     */
    private Map<InteractionResponseKey, Object> responseMap
            = new EnumMap<InteractionResponseKey, Object>(
                    InteractionResponseKey.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public final <T> T getValue(final InteractionResponseKey key,
            final Class<T> clazz) {
        return (T) this.responseMap.get(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setValue(final InteractionResponseKey key,
            final Object value) {
        this.responseMap.put(key, value);
    }

}
