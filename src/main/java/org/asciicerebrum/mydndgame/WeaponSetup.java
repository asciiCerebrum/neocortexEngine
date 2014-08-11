package org.asciicerebrum.mydndgame;

import java.util.ArrayList;
import java.util.List;

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
     * @return the weaponFeatures
     */
    public final List<String> getSpecialAbilities() {
        return this.specialAbilities;
    }

    /**
     * @param specialAbilitiesInput the weaponFeatures to set
     */
    public final void setSpecialAbilities(
            final List<String> specialAbilitiesInput) {
        this.specialAbilities = specialAbilitiesInput;
    }

}
