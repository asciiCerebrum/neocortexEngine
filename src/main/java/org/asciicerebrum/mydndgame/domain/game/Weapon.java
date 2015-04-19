package org.asciicerebrum.mydndgame.domain.game;

import org.asciicerebrum.mydndgame.domain.core.particles.CriticalFactor;
import org.asciicerebrum.mydndgame.domain.core.particles.CriticalMinimumLevel;
import org.asciicerebrum.mydndgame.domain.ruleentities.DamageType;
import org.asciicerebrum.mydndgame.domain.ruleentities.DiceAction;
import org.asciicerebrum.mydndgame.domain.ruleentities.Encumbrance;
import org.asciicerebrum.mydndgame.domain.ruleentities.Proficiency;
import org.asciicerebrum.mydndgame.domain.ruleentities.WeaponCategories;
import org.asciicerebrum.mydndgame.domain.ruleentities.WeaponCategory;
import org.asciicerebrum.mydndgame.domain.ruleentities.WeaponPrototype;
import org.asciicerebrum.mydndgame.domain.ruleentities.WeaponTypes;

/**
 *
 * @author species8472
 */
public class Weapon extends InventoryItem {

    /**
     * @return the weapon prototype.
     */
    public final WeaponPrototype getWeaponPrototype() {
        return (WeaponPrototype) super.getInventoryItemPrototype();
    }

    /**
     * Tests if weapon originates from given prototype.
     *
     * @param weaponPrototype the prototype in question.
     * @return true if weapon is of that prototype, false otherwise.
     */
    public final boolean isOfWeaponPrototype(
            final WeaponPrototype weaponPrototype) {
        return this.getWeaponPrototype().equals(weaponPrototype);
    }

    /**
     * @return the basic unmodified categories.
     */
    public final WeaponCategories getBaseCategories() {
        return this.getWeaponPrototype().getDefaultCategories();
    }

    /**
     * @return the default unmodified damage types.
     */
    public final DamageType getDefaultDamageType() {
        // the first from all the or-connected alternatives.
        return this.getWeaponPrototype().getDefaultDamageTypes()
                .getFirst();
    }

    /**
     * @return the default attack mode of the weapon (as a fallback).
     */
    public final WeaponCategory getDefaultAttackMode() {
        return this.getWeaponPrototype().getDefaultAttackMode();
    }

    /**
     * @return the unmodified base proficiency.
     */
    public final Proficiency getBaseProficiency() {
        return this.getWeaponPrototype().getProficiency();
    }

    /**
     * @return the unmodified base encumbrance.
     */
    public final Encumbrance getBaseEncumbrance() {
        return this.getWeaponPrototype().getEncumbrance();
    }

    /**
     * @return the unmodified base weapon types.
     */
    public final WeaponTypes getBaseWeaponTypes() {
        return this.getWeaponPrototype().getWeaponTypes();
    }

    /**
     * @return the unmodified base critical minimum level.
     */
    public final CriticalMinimumLevel getBaseCriticalMinimumLevel() {
        return this.getWeaponPrototype().getCriticalMinimumLevel();
    }

    /**
     * @return the unmodified base critical factor.
     */
    public final CriticalFactor getBaseCriticalFactor() {
        return this.getWeaponPrototype().getCriticalFactor();
    }

    /**
     * @return the unmodified base damage.
     */
    public final DiceAction getBaseDamage() {
        return this.getWeaponPrototype().getBaseDamage();
    }

}
