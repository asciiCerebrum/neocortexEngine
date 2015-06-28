package org.asciicerebrum.neocortexengine.domain.ruleentities;

import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusValue;
import org.asciicerebrum.neocortexengine.domain.core.particles.SpellFailure;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.ContextBoni;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.source.UniqueEntityResolver;

/**
 *
 * @author species8472
 */
public class ArmorPrototype extends InventoryItemPrototype
        implements FeatBinding {

    /**
     * The maximum dexterity bonus that can be granted by this armor.
     */
    private BonusValue maxDexBonus;

    /**
     * Penalty for some skill checks.
     */
    private BonusValue armorCheckPenalty;

    /**
     * Percentage of the arcane spell failure.
     */
    private SpellFailure arcaneSpellFailure;

    //TODO speed shift (30 ft. --> 20 ft. via observers)
    /**
     * Armor, shield or extras.
     */
    private ArmorCategory armorCategory;

    /**
     * Light, medium, heavy or shield.
     */
    private Proficiency proficiency;

    /**
     * The value of the armor/shield bonus.
     */
    private BonusValue armorBonus;

    /**
     * @return the maxDexBonus
     */
    public final BonusValue getMaxDexBonus() {
        return maxDexBonus;
    }

    /**
     * @param maxDexBonusInput the maxDexBonus to set
     */
    public final void setMaxDexBonus(final BonusValue maxDexBonusInput) {
        this.maxDexBonus = maxDexBonusInput;
    }

    /**
     * @return the armorCheckPenalty
     */
    public final BonusValue getArmorCheckPenalty() {
        return armorCheckPenalty;
    }

    /**
     * @param armorCheckPenaltyInput the armorCheckPenalty to set
     */
    public final void setArmorCheckPenalty(
            final BonusValue armorCheckPenaltyInput) {
        this.armorCheckPenalty = armorCheckPenaltyInput;
    }

    /**
     * @return the arcaneSpellFailure
     */
    public final SpellFailure getArcaneSpellFailure() {
        return arcaneSpellFailure;
    }

    /**
     * @param arcaneSpellFailureInput the arcaneSpellFailure to set
     */
    public final void setArcaneSpellFailure(
            final SpellFailure arcaneSpellFailureInput) {
        this.arcaneSpellFailure = arcaneSpellFailureInput;
    }

    /**
     * @return the armorCategory
     */
    public final ArmorCategory getArmorCategory() {
        return armorCategory;
    }

    /**
     * @param armorCategoryInput the armorCategory to set
     */
    public final void setArmorCategory(
            final ArmorCategory armorCategoryInput) {
        this.armorCategory = armorCategoryInput;
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
     * @return the armorBonus
     */
    public final BonusValue getArmorBonus() {
        return armorBonus;
    }

    /**
     * @param armorBonusInput the armorBonus to set
     */
    public final void setArmorBonus(final BonusValue armorBonusInput) {
        this.armorBonus = armorBonusInput;
    }

    @Override
    public final ContextBoni getBoni(final UniqueEntity context,
            final UniqueEntityResolver resolver) {
        final ContextBoni ctxBoni = new ContextBoni();

        ctxBoni.add(this.getInventoryItemBoni(context, resolver));
        if (this.armorCategory != null) {
            ctxBoni.add(this.armorCategory.getBoni(context, resolver));
        }

        return ctxBoni;
    }

}
