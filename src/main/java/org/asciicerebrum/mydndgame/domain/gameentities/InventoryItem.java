package org.asciicerebrum.mydndgame.domain.gameentities;

import org.asciicerebrum.mydndgame.domain.core.attribution.BodySlotTypes;
import org.asciicerebrum.mydndgame.domain.ruleentities.SizeCategory;
import org.asciicerebrum.mydndgame.domain.ruleentities.SpecialAbilities;
import org.asciicerebrum.mydndgame.domain.core.mechanics.Boni;
import org.asciicerebrum.mydndgame.domain.core.mechanics.BonusSources;
import org.asciicerebrum.mydndgame.domain.core.particles.Cost;
import org.asciicerebrum.mydndgame.domain.gameentities.prototypes.InventoryItemPrototype;

/**
 *
 * @author species8472
 */
public abstract class InventoryItem extends UniqueEntity {

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
    public SizeCategory getSizeCategory() {
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

    public final Cost getBaseCost() {
        return this.inventoryItemPrototype.getBaseCost();
    }

    public final BodySlotTypes getBaseDesignatedBodySlotTypes() {
        return this.inventoryItemPrototype.getDesignatedBodySlotTypes();
    }

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

        return bonusSources;
    }

}
