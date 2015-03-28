package org.asciicerebrum.mydndgame.domain.game;

import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Boni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSources;
import org.asciicerebrum.mydndgame.domain.core.particles.Cost;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSource;
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
    private SpecialAbilities specialAbilities;

    /**
     * All the conditions the item is currently in. E.g. a dogslicer can be
     * broken.
     */
    private Conditions conditions;

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
     * @param specialAbilitiesInput the specialAbilities to set
     */
    public final void setSpecialAbilities(
            final SpecialAbilities specialAbilitiesInput) {
        this.specialAbilities = specialAbilitiesInput;
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
    public final Boni getBoni() {
        return Boni.EMPTY_BONI;
    }

    @Override
    public final BonusSources getBonusSources() {
        final BonusSources bonusSources = new BonusSources();

        bonusSources.add(this.specialAbilities);
        bonusSources.add(this.inventoryItemPrototype);
        bonusSources.add(this.conditions);

        return bonusSources;
    }

    /**
     * @return the conditions
     */
    public final Conditions getConditions() {
        return conditions;
    }

    /**
     * @param conditionsInput the conditions to set
     */
    public final void setConditions(final Conditions conditionsInput) {
        this.conditions = conditionsInput;
    }

}
