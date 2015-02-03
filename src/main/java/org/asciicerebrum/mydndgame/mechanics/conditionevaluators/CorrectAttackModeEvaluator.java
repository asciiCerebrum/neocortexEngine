package org.asciicerebrum.mydndgame.mechanics.conditionevaluators;

import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.ConditionEvaluator;
import org.asciicerebrum.mydndgame.domain.rules.WeaponCategory;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
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
            final UniqueEntity contextEntity) {

        final WeaponCategory refAttackMode = this.situationContextService
                .getItemAttackMode(contextEntity, dndCharacter);

        if (refAttackMode == null) {
            return false;
        }

        return refAttackMode.equals(this.weaponCategory);
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