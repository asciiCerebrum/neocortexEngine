package org.asciicerebrum.mydndgame.interfaces.entities;

import java.util.List;

/**
 *
 * @author Tabea Raab
 */
public interface IRace {

    List<IBodySlotType> getProvidedBodySlotTypes();

    void setProvidedBodySlotTypes(
            List<IBodySlotType> providedBodySlotTypesInput);

}
