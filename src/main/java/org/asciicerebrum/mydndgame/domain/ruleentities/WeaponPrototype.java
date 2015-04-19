package org.asciicerebrum.mydndgame.domain.ruleentities;

import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.particles.CriticalFactor;
import org.asciicerebrum.mydndgame.domain.core.particles.CriticalMinimumLevel;
import org.asciicerebrum.mydndgame.domain.core.particles.RangeIncrement;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.ContextBoni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.UniqueEntityResolver;

/**
 *
 * @author species8472
 */
public class WeaponPrototype extends InventoryItemPrototype
        implements FeatBinding {

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
     * Melee and/or ranged. Some weapons can be used in both ways (even without
     * penalty as an improvised weapon, e.g. dagger. So this should be the
     * default mode of operation. It should be a list because the dagger has two
     * default modes (like all melee weapons with a range increment!) while a
     * sword only has one.
     */
    private WeaponCategories defaultCategories;

    /**
     * Melee or ranged. Only one of the two is possible here as it is no list.
     * But the setting can be overridden with a setting in the situation context
     * registry.
     */
    private WeaponCategory defaultAttackMode;

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
    public final DamageTypes getDefaultDamageTypes() {
        return defaultDamgeTypes;
    }

    /**
     * @param defaultDamgeTypesInput the defaultDamgeTypes to set
     */
    public final void setDefaultDamageTypes(
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

    @Override
    public final ContextBoni getBoni(final UniqueEntity context,
            final UniqueEntityResolver resolver) {
        return this.getInventoryItemBoni(context, resolver);
    }

    /**
     * @return the defaultAttackMode
     */
    public final WeaponCategory getDefaultAttackMode() {
        return defaultAttackMode;
    }

    /**
     * @param defaultAttackModeInput the defaultAttackMode to set
     */
    public final void setDefaultAttackMode(
            final WeaponCategory defaultAttackModeInput) {
        this.defaultAttackMode = defaultAttackModeInput;
    }

}
