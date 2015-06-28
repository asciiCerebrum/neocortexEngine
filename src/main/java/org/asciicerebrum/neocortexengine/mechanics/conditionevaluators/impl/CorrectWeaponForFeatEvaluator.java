package org.asciicerebrum.neocortexengine.mechanics.conditionevaluators.impl;

import java.util.Iterator;
import org.asciicerebrum.neocortexengine.domain.core.ICharacter;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.game.Weapon;
import org.asciicerebrum.neocortexengine.domain.ruleentities.FeatBinding;
import org.asciicerebrum.neocortexengine.domain.ruleentities.FeatBindings;
import org.asciicerebrum.neocortexengine.domain.ruleentities.FeatType;
import org.asciicerebrum.neocortexengine.domain.ruleentities.WeaponPrototype;
import org.asciicerebrum.neocortexengine.mechanics.conditionevaluators.ConditionEvaluator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author species8472
 */
public class CorrectWeaponForFeatEvaluator implements ConditionEvaluator {

    /**
     * The logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(
            CorrectWeaponForFeatEvaluator.class);

    /**
     * The feat type.
     */
    private FeatType featType;

    /**
     * {@inheritDoc} Checks if weapon from the situation context.
     */
    @Override
    public final boolean evaluate(final ICharacter iCharacter,
            final UniqueEntity contextItem) {
        final DndCharacter dndCharacter = (DndCharacter) iCharacter;

        LOG.debug("Entering correct weapon for feat.");

        if (contextItem == null || !(contextItem instanceof Weapon)) {
            return false;
        }

        LOG.debug("Evaluating for {} with context {}.",
                ((DndCharacter) iCharacter).getUniqueId().getValue(),
                contextItem.getUniqueId().getValue());

        final FeatBindings featBindings
                = dndCharacter.getFeatBindingsByFeatType(this.getFeatType());
        final Weapon weapon = (Weapon) contextItem;
        final Iterator<FeatBinding> featBindingIterator
                = featBindings.iterator();
        while (featBindingIterator.hasNext()) {
            final FeatBinding featBinding = featBindingIterator.next();

            LOG.debug("Checking feat binding of type {}.",
                    featBinding.getClass().getSimpleName());

            if (FeatBinding.GenericBinding.ALL.equals(featBinding)) {
                return true;
            }
            if (!(featBinding instanceof WeaponPrototype)) {
                continue;
            }

            if (weapon.isOfWeaponPrototype((WeaponPrototype) featBinding)) {
                LOG.debug("Found correct prototype for weapon feat.");
                return true;
            }
        }
        LOG.debug("Weapon invalid for feat.");
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
