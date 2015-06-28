package org.asciicerebrum.neocortexengine.mechanics.conditionevaluators.impl;

import org.asciicerebrum.neocortexengine.domain.core.ICharacter;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.mechanics.conditionevaluators.ConditionEvaluator;
import org.asciicerebrum.neocortexengine.domain.ruleentities.WeaponCategory;
import org.asciicerebrum.neocortexengine.domain.game.Weapon;
import org.asciicerebrum.neocortexengine.facades.game.WeaponServiceFacade;
import org.asciicerebrum.neocortexengine.services.context.SituationContextService;

/**
 *
 * @author species8472
 */
public class CorrectWeaponAttackModeEvaluator implements ConditionEvaluator {

    /**
     * Getting settings from the character.
     */
    private SituationContextService situationContextService;

    /**
     * Getting modified real-time-values from the weapon.
     */
    private WeaponServiceFacade weaponServiceFacade;

    /**
     * {@inheritDoc} Checks if the given weapon is used in its destined way.
     */
    @Override
    public final boolean evaluate(final ICharacter iCharacter,
            final UniqueEntity contextItem) {
        final DndCharacter dndCharacter = (DndCharacter) iCharacter;

        if (contextItem == null) {
            return false;
        }

        if (!(contextItem instanceof Weapon)) {
            return false;
        }

        WeaponCategory refAttackMode = this.getSituationContextService()
                .getItemAttackMode(contextItem.getUniqueId(), dndCharacter);

        if (refAttackMode == null) {
            refAttackMode = ((Weapon) contextItem).getDefaultAttackMode();
        }

        if (refAttackMode == null) {
            return false;
        }

        return this.getWeaponServiceFacade().isAttackModeCompatible(
                refAttackMode, (Weapon) contextItem, dndCharacter);
    }

    /**
     * @param situationContextServiceInput the situationContextService to set
     */
    public final void setSituationContextService(
            final SituationContextService situationContextServiceInput) {
        this.situationContextService = situationContextServiceInput;
    }

    /**
     * @param weaponServiceFacadeInput the weaponServiceFacade to set
     */
    public final void setWeaponServiceFacade(
            final WeaponServiceFacade weaponServiceFacadeInput) {
        this.weaponServiceFacade = weaponServiceFacadeInput;
    }

    /**
     * @return the situationContextService
     */
    public final SituationContextService getSituationContextService() {
        return situationContextService;
    }

    /**
     * @return the weaponServiceFacade
     */
    public final WeaponServiceFacade getWeaponServiceFacade() {
        return weaponServiceFacade;
    }

}
