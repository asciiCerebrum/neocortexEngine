package org.asciicerebrum.mydndgame;

import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.mydndgame.interfaces.entities.IEncumbrance;
import org.asciicerebrum.mydndgame.interfaces.entities.IProficiency;
import org.asciicerebrum.mydndgame.interfaces.entities.IWeapon;
import org.asciicerebrum.mydndgame.interfaces.entities.IWeaponCategory;

/**
 *
 * @author species8472
 */
public class Weapon extends InventoryItem implements IWeapon {

    /**
     * The damage dice throw.
     */
    private DiceAction damage;

    /**
     * Factor for damage rolls after a successful criticial hit.
     */
    private Integer criticalFactor;

    /**
     * Defines when the dice throw is considered critical.
     */
    private Integer criticalMinimumLevel;

    /**
     * At what distance is there a stacking malus. In ft.!
     */
    private Integer rangeIncrement;

    /**
     * Bludgeoning, piercing or slashing. ATTENTION: They can have AND / OR
     * conjunctions. E.g. a morningstar is bludgeoning and piercing while a
     * dagger is piercing or slashing. The list contains all OR-alternatives.
     * The AND-variants are combined within the damage type itself -> see
     * compoundDamageTypes.
     */
    private List<DamageType> defaultDamageTypes;

    /**
     * Unarmed, light, one-handed or two-handed.
     */
    private IEncumbrance encumbrance;

    /**
     * Simple, martial or exotic.
     */
    private IProficiency proficiency;

    /**
     * Melee or ranged.
     */
    //TODO some weapons can be used in both ways (even without penalty as an
    // improvised weapon, e.g. dagger. So this should be the default mode of
    // operation. It should be a list because the dagger has two default modes
    // (like all melee weapons with a range increment!)
    // while a sword only has one.
    private List<IWeaponCategory> defaultCategories
            = new ArrayList<IWeaponCategory>();

    /**
     * A list of types this weapon represents. E.g. double weapon, thrown
     * weapon, projectile, ammunition, etc.
     */
    private List<WeaponType> weaponTypes;

    /**
     * For which kinds of body slots this weapon is intendet.
     */
    private List<BodySlotType> designatedBodySlots;

    /**
     * @return the designatedBodySlots
     */
    public final List<BodySlotType> getDesignatedBodySlots() {
        return designatedBodySlots;
    }

    /**
     * @param designatedBodySlotsInput the designatedBodySlots to set
     */
    public final void setDesignatedBodySlots(
            final List<BodySlotType> designatedBodySlotsInput) {
        this.designatedBodySlots = designatedBodySlotsInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<IWeaponCategory> getDefaultCategories() {
        return defaultCategories;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setDefaultCategories(
            final List<IWeaponCategory> defaultCategoriesInput) {
        this.defaultCategories = defaultCategoriesInput;
    }

    /**
     * @return the defaultDamageTypes
     */
    public final List<DamageType> getDefaultDamageTypes() {
        return defaultDamageTypes;
    }

    /**
     * @param defaultDamageTypesInput the defaultDamageTypes to set
     */
    public final void setDefaultDamageTypes(
            final List<DamageType> defaultDamageTypesInput) {
        this.defaultDamageTypes = defaultDamageTypesInput;
    }

    /**
     * Adjust attributes of this weapon according to the new size. The prototype
     * is set up for a medium sized weapon.
     *
     * @param newSize the target size.
     */
    public final void adaptToSize(final SizeCategory newSize) {
        //TODO implement this
        // damage
        // weight
        // cost
        // range increment
        // shift in encumbrance regarding a medium sized creature

        this.setSize(newSize);
    }

    /**
     * @return the damage
     */
    public final DiceAction getDamage() {
        return damage;
    }

    /**
     * @param damageInput the damage to set
     */
    public final void setDamage(final DiceAction damageInput) {
        this.damage = damageInput;
    }

    /**
     * @return the criticalFactor
     */
    public final Integer getCriticalFactor() {
        return criticalFactor;
    }

    /**
     * @param criticalFactorInput the criticalFactor to set
     */
    public final void setCriticalFactor(final Integer criticalFactorInput) {
        this.criticalFactor = criticalFactorInput;
    }

    /**
     * @return the criticalMinimumLevel
     */
    public final Integer getCriticalMinimumLevel() {
        return criticalMinimumLevel;
    }

    /**
     * @param criticalMinimumLevelInput the criticalMinimumLevel to set
     */
    public final void setCriticalMinimumLevel(
            final Integer criticalMinimumLevelInput) {
        this.criticalMinimumLevel = criticalMinimumLevelInput;
    }

    /**
     * @return the rangeIncrement
     */
    public final Integer getRangeIncrement() {
        return rangeIncrement;
    }

    /**
     * @param rangeIncrementInput the rangeIncrement to set
     */
    public final void setRangeIncrement(final Integer rangeIncrementInput) {
        this.rangeIncrement = rangeIncrementInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final IEncumbrance getEncumbrance() {
        return encumbrance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setEncumbrance(final IEncumbrance encumbranceInput) {
        this.encumbrance = encumbranceInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final IProficiency getProficiency() {
        return proficiency;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setProficiency(final IProficiency proficiencyInput) {
        this.proficiency = proficiencyInput;
    }

    /**
     * @return the weaponTypes
     */
    public final List<WeaponType> getWeaponTypes() {
        return weaponTypes;
    }

    /**
     * @param weaponTypesInput the weaponTypes to set
     */
    public final void setWeaponTypes(final List<WeaponType> weaponTypesInput) {
        this.weaponTypes = weaponTypesInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Boolean resembles(final IWeapon weapon) {
        if (weapon == null) {
            return Boolean.FALSE;
        }
        return weapon.getName().equals(this.getName());
    }
}
