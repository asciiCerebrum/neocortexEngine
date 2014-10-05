package org.asciicerebrum.mydndgame.interfaces.entities;

/**
 *
 * @author species8472
 */
public interface IInteractionResponse {

    /**
     * Returns the value of the associated key. It's a simple mapping mechanism.
     *
     * @param key The key to the result of a workflow.
     * @param <T> the type of the class.
     * @param clazz the class of the returned object.
     * @return the result itself.
     */
    <T> T getValue(InteractionResponseKey key, Class<T> clazz);

    /**
     * Saves the key-value-pair in the map.
     *
     * @param key the key to save the value for.
     * @param value the value to be saved.
     */
    void setValue(InteractionResponseKey key, Object value);

}
