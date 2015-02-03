package org.asciicerebrum.mydndgame.domain.rules;

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
    private List<BodySlot> elements = new ArrayList<BodySlot>();

    /**
     * @param bodySlotsInput the bodySlots to set
     */
    public final void setBodySlots(final List<BodySlot> bodySlotsInput) {
        this.elements = bodySlotsInput;
    }

    public final boolean contains(final BodySlot bodySlot) {
        return this.elements.contains(bodySlot);
    }

    public final boolean containsType(final BodySlotType bodySlotType) {
        for (BodySlot bodySlot : elements) {
            if (bodySlot.isOfType(bodySlotType)) {
                return true;
            }
        }
        return false;
    }

    public final void add(final BodySlot bodySlot) {
        this.elements.add(bodySlot);
    }

    public final Iterator<BodySlot> iterator() {
        return this.elements.iterator();
    }

}
