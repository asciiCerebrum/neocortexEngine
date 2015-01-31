package org.asciicerebrum.mydndgame.domain.rules.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author species8472
 */
public class BodySlotTypes {

    private final List<BodySlotType> elements
            = new ArrayList<BodySlotType>();

    public final void add(final BodySlotType bodySlotType) {
        this.elements.add(bodySlotType);
    }

    public final boolean contains(final BodySlotType bodySlotType) {
        return this.elements.contains(bodySlotType);
    }

}
