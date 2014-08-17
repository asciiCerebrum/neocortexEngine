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
     * Checks if the given attack mode is contained in the weapon's list of
     * weapon categories.
     *
     * @param attackMode the attack mode in question.
     * @return the compatibility of this attack mode.
     */
    Boolean isAttackModeCompatible(IWeaponCategory attackMode);

    /**
     * @return the defaultCategories
     */
    List<IWeaponCategory> getDefaultCategories();

    /**
     * @param defaultCategories the defaultCategories to set
     */
    void setDefaultCategories(List<IWeaponCategory> defaultCategories);

}
