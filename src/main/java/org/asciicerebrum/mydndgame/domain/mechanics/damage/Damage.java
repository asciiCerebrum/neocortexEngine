package org.asciicerebrum.mydndgame.domain.mechanics.damage;

import org.asciicerebrum.mydndgame.domain.core.particles.DiceRoll;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.domain.ruleentities.DamageType;

/**
 *
 * @author species8472
 */
public class Damage {

    /**
     * The weapon inflicting the damage. Useful e.g. when the target has an
     * immunity against certain types of weapons or materials like cold iron,
     * etc.
     */
    private Weapon weapon;

    /**
     * The type of damage that was inflicted with this weapon. E.g. skeletons
     * have a damage reduction 5/bludgeoning.
     */
    private DamageType damageType;

    /**
     * The numerical amount of inflicted damage.
     */
    private DiceRoll damageValue;

    /**
     * @return the damageType
     */
    public DamageType getDamageType() {
        return damageType;
    }

    /**
     * @param damageType the damageType to set
     */
    public void setDamageType(DamageType damageType) {
        this.damageType = damageType;
    }

    /**
     * @return the weapon
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
     * @param weapon the weapon to set
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    /**
     * @return the damageValue
     */
    public final DiceRoll getDamageValue() {
        return damageValue;
    }

    /**
     * @param damageValueInput the damageValue to set
     */
    public final void setDamageValue(final DiceRoll damageValueInput) {
        this.damageValue = damageValueInput;
    }

}
