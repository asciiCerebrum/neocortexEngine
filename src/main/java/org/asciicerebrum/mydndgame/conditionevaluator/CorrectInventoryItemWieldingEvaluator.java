package org.asciicerebrum.mydndgame.conditionevaluator;

import org.asciicerebrum.mydndgame.interfaces.entities.ConditionEvaluator;
import org.asciicerebrum.mydndgame.interfaces.entities.IBodySlotType;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.IInventoryItem;
import org.asciicerebrum.mydndgame.interfaces.entities.ISituationContext;
import org.asciicerebrum.mydndgame.interfaces.entities.Slotable;

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
                    final Boolean evaluate(final Slotable bodySlot,
                            final ICharacter dndCharacter) {
                        return !bodySlot.getBodySlotType()
                        .getIsPrimaryAttackSlot();
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
                    final Boolean evaluate(final Slotable bodySlot,
                            final ICharacter dndCharacter) {
                        IBodySlotType otherSlot = bodySlot.getBodySlotType()
                        .getCounterSlot();
                        IInventoryItem otherItem = dndCharacter
                        .getBodySlotByType(otherSlot).getItem();

                        if (otherItem == null || bodySlot.getItem() == null) {
                            return Boolean.FALSE;
                        }

                        return bodySlot.getItem().equals(otherItem);
                    }
                };

        /**
         * The evaluation of the wielding type de-factoly takes place here.
         *
         * @param bodySlot the body slot of the item.
         * @param dndCharacter the character wielding the item.
         * @return if the condition is met.
         */
        abstract Boolean evaluate(Slotable bodySlot,
                ICharacter dndCharacter);
    }

    /**
     * The wielding type to test.
     */
    private WieldingType wieldingType;

    /**
     * {@inheritDoc} Checks if the current weapon is wielded in the given way.
     */
    @Override
    public final Boolean evaluate(final ISituationContext situationContext) {

        if (this.getWieldingType() == null) {
            return Boolean.FALSE;
        }

        return this.getWieldingType().evaluate(
                situationContext.getCharacter()
                .getBodySlotByType(situationContext.getBodySlotType()),
                situationContext.getCharacter());
    }

    /**
     * @return the wieldingType
     */
    public final WieldingType getWieldingType() {
        return wieldingType;
    }

    /**
     * @param wieldingTypeInput the wieldingType to set
     */
    public final void setWieldingType(final WieldingType wieldingTypeInput) {
        this.wieldingType = wieldingTypeInput;
    }

}
