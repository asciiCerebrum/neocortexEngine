package org.asciicerebrum.mydndgame.facades.gameentities;

import org.asciicerebrum.mydndgame.domain.core.attribution.BodySlotTypes;
import org.asciicerebrum.mydndgame.domain.core.attribution.SizeCategory;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverHooks;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSources;
import org.asciicerebrum.mydndgame.domain.core.particles.Cost;
import org.asciicerebrum.mydndgame.domain.gameentities.DndCharacter;
import org.asciicerebrum.mydndgame.domain.gameentities.InventoryItem;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverHook;
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
    private ObservableService observableService;

    /**
     * {@inheritDoc}
     */
    @Override
    public Cost getCost(final Cost baseCost, final InventoryItem inventoryItem,
            final DndCharacter dndCharacter) {

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
            final BodySlotTypes baseBodySlotTypes,
            final InventoryItem inventoryItem,
            final DndCharacter dndCharacter) {

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
    public final SizeCategory getSize(final SizeCategory baseValue,
            final InventoryItem inventoryItem,
            final DndCharacter dndCharacter) {
        return (SizeCategory) this.observableService
                .triggerObservers(baseValue, inventoryItem,
                        new ObserverSources(dndCharacter),
                        new ObserverHooks(ObserverHook.SIZE_CATEGORY),
                        dndCharacter);
    }

}
