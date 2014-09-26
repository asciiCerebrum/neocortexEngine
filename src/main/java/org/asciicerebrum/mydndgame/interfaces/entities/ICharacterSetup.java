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
    String ACTIVE_BODY_SLOT_TYPE = "activeBodySlotType";

    /**
     * Defines the key for the active attack mode.
     */
    String ACTIVE_ATTACK_MODE = "activeAttackMode";

    /**
     * Defines the key for the active damage type selected for the weapon of
     * given id. It is followed by the weapons's id.
     */
    String WEAPON_DAMAGE_TYPE_PREFIX = "weaponDamageType.";

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

    /**
     * @return the currentHp
     */
    Long getCurrentHp();

    /**
     * @param currentHpInput the currentHp to set
     */
    void setCurrentHp(Long currentHpInput);
}
