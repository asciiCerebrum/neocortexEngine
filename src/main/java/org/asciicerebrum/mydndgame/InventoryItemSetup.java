package org.asciicerebrum.mydndgame;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author species8472
 */
public abstract class InventoryItemSetup {

    /**
     * Unique id of this item.
     */
    private String id;
    /**
     * The name of the item prototype.
     */
    private String name;
    /**
     * The size category of this item.
     */
    private String sizeCategory;
    /**
     * Mwk, magic, material like cold iron etc.
     */
    private List<String> specialAbilities = new ArrayList<String>();

    /**
     * @return the id
     */
    public final String getId() {
        return id;
    }

    /**
     * @param idInput the id to set
     */
    public final void setId(final String idInput) {
        this.id = idInput;
    }

    /**
     * @return the name
     */
    public final String getName() {
        return name;
    }

    /**
     * @param nameInput the name to set
     */
    public final void setName(final String nameInput) {
        this.name = nameInput;
    }

    /**
     * @return the sizeCategory
     */
    public final String getSizeCategory() {
        return sizeCategory;
    }

    /**
     * @param sizeCategoryInput the sizeCategory to set
     */
    public final void setSizeCategory(final String sizeCategoryInput) {
        this.sizeCategory = sizeCategoryInput;
    }

    /**
     * @return the itemFeatures
     */
    public final List<String> getSpecialAbilities() {
        return this.specialAbilities;
    }

    /**
     * @param specialAbilitiesInput the itemFeatures to set
     */
    public final void setSpecialAbilities(
            final List<String> specialAbilitiesInput) {
        this.specialAbilities = specialAbilitiesInput;
    }
}
