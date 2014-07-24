package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.interfaces.entities.IBodySlotType;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.IInventoryItem;
import org.asciicerebrum.mydndgame.interfaces.entities.Slotable;

/**
 *
 * @author species8472
 */
public class BodySlot implements Slotable {

    /**
     * The type of the body slot.
     */
    private IBodySlotType bodySlotType;
    /**
     * What item is in the body slot.
     */
    private IInventoryItem item;
    /**
     * The character this slot is associated with.
     */
    private ICharacter holder;

    /**
     * {@inheritDoc}
     */
    @Override
    public final IBodySlotType getBodySlotType() {
        return bodySlotType;
    }

    /**
     * @param bodySlotTypeInput the bodySlotType to set
     */
    public final void setBodySlotType(final IBodySlotType bodySlotTypeInput) {
        this.bodySlotType = bodySlotTypeInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final IInventoryItem getItem() {
        return item;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setItem(final IInventoryItem itemInput) {
        this.item = itemInput;
    }

    /**
     * @return the holder
     */
    public final ICharacter getHolder() {
        return holder;
    }

    /**
     * @param holderInput the holder to set
     */
    public final void setHolder(final ICharacter holderInput) {
        this.holder = holderInput;
    }

}
