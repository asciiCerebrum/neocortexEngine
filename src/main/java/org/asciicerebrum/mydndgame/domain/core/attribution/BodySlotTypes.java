package org.asciicerebrum.mydndgame.domain.core.attribution;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author species8472
 */
public class BodySlotTypes {

    private final List<BodySlotType> bodySlotTypes
            = new ArrayList<BodySlotType>();

    public final void add(final BodySlotType bodySlotType) {
        this.bodySlotTypes.add(bodySlotType);
    }

    public final boolean contains(final BodySlotType bodySlotType) {
        return this.bodySlotTypes.contains(bodySlotType);
    }

}
