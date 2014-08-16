package org.asciicerebrum.mydndgame.interfaces.entities;

import java.util.List;

/**
 *
 * @author species8472
 */
public interface IWeapon extends IInventoryItem, Identifiable {

    /**
     * @return the encumbrance
     */
    IEncumbrance getEncumbrance();

    /**
     * @param encumbrance the encumbrance to set
     */
    void setEncumbrance(IEncumbrance encumbrance);

    /**
     * @return the proficiency
     */
    IProficiency getProficiency();

    /**
     * @param proficiency the proficiency to set
     */
    void setProficiency(IProficiency proficiency);

    /**
     * Two weapons resemble each other if they have the same name.
     *
     * @param weapon the weapon to check the resemblance with.
     * @return true if they have the same name.
     */
    Boolean resembles(IWeapon weapon);

    /**
     * @return the defaultCategories
     */
    List<IWeaponCategory> getDefaultCategories();

    /**
     * @param defaultCategories the defaultCategories to set
     */
    void setDefaultCategories(List<IWeaponCategory> defaultCategories);

}
