package org.asciicerebrum.mydndgame.mechanics.conditionevaluators;

import org.asciicerebrum.mydndgame.domain.mechanics.interfaces.ConditionEvaluator;
import org.asciicerebrum.mydndgame.domain.rules.entities.WeaponCategory;
import org.asciicerebrum.mydndgame.domain.mechanics.entities.Bonus;
import org.asciicerebrum.mydndgame.domain.mechanics.entities.Observer;
import org.asciicerebrum.mydndgame.domain.game.entities.DndCharacter;
import org.asciicerebrum.mydndgame.services.context.SituationContextService;

/**
 *
 * @author species8472
 */
public class CorrectAttackModeEvaluator implements ConditionEvaluator {

    /**
     * The attack mode to compare with.
     */
    private WeaponCategory weaponCategory;

    /**
     * Getting settings from the character.
     */
    private SituationContextService situationContextService;

    /**
     * {@inheritDoc} Checks if the character's current attack mode equals the
     * one given here. This is independent of the weapon itself.
     *
     * @return
     */
    @Override
    public final boolean evaluate(final DndCharacter dndCharacter,
            final Observer referenceObserver) {

        final WeaponCategory refAttackMode = this.situationContextService
                .getActiveAttackMode(dndCharacter);

        if (refAttackMode == null) {
            return false;
        }

        return refAttackMode.equals(this.weaponCategory);
    }

    @Override
    public final boolean evaluate(final DndCharacter dndCharacter,
            final Bonus referenceBonus) {
        return this.evaluate(dndCharacter, (Observer) null);
    }

    /**
     * @param weaponCategoryInput the weaponCategory to set
     */
    public final void setWeaponCategory(
            final WeaponCategory weaponCategoryInput) {
        this.weaponCategory = weaponCategoryInput;
    }

    /**
     * @param situationContextServiceInput the situationContextService to set
     */
    public final void setSituationContextService(
            final SituationContextService situationContextServiceInput) {
        this.situationContextService = situationContextServiceInput;
    }

}
