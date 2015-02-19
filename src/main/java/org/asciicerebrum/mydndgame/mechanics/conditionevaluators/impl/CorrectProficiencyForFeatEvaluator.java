package org.asciicerebrum.mydndgame.mechanics.conditionevaluators.impl;

import java.util.Iterator;
import org.asciicerebrum.mydndgame.domain.core.ICharacter;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.mechanics.conditionevaluators.ConditionEvaluator;
import org.asciicerebrum.mydndgame.domain.ruleentities.FeatBinding;
import org.asciicerebrum.mydndgame.domain.ruleentities.FeatBindings;
import org.asciicerebrum.mydndgame.domain.ruleentities.FeatType;
import org.asciicerebrum.mydndgame.domain.ruleentities.Proficiency;
import org.asciicerebrum.mydndgame.facades.game.WeaponServiceFacade;

/**
 *
 * @author species8472
 */
public class CorrectProficiencyForFeatEvaluator implements ConditionEvaluator {

    /**
     * The feat type.
     */
    private FeatType featType;

    /**
     * Getting modified real-time-values from the weapon.
     */
    private WeaponServiceFacade weaponServiceFacade;

    @Override
    public final boolean evaluate(final ICharacter iCharacter,
            final UniqueEntity contextItem) {
        final DndCharacter dndCharacter = (DndCharacter) iCharacter;

        if (this.getFeatType() == null) {
            return false;
        }

        final FeatBindings featBindings = dndCharacter.getLevelAdvancements()
                .getFeatBindingsByFeatType(this.getFeatType());

        if (!(contextItem instanceof Weapon)) {
            return false;
        }

        final Weapon weapon = (Weapon) contextItem;
        final Iterator<FeatBinding> featBindingIterator
                = featBindings.iterator();
        while (featBindingIterator.hasNext()) {
            final FeatBinding featBinding = featBindingIterator.next();
            if (!(featBinding instanceof Proficiency)) {
                continue;
            }

            if (this.getWeaponServiceFacade().hasProficiency(
                    (Proficiency) featBinding, weapon, dndCharacter)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param featTypeInput the featType to set
     */
    public final void setFeatType(final FeatType featTypeInput) {
        this.featType = featTypeInput;
    }

    /**
     * @param weaponServiceFacadeInput the weaponServiceFacade to set
     */
    public final void setWeaponServiceFacade(
            final WeaponServiceFacade weaponServiceFacadeInput) {
        this.weaponServiceFacade = weaponServiceFacadeInput;
    }

    /**
     * @return the featType
     */
    public final FeatType getFeatType() {
        return featType;
    }

    /**
     * @return the weaponServiceFacade
     */
    public final WeaponServiceFacade getWeaponServiceFacade() {
        return weaponServiceFacade;
    }

}
