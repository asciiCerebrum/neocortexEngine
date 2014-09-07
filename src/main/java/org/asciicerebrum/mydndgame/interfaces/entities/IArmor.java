package org.asciicerebrum.mydndgame.interfaces.entities;

/**
 *
 * @author species8472
 */
public interface IArmor extends IInventoryItem, Identifiable {

    /**
     * @return the armorCategory
     */
    IArmorCategory getArmorCategory();

    /**
     * @param armorCategory the armorCategory to set
     */
    void setArmorCategory(IArmorCategory armorCategory);

    /**
     * @return the maxDexBonus
     */
    Long getMaxDexBonus();

    /**
     * @param maxDexBonus the maxDexBonus to set
     */
    void setMaxDexBonus(Long maxDexBonus);

    /**
     * @return the armorCheckPenalty
     */
    Long getArmorCheckPenalty();

    /**
     * @param armorCheckPenalty the armorCheckPenalty to set
     */
    void setArmorCheckPenalty(Long armorCheckPenalty);

    /**
     * @return the arcaneSpellFailure
     */
    Long getArcaneSpellFailure();

    /**
     * @param arcaneSpellFailure the arcaneSpellFailure to set
     */
    void setArcaneSpellFailure(Long arcaneSpellFailure);

    /**
     * @return the proficiency
     */
    IProficiency getProficiency();

    /**
     * @param proficiency the proficiency to set
     */
    void setProficiency(IProficiency proficiency);

}
