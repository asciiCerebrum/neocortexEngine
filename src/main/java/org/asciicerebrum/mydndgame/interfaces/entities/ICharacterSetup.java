package org.asciicerebrum.mydndgame.interfaces.entities;

import java.util.Map;

/**
 *
 * @author Tabea Raab
 */
public interface ICharacterSetup {

    /**
     * Defines the key for the active body slot type.
     */
    static final String ACTIVE_BODY_SLOT_TYPE = "activeBodySlotType";

    /**
     * Defines the key for the active attack mode.
     */
    static final String ACTIVE_ATTACK_MODE = "activeAttackMode";

    /**
     *
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

}
