package org.asciicerebrum.neocortexengine.domain.ruleentities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author species8472
 */
public class BodySlots {

    /**
     * The list of body slots.
     */
    private final List<BodySlot> elements = new ArrayList<BodySlot>();

    /**
     * @param bodySlotsInput the bodySlots to set
     */
    public final void setBodySlots(final List<BodySlot> bodySlotsInput) {
        this.elements.addAll(bodySlotsInput);
    }

    /**
     * Tests if the collection contains the given body slot.
     *
     * @param bodySlot the body slot to check.
     * @return true if part of the collection, false otherwise.
     */
    public final boolean contains(final BodySlot bodySlot) {
        return this.elements.contains(bodySlot);
    }

    /**
     * Tests if there is at least one body slot being of the given body slot
     * type.
     *
     * @param bodySlotType the type in question.
     * @return true if this slot type can be found in the collection, false
     * otherwise.
     */
    public final boolean containsType(final BodySlotType bodySlotType) {
        for (BodySlot bodySlot : elements) {
            if (bodySlot.isOfType(bodySlotType)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a further body slot to the collection.
     *
     * @param bodySlot the body slot to add.
     */
    public final void add(final BodySlot bodySlot) {
        this.elements.add(bodySlot);
    }

    /**
     * Iterator over the collection of body slots.
     *
     * @return the iterator.
     */
    public final Iterator<BodySlot> iterator() {
        return this.elements.iterator();
    }

}
