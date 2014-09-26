package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.interfaces.entities.IDamage;
import org.asciicerebrum.mydndgame.interfaces.entities.IDamageType;
import org.asciicerebrum.mydndgame.interfaces.entities.IWeapon;

/**
 *
 * @author species8472
 */
public class Damage implements IDamage {

    /**
     * The weapon inflicting the damage. Useful e.g. when the target has an
     * immunity against certain types of weapons or materials like cold iron,
     * etc.
     */
    private IWeapon weapon;

    /**
     * The type of damage that was inflicted with this weapon. E.g. skeletons
     * have a damage reduction 5/bludgeoning.
     */
    private IDamageType damageType;

    /**
     * The numerical amount of inflicted damage.
     */
    private Long damageValue;

    /**
     * {@inheritDoc}
     */
    @Override
    public final IWeapon getWeapon() {
        return weapon;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setWeapon(final IWeapon weaponInput) {
        this.weapon = weaponInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final IDamageType getDamageType() {
        return damageType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setDamageType(final IDamageType damageTypeInput) {
        this.damageType = damageTypeInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Long getDamageValue() {
        return damageValue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setDamageValue(final Long damageValueInput) {
        this.damageValue = damageValueInput;
    }

}
