package org.asciicerebrum.mydndgame.conditionevaluator;

import org.asciicerebrum.mydndgame.domain.rules.entities.WeaponType;
import org.asciicerebrum.mydndgame.domain.core.mechanics.Bonus;
import org.asciicerebrum.mydndgame.domain.game.entities.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.entities.InventoryItem;
import org.asciicerebrum.mydndgame.domain.game.entities.Weapon;
import org.asciicerebrum.mydndgame.facades.gameentities.WeaponServiceFacade;
import org.asciicerebrum.mydndgame.observers.IObserver;
import org.asciicerebrum.mydndgame.services.context.SituationContextService;

/**
 *
 * @author species8472
 */
public class CorrectWeaponTypeEvaluator implements ConditionEvaluator {

    /**
     * The weapon type to compare with.
     */
    private WeaponType weaponType;

    /**
     * Getting settings from the character.
     */
    private SituationContextService situationContextService;

    /**
     * Getting modified real-time-values from the weapon.
     */
    private WeaponServiceFacade weaponServiceFacade;

    /**
     * {@inheritDoc} Checks if the given weapontype is included in the current
     * weapon's list of types.
     *
     * @return
     */
    @Override
    public final boolean evaluate(final DndCharacter dndCharacter,
            final IObserver referenceObserver) {

        final InventoryItem refWeapon = this.situationContextService
                .getActiveItem(dndCharacter);

        if (this.weaponType == null || refWeapon == null
                || !(refWeapon instanceof Weapon)) {
            return false;
        }

        return this.weaponServiceFacade.hasWeaponType(this.weaponType,
                (Weapon) refWeapon, dndCharacter);
    }

    @Override
    public final boolean evaluate(final DndCharacter dndCharacter,
            final Bonus referenceBonus) {
        return this.evaluate(dndCharacter, (IObserver) null);
    }

    /**
     * @return the weaponType
     */
    public final WeaponType getWeaponType() {
        return weaponType;
    }

    /**
     * @param weaponTypeInput the weaponType to set
     */
    public final void setWeaponType(final WeaponType weaponTypeInput) {
        this.weaponType = weaponTypeInput;
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
