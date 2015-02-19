package org.asciicerebrum.mydndgame.facades.game;

import org.asciicerebrum.mydndgame.domain.ruleentities.BodySlotType;
import org.asciicerebrum.mydndgame.domain.ruleentities.BodySlotTypes;
import org.asciicerebrum.mydndgame.domain.ruleentities.SizeCategory;
import org.asciicerebrum.mydndgame.domain.mechanics.ObserverHooks;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSources;
import org.asciicerebrum.mydndgame.domain.core.particles.Cost;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.InventoryItem;
import org.asciicerebrum.mydndgame.domain.mechanics.ObserverHook;
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
    public final Cost getCost(final InventoryItem inventoryItem,
            final DndCharacter dndCharacter) {

        final Cost baseCost = inventoryItem.getBaseCost();

        return (Cost) this.getObservableService().triggerObservers(
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

        return (BodySlotTypes) this.getObservableService().triggerObservers(
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

        return (SizeCategory) this.getObservableService()
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

    /**
     * @return the observableService
     */
    protected final ObservableService getObservableService() {
        return observableService;
    }

}
