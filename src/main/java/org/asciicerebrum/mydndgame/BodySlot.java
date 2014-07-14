package org.asciicerebrum.mydndgame;

/**
 *
 * @author species8472
 */
public class BodySlot {

    /**
     * The type of the body slot.
     */
    private BodySlotType bodySlotType;
    /**
     * What item is in the body slot.
     */
    private InventoryItem item;
    /**
     * The character this slot is associated with.
     */
    private DndCharacter holder;

    /**
     * @return the bodySlotType
     */
    public final BodySlotType getBodySlotType() {
        return bodySlotType;
    }

    /**
     * @param bodySlotTypeInput the bodySlotType to set
     */
    public final void setBodySlotType(final BodySlotType bodySlotTypeInput) {
        this.bodySlotType = bodySlotTypeInput;
    }

    /**
     * @return the item
     */
    public final InventoryItem getItem() {
        return item;
    }

    /**
     * @param itemInput the item to set
     */
    public final void setItem(final InventoryItem itemInput) {
        this.item = itemInput;
    }

    /**
     * @return the holder
     */
    public final DndCharacter getHolder() {
        return holder;
    }

    /**
     * @param holderInput the holder to set
     */
    public final void setHolder(final DndCharacter holderInput) {
        this.holder = holderInput;
    }

}
