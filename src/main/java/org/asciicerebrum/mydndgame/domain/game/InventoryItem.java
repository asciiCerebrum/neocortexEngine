package org.asciicerebrum.mydndgame.domain.game;

import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.particles.Cost;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.ContextBoni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSource;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.UniqueEntityResolver;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.mydndgame.domain.ruleentities.BodySlotTypes;
import org.asciicerebrum.mydndgame.domain.ruleentities.InventoryItemPrototype;
import org.asciicerebrum.mydndgame.domain.ruleentities.SizeCategory;
import org.asciicerebrum.mydndgame.domain.ruleentities.SpecialAbilities;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.Conditions;

/**
 *
 * @author species8472
 */
public abstract class InventoryItem extends UniqueEntity
        implements BonusSource, ObserverSource {

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
    private final SpecialAbilities specialAbilities = new SpecialAbilities();

    /**
     * All the conditions the item is currently in. E.g. a dogslicer can be
     * broken.
     */
    private final Conditions conditions = new Conditions();

    /**
     * @return the inventoryItemPrototype
     */
    public final InventoryItemPrototype getInventoryItemPrototype() {
        return inventoryItemPrototype;
    }

    /**
     * @param inventoryItemPrototypeInput the inventoryItemPrototype to set
     */
    public final void setInventoryItemPrototype(
            final InventoryItemPrototype inventoryItemPrototypeInput) {
        this.inventoryItemPrototype = inventoryItemPrototypeInput;
    }

    /**
     * @return the sizeCategory
     */
    public final SizeCategory getSizeCategory() {
        return sizeCategory;
    }

    /**
     * @param sizeCategoryInput the sizeCategory to set
     */
    public final void setSizeCategory(final SizeCategory sizeCategoryInput) {
        this.sizeCategory = sizeCategoryInput;
    }

    /**
     * @return the specialAbilities
     */
    public final SpecialAbilities getSpecialAbilities() {
        return specialAbilities;
    }

    /**
     * @return the base cost from the prototype.
     */
    public final Cost getBaseCost() {
        return this.getInventoryItemPrototype().getBaseCost();
    }

    /**
     * @return the base designated body slot types from the prototype.
     */
    public final BodySlotTypes getBaseDesignatedBodySlotTypes() {
        return this.getInventoryItemPrototype().getDesignatedBodySlotTypes();
    }

    /**
     * Tests if this instance resembles a given inventory item. It checks if
     * they originate from the same prototype.
     *
     * @param item the item to check resemblence with.
     * @return true if they have the same prototype, false otherwise.
     */
    public final boolean resembles(final InventoryItem item) {
        if (item == null) {
            return Boolean.FALSE;
        }
        return item.getInventoryItemPrototype()
                == this.getInventoryItemPrototype();
    }

    @Override
    public final ContextBoni getBoni(final UniqueEntity context,
            final UniqueEntityResolver resolver) {
        final ContextBoni contextBoni = new ContextBoni();

        contextBoni.add(this.specialAbilities.getBoni(this, resolver));
        contextBoni.add(this.getInventoryItemPrototype()
                .getBoni(this, resolver));
        contextBoni.add(this.conditions.getBoni(this, resolver));

        return contextBoni;
    }

    /**
     * @return the conditions
     */
    public final Conditions getConditions() {
        return conditions;
    }

}
