package org.asciicerebrum.mydndgame.interfaces.entities;

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

}
