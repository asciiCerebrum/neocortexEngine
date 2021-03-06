package org.asciicerebrum.neocortexengine.mechanics.conditionevaluators.impl;

import java.util.Iterator;
import org.asciicerebrum.neocortexengine.domain.core.ICharacter;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.game.Weapon;
import org.asciicerebrum.neocortexengine.mechanics.conditionevaluators.ConditionEvaluator;
import org.asciicerebrum.neocortexengine.domain.ruleentities.FeatBinding;
import org.asciicerebrum.neocortexengine.domain.ruleentities.FeatBindings;
import org.asciicerebrum.neocortexengine.domain.ruleentities.FeatType;
import org.asciicerebrum.neocortexengine.domain.ruleentities.Proficiency;
import org.asciicerebrum.neocortexengine.facades.game.WeaponServiceFacade;

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

        if (!(contextItem instanceof Weapon)) {
            return false;
        }

        final FeatBindings featBindings
                = dndCharacter.getFeatBindingsByFeatType(this.getFeatType());
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
