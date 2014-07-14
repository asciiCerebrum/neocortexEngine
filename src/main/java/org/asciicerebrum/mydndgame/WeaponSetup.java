package org.asciicerebrum.mydndgame;

/**
 *
 * @author species8472
 */
public class WeaponSetup {

    /**
     * Unique id of this weapon.
     */
    private String id;
    /**
     * The name of the weapon prototype.
     */
    private String name;
    /**
     * The size category of this weapon.
     */
    private String sizeCategory;

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

}
