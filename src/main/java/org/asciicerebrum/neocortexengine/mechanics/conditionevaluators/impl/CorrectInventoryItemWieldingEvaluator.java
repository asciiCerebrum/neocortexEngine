package org.asciicerebrum.neocortexengine.mechanics.conditionevaluators.impl;

import org.asciicerebrum.neocortexengine.domain.core.ICharacter;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueId;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.mechanics.conditionevaluators.ConditionEvaluator;
import org.asciicerebrum.neocortexengine.domain.ruleentities.composition.PersonalizedBodySlot;
import org.asciicerebrum.neocortexengine.domain.ruleentities.composition.PersonalizedBodySlots;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author species8472
 */
public class CorrectInventoryItemWieldingEvaluator
        implements ConditionEvaluator {

    /**
     * The logger instance.
     */
    private static final Logger LOG
            = LoggerFactory.getLogger(
                    CorrectInventoryItemWieldingEvaluator.class);

    /**
     * Defines the way a weapon is held.
     */
    public static enum WieldingType {

        /**
         * Held in off-hand. That also means not in both hands!
         */
        SECONDARY {
                    /**
                     * {@inheritDoc}
                     */
                    @Override
                    final boolean evaluate(
                            final PersonalizedBodySlot bodySlot,
                            final PersonalizedBodySlots bodySlots) {
                                return this.evaluateSecondary(bodySlot)
                                && !this.evaluateBoth(bodySlot, bodySlots);
                            }
                },
        /**
         * Held in both hands.
         */
        BOTH {

                    /**
                     * {@inheritDoc} The item of given slot must also be present
                     * in the opposite slot.
                     */
                    @Override
                    final boolean evaluate(
                            final PersonalizedBodySlot bodySlot,
                            final PersonalizedBodySlots bodySlots) {
                                return this.evaluateBoth(bodySlot, bodySlots);
                            }
                };

        /**
         * The evaluation of the wielding type de-factoly takes place here.
         *
         * @param bodySlot the body slot of the item.
         * @param bodySlots the collection of body slots available.
         * @return if the condition is met.
         */
        abstract boolean evaluate(PersonalizedBodySlot bodySlot,
                PersonalizedBodySlots bodySlots);

        /**
         * Evaluator method for holding in secondary slot.
         *
         * @param bodySlot the body slot.
         * @return whether it is in the secondary slot or not.
         */
        protected final boolean evaluateSecondary(
                final PersonalizedBodySlot bodySlot) {
            return !bodySlot.getIsPrimaryAttackSlot().isValue();
        }

        /**
         * Evaluator method for holding in both slots (hands).
         *
         * @param bodySlot the body slot.
         * @param bodySlots the whole collection of slots.
         * @return whether it is in both slots or not.
         */
        protected final boolean evaluateBoth(
                final PersonalizedBodySlot bodySlot,
                final PersonalizedBodySlots bodySlots) {
            if (bodySlot == null) {
                return false;
            }
            final UniqueId itemId = bodySlot.getItemId();
            final PersonalizedBodySlot counterSlot
                    = bodySlots.getCounterSlotForSlot(bodySlot);
            if (counterSlot == null) {
                return false;
            }
            final UniqueId counterItemId = counterSlot.getItemId();
            if (counterItemId == null) {
                return false;
            }
            return itemId.equals(counterItemId);
        }
    }

    /**
     * The wielding type to test.
     */
    private WieldingType wieldingType;

    /**
     * {@inheritDoc} Checks if the current item is wielded in the given way.
     */
    @Override
    public final boolean evaluate(final ICharacter iCharacter,
            final UniqueEntity contextItem) {
        final DndCharacter dndCharacter = (DndCharacter) iCharacter;

        LOG.debug("Eval correct item wielding for {}.",
                dndCharacter.getUniqueId().getValue());

        if (this.wieldingType == null) {
            return false;
        }

        if (contextItem == null) {
            return false;
        }
        final boolean evalResult = this.wieldingType.evaluate(
                dndCharacter.getPersonalizedBodySlots()
                .getSlotForItem(contextItem.getUniqueId()),
                dndCharacter.getPersonalizedBodySlots());

        LOG.debug("Correct item wielding result evaluated: {}, {}: {}.",
                new Object[]{contextItem.getUniqueId().getValue(),
                    this.wieldingType, evalResult});

        return evalResult;
    }

    /**
     * @param wieldingTypeInput the wieldingType to set
     */
    public final void setWieldingType(final WieldingType wieldingTypeInput) {
        this.wieldingType = wieldingTypeInput;
    }

}
