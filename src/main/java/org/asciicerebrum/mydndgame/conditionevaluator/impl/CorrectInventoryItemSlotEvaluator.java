package org.asciicerebrum.mydndgame.conditionevaluator.impl;

import org.asciicerebrum.mydndgame.conditionevaluator.ConditionEvaluator;
import org.asciicerebrum.mydndgame.domain.core.mechanics.Bonus;
import org.asciicerebrum.mydndgame.domain.core.mechanics.Observer;
import org.asciicerebrum.mydndgame.domain.game.entities.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.entities.InventoryItem;
import org.asciicerebrum.mydndgame.domain.rules.composition.PersonalizedBodySlot;
import org.asciicerebrum.mydndgame.facades.gameentities.InventoryItemServiceFacade;
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
            final Observer referenceObserver) {

        final InventoryItem item = this.ctxService.getActiveItem(dndCharacter);

        final PersonalizedBodySlot bodySlot
                = dndCharacter.getPersonalizedBodySlots().getSlotForItem(item);

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
        return this.evaluate(dndCharacter, (Observer) null);
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
