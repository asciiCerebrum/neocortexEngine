package org.asciicerebrum.mydndgame.conditionevaluator;

import org.asciicerebrum.mydndgame.domain.core.mechanics.Bonus;
import org.asciicerebrum.mydndgame.domain.core.attribution.BodySlot;
import org.asciicerebrum.mydndgame.domain.gameentities.DndCharacter;
import org.asciicerebrum.mydndgame.domain.gameentities.InventoryItem;
import org.asciicerebrum.mydndgame.domain.gameentities.UniqueEntity;
import org.asciicerebrum.mydndgame.observers.IObserver;
import org.asciicerebrum.mydndgame.services.context.SituationContextService;

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
                    final boolean evaluate(final BodySlot bodySlot) {
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
                    final boolean evaluate(final BodySlot bodySlot) {
                        return this.evaluateBoth(bodySlot);
                    }
                };

        /**
         * The evaluation of the wielding type de-factoly takes place here.
         *
         * @param bodySlot the body slot of the item.
         * @param dndCharacter the character wielding the item.
         * @return if the condition is met.
         */
        abstract boolean evaluate(BodySlot bodySlot);

        /**
         * Evaluator method for holding in secondary slot.
         *
         * @param bodySlot the body slot.
         * @return whether it is in the secondary slot or not.
         */
        protected final boolean evaluateSecondary(final BodySlot bodySlot) {
            return !bodySlot.getIsPrimaryAttackSlot().isValue();
        }

        /**
         * Evaluator method for holding in both slots (hands).
         *
         * @param bodySlot the body slot.
         * @return whether it is in both slots or not.
         */
        protected final boolean evaluateBoth(final BodySlot bodySlot) {
            final UniqueEntity item = bodySlot.getItem();
            if (item == null) {
                return false;
            }
            final BodySlot counterSlot = bodySlot.getCounterSlot();
            if (counterSlot == null) {
                return false;
            }
            final UniqueEntity counterItem = counterSlot.getItem();
            if (counterItem == null) {
                return false;
            }
            return item.equals(counterItem);
        }
    }

    /**
     * The wielding type to test.
     */
    private WieldingType wieldingType;

    /**
     * Getting settings from the character.
     */
    private SituationContextService situationContextService;

    /**
     * {@inheritDoc} Checks if the current item is wielded in the given way.
     */
    @Override
    public final boolean evaluate(final DndCharacter dndCharacter,
            final IObserver referenceObserver) {

        if (this.wieldingType == null) {
            return false;
        }

        final InventoryItem item = this.situationContextService
                .getActiveItem(dndCharacter);
        if (item == null) {
            return false;
        }
        return this.wieldingType.evaluate(
                dndCharacter.getBodySlots().getSlotForItem(item));
    }

    @Override
    public final boolean evaluate(final DndCharacter dndCharacter,
            final Bonus referenceBonus) {
        return this.evaluate(dndCharacter, (IObserver) null);
    }

    /**
     * @param wieldingTypeInput the wieldingType to set
     */
    public final void setWieldingType(final WieldingType wieldingTypeInput) {
        this.wieldingType = wieldingTypeInput;
    }

    /**
     * @param situationContextServiceInput the situationContextService to set
     */
    public final void setSituationContextService(
            final SituationContextService situationContextServiceInput) {
        this.situationContextService = situationContextServiceInput;
    }

}
