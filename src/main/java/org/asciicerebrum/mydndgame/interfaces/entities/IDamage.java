package org.asciicerebrum.mydndgame.interfaces.entities;

/**
 *
 * @author species8472
 */
public interface IDamage {

    /**
     * @return the weapon
     */
    IWeapon getWeapon();

    /**
     * @param weapon the weapon to set
     */
    void setWeapon(IWeapon weapon);

    /**
     * @return the damageType
     */
    IDamageType getDamageType();

    /**
     * @param damageType the damageType to set
     */
    void setDamageType(IDamageType damageType);

    /**
     * @return the damageValue
     */
    Long getDamageValue();

    /**
     * @param damageValue the damageValue to set
     */
    void setDamageValue(Long damageValue);

}
