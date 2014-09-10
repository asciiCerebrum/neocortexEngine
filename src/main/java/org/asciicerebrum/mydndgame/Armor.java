package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.interfaces.entities.IArmor;
import org.asciicerebrum.mydndgame.interfaces.entities.IArmorCategory;
import org.asciicerebrum.mydndgame.interfaces.entities.IProficiency;
import org.asciicerebrum.mydndgame.interfaces.entities.ObserverHook;

/**
 *
 * @author species8472
 */
public class Armor extends InventoryItem implements IArmor {

    //TODO speed shift (30 ft. --> 20 ft. via observers)
    /**
     * Armor, shield or extras.
     */
    private IArmorCategory armorCategory;

    /**
     * Light, medium, heavy or shield.
     */
    private IProficiency proficiency;

    /**
     * The maximum dexterity bonus that can be granted by this armor.
     */
    private Long maxDexBonus;

    /**
     * Penalty for some skill checks.
     */
    private Long armorCheckPenalty;

    /**
     * Percentage of the arcane spell failure.
     */
    private Long arcaneSpellFailure;

    /**
     * {@inheritDoc}
     */
    @Override
    public final IArmorCategory getArmorCategory() {
        return armorCategory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setArmorCategory(
            final IArmorCategory armorCategoryInput) {
        this.armorCategory = armorCategoryInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Long getMaxDexBonus() {
        return maxDexBonus;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setMaxDexBonus(final Long maxDexBonusInput) {
        this.maxDexBonus = maxDexBonusInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Long getArmorCheckPenalty() {
        return (Long) this.getObservableDelegate()
                .triggerObservers(
                        ObserverHook.ARMOR_CHECK_PENALTY,
                        this.armorCheckPenalty, this.getObserverMap(), null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setArmorCheckPenalty(final Long armorCheckPenaltyInput) {
        this.armorCheckPenalty = armorCheckPenaltyInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Long getArcaneSpellFailure() {
        return arcaneSpellFailure;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setArcaneSpellFailure(
            final Long arcaneSpellFailureInput) {
        this.arcaneSpellFailure = arcaneSpellFailureInput;
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
}
