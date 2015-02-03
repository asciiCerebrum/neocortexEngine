package org.asciicerebrum.mydndgame.mechanics.conditionevaluators;

import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.mechanics.transfer.ConditionEvaluator;
import org.asciicerebrum.mydndgame.domain.rules.Encumbrance;
import org.asciicerebrum.mydndgame.domain.game.dndcharacter.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.facades.game.WeaponServiceFacade;

/**
 *
 * @author species8472
 */
public class CorrectWeaponEncumbranceEvaluator implements ConditionEvaluator {

    /**
     * The encumbrance to compare with.
     */
    private Encumbrance encumbrance;

    /**
     * Getting modified real-time-values from the weapon.
     */
    private WeaponServiceFacade weaponServiceFacade;

    /**
     * {@inheritDoc} Checks if the given weapon's encumbrance corresponds to the
     * one from the situation context.
     *
     * @return
     */
    @Override
    public final boolean evaluate(final DndCharacter dndCharacter,
            final UniqueEntity contextItem) {

        if (this.encumbrance == null || contextItem == null
                || !(contextItem instanceof Weapon)) {
            return false;
        }

        return this.weaponServiceFacade.hasEncumbrance(this.encumbrance,
                (Weapon) contextItem, dndCharacter);
    }

    /**
     * @param encumbranceInput the encumbrance to set
     */
    public final void setEncumbrance(final Encumbrance encumbranceInput) {
        this.encumbrance = encumbranceInput;
    }

    /**
     * @param weaponServiceFacadeInput the weaponServiceFacade to set
     */
    public final void setWeaponServiceFacade(
            final WeaponServiceFacade weaponServiceFacadeInput) {
        this.weaponServiceFacade = weaponServiceFacadeInput;
    }

}
