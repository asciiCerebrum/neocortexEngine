package org.asciicerebrum.mydndgame.conditionevaluator;

import org.asciicerebrum.mydndgame.interfaces.entities.ConditionEvaluator;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.IWeaponCategory;

/**
 *
 * @author species8472
 */
public class CorrectAttackModeEvaluator implements ConditionEvaluator {

    /**
     * The attack mode to compare with.
     */
    private IWeaponCategory weaponCategory;

    /**
     * {@inheritDoc} Checks if the character's current attack mode equals the
     * one given here. This is independent of the weapon itself.
     *
     * @return
     */
    @Override
    public final Boolean evaluate(final ICharacter character) {

        final IWeaponCategory refAttackMode = character.getSituationContext()
                .getAttackMode();

        if (refAttackMode == null) {
            return Boolean.FALSE;
        }

        return refAttackMode.equals(this.getWeaponCategory());
    }

    /**
     * @return the weaponCategory
     */
    public final IWeaponCategory getWeaponCategory() {
        return weaponCategory;
    }

    /**
     * @param weaponCategoryInput the weaponCategory to set
     */
    public final void setWeaponCategory(
            final IWeaponCategory weaponCategoryInput) {
        this.weaponCategory = weaponCategoryInput;
    }

}
