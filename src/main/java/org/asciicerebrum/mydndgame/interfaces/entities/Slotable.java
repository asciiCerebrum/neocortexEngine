package org.asciicerebrum.mydndgame.interfaces.entities;

/**
 * Something that has a slot type. E.g. a body slot.
 *
 * @author Tabea Raab
 */
public interface Slotable {

    /**
     *
     * @return the type of this (body) slot.
     */
    IBodySlotType getBodySlotType();

    /**
     *
     * @return the item stored in this slot.
     */
    IInventoryItem getItem();

    /**
     * Store the item in the slot.
     *
     * @param item the inventory item in question.
     */
    void setItem(IInventoryItem item);
}
