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
import org.asciicerebrum.mydndgame.services.context.SituationContextService;
import org.asciicerebrum.mydndgame.services.core.ObservableService;

/**
 *
 * @author species8472
 */
public abstract class DefaultInventoryItemServiceFacade
        implements InventoryItemServiceFacade {

    /**
     * The observable service.
     */
    private ObservableService observableService;

    /**
     * The situation context service.
     */
    private SituationContextService situationContextService;

    /**
     * {@inheritDoc}
     */
    @Override
    public final Cost getCost(final InventoryItem inventoryItem,
            final DndCharacter dndCharacter) {

        final Cost baseCost = inventoryItem.getBaseCost();

        return (Cost) this.getObservableService().triggerObservers(
                baseCost, inventoryItem,
                new ObserverSources(dndCharacter, inventoryItem),
                new ObserverHooks(ObserverHook.PRICE),
                dndCharacter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Cost getCost(final InventoryItem inventoryItem) {

        final Cost baseCost = inventoryItem.getBaseCost();
        final DndCharacter pseudoCharacter = new DndCharacter();

        return (Cost) this.getObservableService().triggerObservers(
                baseCost, inventoryItem,
                new ObserverSources(pseudoCharacter, inventoryItem),
                new ObserverHooks(ObserverHook.PRICE),
                pseudoCharacter);
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
                new ObserverSources(dndCharacter, inventoryItem),
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
                        new ObserverSources(dndCharacter, inventoryItem),
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

    /**
     * @return the situationContextService
     */
    public final SituationContextService getSituationContextService() {
        return situationContextService;
    }

    /**
     * @param situationContextServiceInput the situationContextService to set
     */
    public final void setSituationContextService(
            final SituationContextService situationContextServiceInput) {
        this.situationContextService = situationContextServiceInput;
    }

}
