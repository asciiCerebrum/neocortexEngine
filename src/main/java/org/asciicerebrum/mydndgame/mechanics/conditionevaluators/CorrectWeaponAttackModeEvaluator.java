package org.asciicerebrum.mydndgame.mechanics.conditionevaluators;

import org.asciicerebrum.mydndgame.domain.mechanics.interfaces.ConditionEvaluator;
import org.asciicerebrum.mydndgame.domain.rules.entities.WeaponCategory;
import org.asciicerebrum.mydndgame.domain.mechanics.entities.Bonus;
import org.asciicerebrum.mydndgame.domain.mechanics.entities.Observer;
import org.asciicerebrum.mydndgame.domain.game.entities.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.entities.InventoryItem;
import org.asciicerebrum.mydndgame.domain.game.entities.Weapon;
import org.asciicerebrum.mydndgame.facades.gameentities.WeaponServiceFacade;
import org.asciicerebrum.mydndgame.services.context.SituationContextService;

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
     *
     * @return
     */
    @Override
    public final boolean evaluate(final DndCharacter dndCharacter,
            final Observer referenceObserver) {

        final WeaponCategory refAttackMode = this.situationContextService
                .getActiveAttackMode(dndCharacter);

        final InventoryItem refWeapon = this.situationContextService
                .getActiveItem(dndCharacter);

        if (refAttackMode == null || refWeapon == null
                || !(refWeapon instanceof Weapon)) {
            return false;
        }

        return this.weaponServiceFacade.isAttackModeCompatible(refAttackMode,
                (Weapon) refWeapon, dndCharacter);
    }

    @Override
    public final boolean evaluate(final DndCharacter dndCharacter,
            final Bonus referenceBonus) {
        return this.evaluate(dndCharacter, (Observer) null);
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

}
