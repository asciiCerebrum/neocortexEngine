package org.asciicerebrum.mydndgame.interfaces.entities;

import java.util.List;

/**
 *
 * @author Tabea Raab
 */
public interface IInventoryItem extends Identifiable {

    /**
     * @return the name
     */
    String getName();

    /**
     * @param name the name to set
     */
    void setName(String name);

    /**
     * @return the size
     */
    ISizeCategory getSize();

    /**
     * @param sizeCategory the size to set
     */
    void setSize(ISizeCategory sizeCategory);

    /**
     * @return the cost
     */
    Long getBaseCost();

    /**
     * @param baseCost the cost to set
     */
    void setBaseCost(Long baseCost);

    /**
     *
     * @return the effective price of the item, including special abilities like
     * mwk or magic, etc.
     */
    Long getCost();

    /**
     * @return the specialAbilities
     */
    List<ISpecialAbility> getSpecialAbilities();

    /**
     * @param specialAbilities the specialAbilities to set
     */
    void setSpecialAbilities(List<ISpecialAbility> specialAbilities);

    /**
     * Two items resemble each other if they have the same name.
     *
     * @param item the weapon to check the resemblance with.
     * @return true if they have the same name.
     */
    Boolean resembles(IInventoryItem item);

}
