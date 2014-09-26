package org.asciicerebrum.mydndgame.interfaces.entities;

import java.util.List;
import org.asciicerebrum.mydndgame.DamageType;

/**
 *
 * @author species8472
 */
public interface IDamageType extends Identifiable {

    /**
     * @return the compoundDamageTypes
     */
    List<DamageType> getCompoundDamageTypes();

    /**
     * @param compoundDamageTypesInput the compoundDamageTypes to set
     */
    void setCompoundDamageTypes(List<DamageType> compoundDamageTypesInput);

}
