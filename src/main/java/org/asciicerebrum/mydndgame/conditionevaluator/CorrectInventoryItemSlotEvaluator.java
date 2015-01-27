package org.asciicerebrum.mydndgame.conditionevaluator;

import org.asciicerebrum.mydndgame.domain.core.mechanics.Bonus;
import org.asciicerebrum.mydndgame.domain.gameentities.BodySlot;
import org.asciicerebrum.mydndgame.domain.gameentities.DndCharacter;
import org.asciicerebrum.mydndgame.domain.gameentities.InventoryItem;
import org.asciicerebrum.mydndgame.facades.gameentities.InventoryItemServiceFacade;
import org.asciicerebrum.mydndgame.observers.IObserver;
import org.asciicerebrum.mydndgame.services.context.SituationContextService;

/**
 * Checks if the inventory item is contained in one of its designated slots.
 *
 * @author species8472
 */
public class CorrectInventoryItemSlotEvaluator implements ConditionEvaluator {

    private SituationContextService ctxService;

    private InventoryItemServiceFacade itemFacade;

    /**
     * {@inheritDoc }
     */
    @Override
    public final boolean evaluate(final DndCharacter dndCharacter,
            final IObserver referenceObserver) {

        final InventoryItem item = this.ctxService.getActiveItem(dndCharacter);

        final BodySlot bodySlot = dndCharacter.getBodySlots()
                .getSlotForItem(item);

        if (bodySlot == null) {
            return false;
        }

        return this.itemFacade.isCorrectlyWielded(bodySlot.getBodySlotType(),
                item, dndCharacter);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public final boolean evaluate(final DndCharacter dndCharacter,
            final Bonus referenceBonus) {
        return this.evaluate(dndCharacter, (IObserver) null);
    }

    /**
     * @param ctxServiceInput the ctxService to set
     */
    public final void setCtxService(
            final SituationContextService ctxServiceInput) {
        this.ctxService = ctxServiceInput;
    }

    /**
     * @param itemFacadeInput the itemFacade to set
     */
    public final void setItemFacade(
            final InventoryItemServiceFacade itemFacadeInput) {
        this.itemFacade = itemFacadeInput;
    }

}
