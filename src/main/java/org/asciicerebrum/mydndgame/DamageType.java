package org.asciicerebrum.mydndgame;

import java.util.List;
import org.asciicerebrum.mydndgame.interfaces.entities.IDamageType;

/**
 *
 * @author species8472
 */
public class DamageType implements IDamageType {

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
     * {@inheritDoc}
     */
    @Override
    public final List<DamageType> getCompoundDamageTypes() {
        return compoundDamageTypes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setCompoundDamageTypes(
            final List<DamageType> compoundDamageTypesInput) {
        this.compoundDamageTypes = compoundDamageTypesInput;
    }

}
