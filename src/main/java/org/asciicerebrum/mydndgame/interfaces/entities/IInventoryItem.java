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
     * @return the size
     */
    ISizeCategory getSize();

}
