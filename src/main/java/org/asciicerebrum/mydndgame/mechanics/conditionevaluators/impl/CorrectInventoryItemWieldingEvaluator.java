package org.asciicerebrum.mydndgame.mechanics.conditionevaluators.impl;

import org.asciicerebrum.mydndgame.domain.core.ICharacter;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.mechanics.conditionevaluators.ConditionEvaluator;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.PersonalizedBodySlot;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.PersonalizedBodySlots;

/**
 *
 * @author species8472
 */
public class CorrectInventoryItemWieldingEvaluator
        implements ConditionEvaluator {

    /**
     * Defines the way a weapon is held.
     */
    public static enum WieldingType {

        /**
         * Held in off-hand.
         */
        SECONDARY {
                    /**
                     * {@inheritDoc}
                     */
                    @Override
                    final boolean evaluate(
                            final PersonalizedBodySlot bodySlot,
                            final PersonalizedBodySlots bodySlots) {
                                return this.evaluateSecondary(bodySlot);
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

        if (this.wieldingType == null) {
            return false;
        }

        if (contextItem == null) {
            return false;
        }
        return this.wieldingType.evaluate(
                dndCharacter.getPersonalizedBodySlots()
                .getSlotForItem(contextItem.getUniqueId()),
                dndCharacter.getPersonalizedBodySlots());
    }

    /**
     * @param wieldingTypeInput the wieldingType to set
     */
    public final void setWieldingType(final WieldingType wieldingTypeInput) {
        this.wieldingType = wieldingTypeInput;
    }

}
