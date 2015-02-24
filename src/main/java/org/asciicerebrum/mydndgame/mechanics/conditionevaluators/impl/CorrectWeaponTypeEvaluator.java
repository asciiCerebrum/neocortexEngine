package org.asciicerebrum.mydndgame.mechanics.conditionevaluators.impl;

import org.asciicerebrum.mydndgame.domain.core.ICharacter;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.mechanics.conditionevaluators.ConditionEvaluator;
import org.asciicerebrum.mydndgame.domain.ruleentities.WeaponType;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.facades.game.WeaponServiceFacade;

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
    public final boolean evaluate(final ICharacter iCharacter,
            final UniqueEntity contextItem) {
        final DndCharacter dndCharacter = (DndCharacter) iCharacter;

        if (this.getWeaponType() == null || contextItem == null
                || !(contextItem instanceof Weapon)) {
            return false;
        }

        return this.getWeaponServiceFacade().hasWeaponType(this.getWeaponType(),
                (Weapon) contextItem, dndCharacter);
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
     * @param weaponServiceFacadeInput the weaponServiceFacade to set
     */
    public final void setWeaponServiceFacade(
            final WeaponServiceFacade weaponServiceFacadeInput) {
        this.weaponServiceFacade = weaponServiceFacadeInput;
    }

    /**
     * @return the weaponServiceFacade
     */
    public final WeaponServiceFacade getWeaponServiceFacade() {
        return weaponServiceFacade;
    }

}
