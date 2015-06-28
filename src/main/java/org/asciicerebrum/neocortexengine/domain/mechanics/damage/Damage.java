package org.asciicerebrum.neocortexengine.domain.mechanics.damage;

import org.asciicerebrum.neocortexengine.domain.core.particles.DiceRoll;
import org.asciicerebrum.neocortexengine.domain.game.Weapon;
import org.asciicerebrum.neocortexengine.domain.ruleentities.DamageType;

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
    public final DamageType getDamageType() {
        return damageType;
    }

    /**
     * @param damageTypeInput the damageType to set
     */
    public final void setDamageType(final DamageType damageTypeInput) {
        this.damageType = damageTypeInput;
    }

    /**
     * @return the weapon
     */
    public final Weapon getWeapon() {
        return weapon;
    }

    /**
     * @param weaponInput the weapon to set
     */
    public final void setWeapon(final Weapon weaponInput) {
        this.weapon = weaponInput;
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
