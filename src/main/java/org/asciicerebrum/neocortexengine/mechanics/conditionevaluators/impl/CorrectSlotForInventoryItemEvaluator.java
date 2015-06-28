package org.asciicerebrum.neocortexengine.mechanics.conditionevaluators.impl;

import org.asciicerebrum.neocortexengine.domain.core.ICharacter;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.game.InventoryItem;
import org.asciicerebrum.neocortexengine.domain.ruleentities.composition.PersonalizedBodySlot;
import org.asciicerebrum.neocortexengine.facades.game.InventoryItemServiceFacade;
import org.asciicerebrum.neocortexengine.mechanics.conditionevaluators.ConditionEvaluator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author species8472
 */
public class CorrectSlotForInventoryItemEvaluator
        implements ConditionEvaluator {

    /**
     * The logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(
            CorrectSlotForInventoryItemEvaluator.class);

    /**
     * The inventory item service facade.
     */
    private InventoryItemServiceFacade inventoryItemServiceFacade;

    @Override
    public final boolean evaluate(final ICharacter dndCharacter,
            final UniqueEntity contextEntity) {

        LOG.debug("Evaluating correct body slot for entity {}.",
                contextEntity.getUniqueId().getValue());

        if (!(contextEntity instanceof InventoryItem)) {
            return false;
        }

        final InventoryItem item = (InventoryItem) contextEntity;

        final PersonalizedBodySlot slot = ((DndCharacter) dndCharacter)
                .getPersonalizedBodySlots()
                .getSlotForItem(item.getUniqueId());

        if (slot == null) {
            return false;
        }

        return this.getInventoryItemServiceFacade().getDesignatedBodySlotTypes(
                item, (DndCharacter) dndCharacter)
                .contains(slot.getBodySlotType());
    }

    /**
     * @return the inventoryItemServiceFacade
     */
    public final InventoryItemServiceFacade getInventoryItemServiceFacade() {
        return inventoryItemServiceFacade;
    }

    /**
     * @param inventoryItemServiceFacadeInput the inventoryItemServiceFacade to
     * set
     */
    public final void setInventoryItemServiceFacade(
            final InventoryItemServiceFacade inventoryItemServiceFacadeInput) {
        this.inventoryItemServiceFacade = inventoryItemServiceFacadeInput;
    }

}
