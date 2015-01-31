package org.asciicerebrum.mydndgame.domain.rules.entities;

import org.asciicerebrum.mydndgame.domain.rules.entities.DamageTypes;
import org.asciicerebrum.mydndgame.domain.rules.entities.Encumbrance;
import org.asciicerebrum.mydndgame.domain.rules.entities.Proficiency;
import org.asciicerebrum.mydndgame.domain.rules.entities.WeaponCategories;
import org.asciicerebrum.mydndgame.domain.rules.entities.WeaponTypes;
import org.asciicerebrum.mydndgame.domain.core.particles.CriticalFactor;
import org.asciicerebrum.mydndgame.domain.core.particles.CriticalMinimumLevel;
import org.asciicerebrum.mydndgame.domain.core.particles.RangeIncrement;

/**
 *
 * @author species8472
 */
public class WeaponPrototype extends InventoryItemPrototype {

    /**
     * Factor for damage rolls after a successful criticial hit.
     */
    private CriticalFactor criticalFactor;

    /**
     * Defines when the dice throw is considered critical.
     */
    private CriticalMinimumLevel criticalMinimumLevel;

    /**
     * At what distance is there a stacking malus. In ft.!
     */
    private RangeIncrement rangeIncrement;

    /**
     * The damage dice throw.
     */
    private DiceAction baseDamage;

    /**
     * Bludgeoning, piercing or slashing. ATTENTION: They can have AND / OR
     * conjunctions. E.g. a morningstar is bludgeoning and piercing while a
     * dagger is piercing or slashing. The list contains all OR-alternatives.
     * The AND-variants are combined within the damage type itself -> see
     * compoundDamageTypes.
     */
    private DamageTypes defaultDamgeTypes;

    /**
     * Unarmed, light, one-handed or two-handed.
     */
    private Encumbrance encumbrance;

    /**
     * Simple, martial or exotic.
     */
    private Proficiency proficiency;

    /**
     * Melee or ranged.
     */
    //TODO some weapons can be used in both ways (even without penalty as an
    // improvised weapon, e.g. dagger. So this should be the default mode of
    // operation. It should be a list because the dagger has two default modes
    // (like all melee weapons with a range increment!)
    // while a sword only has one.
    private WeaponCategories defaultCategories;

    /**
     * A list of types this weapon represents. E.g. double weapon, thrown
     * weapon, projectile, ammunition, etc.
     */
    private WeaponTypes weaponTypes;

    /**
     * @return the criticalFactor
     */
    public final CriticalFactor getCriticalFactor() {
        return criticalFactor;
    }

    /**
     * @param criticalFactorInput the criticalFactor to set
     */
    public final void setCriticalFactor(
            final CriticalFactor criticalFactorInput) {
        this.criticalFactor = criticalFactorInput;
    }

    /**
     * @return the criticalMinimumLevel
     */
    public final CriticalMinimumLevel getCriticalMinimumLevel() {
        return criticalMinimumLevel;
    }

    /**
     * @param criticalMinimumLevelInput the criticalMinimumLevel to set
     */
    public final void setCriticalMinimumLevel(
            final CriticalMinimumLevel criticalMinimumLevelInput) {
        this.criticalMinimumLevel = criticalMinimumLevelInput;
    }

    /**
     * @return the rangeIncrement
     */
    public final RangeIncrement getRangeIncrement() {
        return rangeIncrement;
    }

    /**
     * @param rangeIncrementInput the rangeIncrement to set
     */
    public final void setRangeIncrement(
            final RangeIncrement rangeIncrementInput) {
        this.rangeIncrement = rangeIncrementInput;
    }

    /**
     * @return the baseDamage
     */
    public final DiceAction getBaseDamage() {
        return baseDamage;
    }

    /**
     * @param baseDamageInput the baseDamage to set
     */
    public final void setBaseDamage(final DiceAction baseDamageInput) {
        this.baseDamage = baseDamageInput;
    }

    /**
     * @return the defaultDamgeTypes
     */
    public final DamageTypes getDefaultDamgeTypes() {
        return defaultDamgeTypes;
    }

    /**
     * @param defaultDamgeTypesInput the defaultDamgeTypes to set
     */
    public final void setDefaultDamgeTypes(
            final DamageTypes defaultDamgeTypesInput) {
        this.defaultDamgeTypes = defaultDamgeTypesInput;
    }

    /**
     * @return the encumbrance
     */
    public final Encumbrance getEncumbrance() {
        return encumbrance;
    }

    /**
     * @param encumbranceInput the encumbrance to set
     */
    public final void setEncumbrance(final Encumbrance encumbranceInput) {
        this.encumbrance = encumbranceInput;
    }

    /**
     * @return the proficiency
     */
    public final Proficiency getProficiency() {
        return proficiency;
    }

    /**
     * @param proficiencyInput the proficiency to set
     */
    public final void setProficiency(final Proficiency proficiencyInput) {
        this.proficiency = proficiencyInput;
    }

    /**
     * @return the defaultCategories
     */
    public final WeaponCategories getDefaultCategories() {
        return defaultCategories;
    }

    /**
     * @param defaultCategoriesInput the defaultCategories to set
     */
    public final void setDefaultCategories(
            final WeaponCategories defaultCategoriesInput) {
        this.defaultCategories = defaultCategoriesInput;
    }

    /**
     * @return the weaponTypes
     */
    public final WeaponTypes getWeaponTypes() {
        return weaponTypes;
    }

    /**
     * @param weaponTypesInput the weaponTypes to set
     */
    public final void setWeaponTypes(final WeaponTypes weaponTypesInput) {
        this.weaponTypes = weaponTypesInput;
    }

}
