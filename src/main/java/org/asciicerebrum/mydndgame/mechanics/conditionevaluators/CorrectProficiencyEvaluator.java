package org.asciicerebrum.mydndgame.mechanics.conditionevaluators;

import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.mechanics.conditionevaluators.interfaces.ConditionEvaluator;
import org.asciicerebrum.mydndgame.domain.rules.Proficiency;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.facades.game.WeaponServiceFacade;

/**
 *
 * @author species8472
 */
public class CorrectProficiencyEvaluator implements ConditionEvaluator {

    /**
     * The proficiency to compare with.
     */
    private Proficiency proficiency;

    /**
     * Getting modified real-time-values from the weapon.
     */
    private WeaponServiceFacade weaponServiceFacade;

    /**
     * {@inheritDoc} Checks if the given weapon's (weapon in the active slot)
     * proficiency corresponds to the one from the situation context. The
     * proficiency can be compared with equals as it is a singleton (equality by
     * reference).
     *
     * @return
     */
    @Override
    public final boolean evaluate(final DndCharacter dndCharacter,
            final UniqueEntity contextItem) {

        if (this.proficiency == null || contextItem == null
                || !(contextItem instanceof Weapon)) {
            return false;
        }

        return this.weaponServiceFacade.hasProficiency(this.proficiency,
                (Weapon) contextItem, dndCharacter);
    }

    /**
     * @param proficiencyInput the proficiency to set
     */
    public final void setProficiency(final Proficiency proficiencyInput) {
        this.proficiency = proficiencyInput;
    }

    /**
     * @param weaponServiceFacadeInput the weaponServiceFacade to set
     */
    public final void setWeaponServiceFacade(
            final WeaponServiceFacade weaponServiceFacadeInput) {
        this.weaponServiceFacade = weaponServiceFacadeInput;
    }

}
