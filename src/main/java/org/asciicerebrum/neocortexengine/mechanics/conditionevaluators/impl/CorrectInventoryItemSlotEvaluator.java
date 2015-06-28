package org.asciicerebrum.neocortexengine.mechanics.conditionevaluators.impl;

import org.asciicerebrum.neocortexengine.domain.core.ICharacter;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.mechanics.conditionevaluators.ConditionEvaluator;
import org.asciicerebrum.neocortexengine.domain.game.InventoryItem;
import org.asciicerebrum.neocortexengine.domain.ruleentities.composition.PersonalizedBodySlot;
import org.asciicerebrum.neocortexengine.facades.game.InventoryItemServiceFacade;

/**
 * Checks if the inventory item is contained in one of its designated slots.
 *
 * @author species8472
 */
public class CorrectInventoryItemSlotEvaluator implements ConditionEvaluator {

    /**
     * Facade for retrieving modified inventory item values.
     */
    private InventoryItemServiceFacade itemFacade;

    @Override
    public final boolean evaluate(final ICharacter iCharacter,
            final UniqueEntity contextItem) {
        if (!(contextItem instanceof InventoryItem)) {
            return false;
        }

        final DndCharacter dndCharacter = (DndCharacter) iCharacter;

        final PersonalizedBodySlot bodySlot
                = dndCharacter.getPersonalizedBodySlots()
                .getSlotForItem(contextItem.getUniqueId());

        if (bodySlot == null) {
            return false;
        }

        return this.getItemFacade().isCorrectlyWielded(
                bodySlot.getBodySlotType(), (InventoryItem) contextItem,
                dndCharacter);
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
