package org.asciicerebrum.mydndgame;

import java.util.List;

/**
 *
 * @author species8472
 */
public class DamageType {

    /**
     * Unique id of the damage type.
     */
    private String id;

    /**
     * This damage type could consist of futher subdamage types which all apply
     * at once. E.g. morningstar: Bludgeoning and piercing.
     */
    private List<DamageType> compoundDamageTypes;

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
     * @return the compoundDamageTypes
     */
    public final List<DamageType> getCompoundDamageTypes() {
        return compoundDamageTypes;
    }

    /**
     * @param compoundDamageTypesInput the compoundDamageTypes to set
     */
    public final void setCompoundDamageTypes(
            final List<DamageType> compoundDamageTypesInput) {
        this.compoundDamageTypes = compoundDamageTypesInput;
    }

}
