package org.asciicerebrum.mydndgame.mechanics.conditionevaluators.impl;

import java.util.Iterator;
import org.asciicerebrum.mydndgame.domain.core.ICharacter;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.mechanics.conditionevaluators.ConditionEvaluator;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.domain.ruleentities.FeatBinding;
import org.asciicerebrum.mydndgame.domain.ruleentities.FeatBindings;
import org.asciicerebrum.mydndgame.domain.ruleentities.FeatType;
import org.asciicerebrum.mydndgame.domain.ruleentities.WeaponPrototype;

/**
 *
 * @author species8472
 */
public class CorrectWeaponForFeatEvaluator implements ConditionEvaluator {

    /**
     * The feat type.
     */
    private FeatType featType;

    /**
     * {@inheritDoc} Checks if weapon from the situation context.
     *
     * @return if weapons resemble each other. True if weapon is not set.
     */
    @Override
    public final boolean evaluate(final ICharacter iCharacter,
            final UniqueEntity contextItem) {
        final DndCharacter dndCharacter = (DndCharacter) iCharacter;

        if (contextItem == null || !(contextItem instanceof Weapon)) {
            return false;
        }

        final FeatBindings featBindings
                = dndCharacter.getFeatBindingsByFeatType(this.getFeatType());
        final Weapon weapon = (Weapon) contextItem;
        final Iterator<FeatBinding> featBindingIterator
                = featBindings.iterator();
        while (featBindingIterator.hasNext()) {
            final FeatBinding featBinding = featBindingIterator.next();
            if (FeatBinding.GenericBinding.ALL.equals(featBinding)) {
                return true;
            }
            if (!(featBinding instanceof WeaponPrototype)) {
                continue;
            }

            if (weapon.isOfWeaponPrototype((WeaponPrototype) featBinding)) {
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
     * @return the featType
     */
    public final FeatType getFeatType() {
        return featType;
    }

}
