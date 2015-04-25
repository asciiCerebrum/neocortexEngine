package org.asciicerebrum.mydndgame.mechanics.conditionevaluators.impl;

import org.asciicerebrum.mydndgame.domain.core.ICharacter;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.mechanics.conditionevaluators.ConditionEvaluator;
import org.asciicerebrum.mydndgame.domain.ruleentities.WeaponCategory;
import org.asciicerebrum.mydndgame.services.context.SituationContextService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author species8472
 */
public class CorrectAttackModeEvaluator implements ConditionEvaluator {

    /**
     * The logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(
            CorrectAttackModeEvaluator.class);

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
    public final boolean evaluate(final ICharacter iCharacter,
            final UniqueEntity contextEntity) {
        final DndCharacter dndCharacter = (DndCharacter) iCharacter;

        LOG.debug("Eval of correct attack mode for {} with context {}.",
                dndCharacter.getUniqueId().getValue(),
                contextEntity.getUniqueId().getValue());

        if (!(contextEntity instanceof Weapon)) {
            return false;
        }

        WeaponCategory refAttackMode = this.getSituationContextService()
                .getItemAttackMode(contextEntity.getUniqueId(), dndCharacter);

        if (refAttackMode == null) {
            refAttackMode = ((Weapon) contextEntity).getDefaultAttackMode();
        }

        if (refAttackMode == null) {
            return false;
        }

        final boolean evalResult
                = refAttackMode.equals(this.getWeaponCategory());

        LOG.debug("Eval result is {}.", evalResult);

        return evalResult;
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

    /**
     * @return the weaponCategory
     */
    public final WeaponCategory getWeaponCategory() {
        return weaponCategory;
    }

    /**
     * @return the situationContextService
     */
    public final SituationContextService getSituationContextService() {
        return situationContextService;
    }

}
