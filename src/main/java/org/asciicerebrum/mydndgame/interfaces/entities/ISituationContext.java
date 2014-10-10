package org.asciicerebrum.mydndgame.interfaces.entities;

/**
 *
 * @author species8472
 */
public interface ISituationContext {

    /**
     * Assignes the situationn context to the character.
     *
     * @param character the character in question.
     */
    void setCharacter(ICharacter character);

    /**
     *
     * @return the body slot type of the character.
     */
    IBodySlotType getBodySlotType();

    /**
     * @return the attackMode
     */
    IWeaponCategory getAttackMode();

    /**
     * @return the damageType
     */
    IDamageType getDamageType();

}
