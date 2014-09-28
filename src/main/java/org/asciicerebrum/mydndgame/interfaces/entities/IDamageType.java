package org.asciicerebrum.mydndgame.interfaces.entities;

import java.util.List;

/**
 *
 * @author species8472
 */
public interface IDamageType extends Identifiable {

    /**
     * @return the compoundDamageTypes
     */
    List<IDamageType> getCompoundDamageTypes();

    /**
     * @param compoundDamageTypesInput the compoundDamageTypes to set
     */
    void setCompoundDamageTypes(List<IDamageType> compoundDamageTypesInput);

}
