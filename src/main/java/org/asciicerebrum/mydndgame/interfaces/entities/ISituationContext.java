package org.asciicerebrum.mydndgame.interfaces.entities;

/**
 *
 * @author species8472
 */
public interface ISituationContext {

    /**
     *
     * @return the body slot type of the character.
     */
    IBodySlotType getBodySlotType();

    /**
     *
     * @param iBodySlotType the body slot type of the character.
     */
    void setBodySlotType(IBodySlotType iBodySlotType);

    /**
     * @return the attackMode
     */
    IWeaponCategory getAttackMode();

    /**
     * @param attackMode the attackMode to set
     */
    void setAttackMode(IWeaponCategory attackMode);
}
