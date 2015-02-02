package org.asciicerebrum.mydndgame.facades.gameentities;

import org.asciicerebrum.mydndgame.domain.rules.entities.BodySlotType;
import org.asciicerebrum.mydndgame.domain.rules.entities.BodySlotTypes;
import org.asciicerebrum.mydndgame.domain.rules.entities.SizeCategory;
import org.asciicerebrum.mydndgame.domain.mechanics.entities.ObserverHooks;
import org.asciicerebrum.mydndgame.domain.mechanics.entities.ObserverSources;
import org.asciicerebrum.mydndgame.domain.core.particles.Cost;
import org.asciicerebrum.mydndgame.domain.game.entities.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.entities.InventoryItem;
import org.asciicerebrum.mydndgame.domain.mechanics.entities.ObserverHook;
import org.asciicerebrum.mydndgame.services.core.ObservableService;

/**
 *
 * @author species8472
 */
public class DefaultInventoryItemServiceFacade
        implements InventoryItemServiceFacade {

    /**
     * The observable service.
     */
    protected ObservableService observableService;

    /**
     * {@inheritDoc}
     */
    @Override
    public Cost getCost(final InventoryItem inventoryItem,
            final DndCharacter dndCharacter) {

        final Cost baseCost = inventoryItem.getBaseCost();

        return (Cost) this.observableService.triggerObservers(
                baseCost, inventoryItem,
                new ObserverSources(dndCharacter),
                new ObserverHooks(ObserverHook.PRICE),
                dndCharacter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final BodySlotTypes getDesignatedBodySlotTypes(
            final InventoryItem inventoryItem,
            final DndCharacter dndCharacter) {

        final BodySlotTypes baseBodySlotTypes
                = inventoryItem.getBaseDesignatedBodySlotTypes();

        return (BodySlotTypes) this.observableService.triggerObservers(
                baseBodySlotTypes, inventoryItem,
                new ObserverSources(dndCharacter),
                new ObserverHooks(ObserverHook.DESIGNATED_BODY_SLOT),
                dndCharacter);
    }

    /**
     * @param observableServiceInput the observableService to set
     */
    public final void setObservableService(
            final ObservableService observableServiceInput) {
        this.observableService = observableServiceInput;
    }

    @Override
    public final SizeCategory getSize(final InventoryItem inventoryItem,
            final DndCharacter dndCharacter) {
        final SizeCategory baseValue = inventoryItem.getSizeCategory();

        return (SizeCategory) this.observableService
                .triggerObservers(baseValue, inventoryItem,
                        new ObserverSources(dndCharacter),
                        new ObserverHooks(ObserverHook.SIZE_CATEGORY),
                        dndCharacter);
    }

    @Override
    public final boolean isCorrectlyWielded(final BodySlotType bodySlotType,
            final InventoryItem inventoryItem,
            final DndCharacter dndCharacter) {
        return this.getDesignatedBodySlotTypes(inventoryItem, dndCharacter)
                .contains(bodySlotType);
    }

}
