package org.asciicerebrum.mydndgame.interfaces.entities;

import java.util.Map;

/**
 *
 * @author Tabea Raab
 */
public interface ICharacterSetup {

    /**
     * @return the stateRegistry.
     */
    Map<String, Object> getStateRegistry();

    /**
     * Retrieves the bean, if possible, for a given ID found in the state
     * registry for the given key string.
     *
     * @param <T> type of the bean needed.
     * @param registryKey the key to find an entry for.
     * @param beanClass the class of the bean in question.
     * @return the object if found.
     */
    <T> T getStateRegistryBeanForKey(String registryKey, Class<T> beanClass);

    /**
     * @return the currentHp
     */
    Long getCurrentHp();

    /**
     * @param currentHpInput the currentHp to set
     */
    void setCurrentHp(Long currentHpInput);

    /**
     * Add attributes as key-value string pairs to the registry overlay. These
     * values are used for faking the real ones. They have priority!
     *
     * @param key the attribute to fake.
     * @param value the new value overriding the old one.
     */
    void fakeAttribute(String key, String value);

    /**
     * Remove all key-value entries from the registry overlay.
     */
    void clearFakes();
}
