package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.interfaces.entities.IInventoryItem;

/**
 * Abstract prototype class for everything a DND Character can carry.
 *
 * @author species8472
 */
public abstract class InventoryItem implements IInventoryItem {

    /**
     * Unique id String. This is not set in the prototype spring bean
     * definition. So this value has to be determined on creation time.
     */
    private String id;

    /**
     * Name of this prototypicl object. E.g. longsword.
     */
    private String name;

    /**
     * Weight of the object.
     */
    private Long weight;

    /**
     * Price always in gp of the object.
     */
    private Long cost;

    /**
     * Size category of the object.
     */
    private SizeCategory size;

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
     * @return the weight
     */
    public final Long getWeight() {
        return weight;
    }

    /**
     * @param weightInput the weight to set
     */
    public final void setWeight(final Long weightInput) {
        this.weight = weightInput;
    }

    /**
     * @return the cost
     */
    public final Long getCost() {
        return this.cost;
    }

    /**
     * @param costInput the cost to set
     */
    public final void setCost(final Long costInput) {
        this.cost = costInput;
    }

    /**
     * @return the size
     */
    public final SizeCategory getSize() {
        return size;
    }

    /**
     * @param sizeInput the size to set
     */
    public final void setSize(final SizeCategory sizeInput) {
        this.size = sizeInput;
    }
}
