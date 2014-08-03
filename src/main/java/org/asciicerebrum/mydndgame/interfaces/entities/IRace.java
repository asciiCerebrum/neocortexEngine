package org.asciicerebrum.mydndgame.interfaces.entities;

import java.util.List;

/**
 *
 * @author Tabea Raab
 */
public interface IRace {

    /**
     * @return the providedBodySlotTypes.
     */
    List<IBodySlotType> getProvidedBodySlotTypes();

    /**
     * @param providedBodySlotTypesInput the providedBodySlotTypes to set.
     */
    void setProvidedBodySlotTypes(
            List<IBodySlotType> providedBodySlotTypesInput);

    /**
     * @return the size.
     */
    ISizeCategory getSize();

}
