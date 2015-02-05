package org.asciicerebrum.mydndgame.mechanics.conditionevaluators.impl;

import org.asciicerebrum.mydndgame.domain.core.ICharacter;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.mechanics.conditionevaluators.ConditionEvaluator;
import org.asciicerebrum.mydndgame.domain.game.InventoryItem;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.PersonalizedBodySlot;
import org.asciicerebrum.mydndgame.facades.game.InventoryItemServiceFacade;

/**
 * Checks if the inventory item is contained in one of its designated slots.
 *
 * @author species8472
 */
public class CorrectInventoryItemSlotEvaluator implements ConditionEvaluator {

    private InventoryItemServiceFacade itemFacade;

    /**
     * {@inheritDoc }
     */
    @Override
    public final boolean evaluate(final ICharacter iCharacter,
            final UniqueEntity contextItem) {
        if (!(contextItem instanceof InventoryItem)) {
            return false;
        }

        final DndCharacter dndCharacter = (DndCharacter) iCharacter;

        final PersonalizedBodySlot bodySlot
                = dndCharacter.getPersonalizedBodySlots()
                .getSlotForItem(contextItem);

        if (bodySlot == null) {
            return false;
        }

        return this.getItemFacade().isCorrectlyWielded(bodySlot.getBodySlotType(),
                (InventoryItem) contextItem, dndCharacter);
    }

    /**
     * @param itemFacadeInput the itemFacade to set
     */
    public final void setItemFacade(
            final InventoryItemServiceFacade itemFacadeInput) {
        this.itemFacade = itemFacadeInput;
    }

    /**
     * @return the itemFacade
     */
    public final InventoryItemServiceFacade getItemFacade() {
        return itemFacade;
    }

}
