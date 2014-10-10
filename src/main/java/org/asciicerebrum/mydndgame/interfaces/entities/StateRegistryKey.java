package org.asciicerebrum.mydndgame.interfaces.entities;

/**
 *
 * @author species8472
 */
public enum StateRegistryKey {

    /**
     * Defines the key for the active body slot type.
     */
    ACTIVE_BODY_SLOT_TYPE,
    /**
     * Defines the key for the active attack mode.
     */
    ACTIVE_ATTACK_MODE,
    /**
     * Defines the key for the active damage type selected for the weapon of
     * given id. It is followed by the weapons's id.
     */
    WEAPON_DAMAGE_TYPE_PREFIX {

                /**
                 * Adding a separator as it is a prefix.
                 *
                 * @return the separated string.
                 */
                @Override
                public final String toString() {
                    return super.toString() + PREFIX_SEPARATOR;
                }
            };

    /**
     * For separating prefix enums.
     */
    private static final String PREFIX_SEPARATOR = ".";

}
