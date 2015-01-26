package org.asciicerebrum.mydndgame.domain.gameentities;

import org.asciicerebrum.mydndgame.domain.core.attribution.BodySlotType;
import org.asciicerebrum.mydndgame.domain.core.attribution.BodySlotTypes;
import org.asciicerebrum.mydndgame.domain.core.attribution.SizeCategory;
import org.asciicerebrum.mydndgame.domain.core.attribution.SpecialAbilities;
import org.asciicerebrum.mydndgame.domain.core.mechanics.Boni;
import org.asciicerebrum.mydndgame.domain.core.mechanics.BonusSources;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSources;
import org.asciicerebrum.mydndgame.observers.Observers;
import org.asciicerebrum.mydndgame.domain.core.particles.Cost;
import org.asciicerebrum.mydndgame.domain.gameentities.prototypes.InventoryItemPrototype;
import org.asciicerebrum.mydndgame.facades.gameentities.InventoryItemServiceFacade;

/**
 *
 * @author species8472
 */
public abstract class InventoryItem extends UniqueEntity {

    /**
     * Facade for all services participating in realtime-calculation of item
     * attributes.
     */
    private InventoryItemServiceFacade inventoryItemServiceFacade;

    /**
     * The prototype of the inventory item.
     */
    private InventoryItemPrototype inventoryItemPrototype;

    /**
     * The individual size of the object.
     */
    private SizeCategory sizeCategory;

    /**
     * All individual special abilities for this item instance.
     */
    private SpecialAbilities specialAbilities;

    /**
     * @return the inventoryItemPrototype
     */
    protected InventoryItemPrototype getInventoryItemPrototype() {
        return inventoryItemPrototype;
    }

    /**
     * @param inventoryItemPrototypeInput the inventoryItemPrototype to set
     */
    protected void setInventoryItemPrototype(
            final InventoryItemPrototype inventoryItemPrototypeInput) {
        this.inventoryItemPrototype = inventoryItemPrototypeInput;
    }

    /**
     * @return the sizeCategory
     */
    protected SizeCategory getSizeCategory() {
        return sizeCategory;
    }

    /**
     * @param sizeCategoryInput the sizeCategory to set
     */
    protected void setSizeCategory(final SizeCategory sizeCategoryInput) {
        this.sizeCategory = sizeCategoryInput;
    }

    /**
     * @return the specialAbilities
     */
    protected SpecialAbilities getSpecialAbilities() {
        return specialAbilities;
    }

    /**
     * @param specialAbilitiesInput the specialAbilities to set
     */
    protected void setSpecialAbilities(
            final SpecialAbilities specialAbilitiesInput) {
        this.specialAbilities = specialAbilitiesInput;
    }

    /**
     * Calculating the cost of this item and taking into account all
     * cost-manipulating features.
     *
     * @return the calculated cost.
     */
    public final Cost getCost(final DndCharacter dndCharacter) {
        final Cost baseCost = this.inventoryItemPrototype.getBaseCost();

        return this.inventoryItemServiceFacade.getCost(
                baseCost, this, dndCharacter);
    }

    public final BodySlotTypes getDesignatedBodySlotTypes(
            final DndCharacter dndCharacter) {
        final BodySlotTypes designatedBodySlotTypes
                = this.inventoryItemPrototype.getDesignatedBodySlotTypes();

        return this.inventoryItemServiceFacade
                .getDesignatedBodySlotTypes(designatedBodySlotTypes,
                        this, dndCharacter);
    }

    public final boolean resembles(final InventoryItem item) {
        if (item == null) {
            return Boolean.FALSE;
        }
        return item.getInventoryItemPrototype()
                == this.getInventoryItemPrototype();
    }

    public final boolean isCorrectlyWielded(final BodySlotType bodySlotType,
            final DndCharacter dndCharacter) {
        return this.getDesignatedBodySlotTypes(dndCharacter)
                .contains(bodySlotType);
    }

    @Override
    public final Boni getBoni() {
        return Boni.EMPTY_BONI;
    }

    @Override
    public final BonusSources getBonusSources() {
        final BonusSources bonusSources = new BonusSources();

        bonusSources.add(this.specialAbilities);

        return bonusSources;
    }

    @Override
    public final Observers getObservers() {
        return Observers.EMPTY_OBSERVERS;
    }

    @Override
    public final ObserverSources getObserverSources() {
        final ObserverSources observerSources = new ObserverSources();

        observerSources.add(this.specialAbilities);

        return observerSources;
    }

    public final SizeCategory getSize(final DndCharacter dndCharacter) {
        return this.inventoryItemServiceFacade.getSize(
                this.getSizeCategory(), this, dndCharacter);
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
