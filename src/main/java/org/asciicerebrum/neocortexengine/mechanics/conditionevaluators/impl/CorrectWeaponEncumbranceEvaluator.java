package org.asciicerebrum.neocortexengine.mechanics.conditionevaluators.impl;

import org.asciicerebrum.neocortexengine.domain.core.ICharacter;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.mechanics.conditionevaluators.ConditionEvaluator;
import org.asciicerebrum.neocortexengine.domain.ruleentities.Encumbrance;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.game.Weapon;
import org.asciicerebrum.neocortexengine.facades.game.WeaponServiceFacade;

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
     */
    @Override
    public final boolean evaluate(final ICharacter iCharacter,
            final UniqueEntity contextItem) {
        final DndCharacter dndCharacter = (DndCharacter) iCharacter;

        if (this.getEncumbrance() == null || contextItem == null
                || !(contextItem instanceof Weapon)) {
            return false;
        }

        return this.getWeaponServiceFacade().hasEncumbrance(
                this.getEncumbrance(), (Weapon) contextItem, dndCharacter);
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

    /**
     * @return the encumbrance
     */
    public final Encumbrance getEncumbrance() {
        return encumbrance;
    }

    /**
     * @return the weaponServiceFacade
     */
    public final WeaponServiceFacade getWeaponServiceFacade() {
        return weaponServiceFacade;
    }

}
