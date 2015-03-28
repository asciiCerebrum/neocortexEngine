package org.asciicerebrum.mydndgame.domain.ruleentities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author species8472
 */
public class BodySlotTypes {

    /**
     * Central collection of body slot types.
     */
    private final List<BodySlotType> elements
            = new ArrayList<BodySlotType>();

    /**
     * Adds a further body slot type to the collection.
     *
     * @param bodySlotType the body slot type to add.
     */
    public final void add(final BodySlotType bodySlotType) {
        this.elements.add(bodySlotType);
    }

    /**
     * Clears the list first.
     *
     * @param bodySlotTypes the collection of body slot types to set.
     */
    public final void setBodySlotTypes(final List<BodySlotType> bodySlotTypes) {
        this.elements.clear();
        this.elements.addAll(bodySlotTypes);
    }

    /**
     * Tests if the collection contains the given body slot type.
     *
     * @param bodySlotType the body slot type in question.
     * @return true if the body slot type is part of the list, false otherwise.
     */
    public final boolean contains(final BodySlotType bodySlotType) {
        return this.elements.contains(bodySlotType);
    }

}
