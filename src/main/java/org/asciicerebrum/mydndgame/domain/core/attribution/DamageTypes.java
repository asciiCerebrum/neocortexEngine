package org.asciicerebrum.mydndgame.domain.core.attribution;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author species8472
 */
public class DamageTypes {

    /**
     * The list of damage types.
     */
    private List<DamageType> damageTypes = new ArrayList<DamageType>();

    /**
     * @return the damageTypes
     */
    public final List<DamageType> getDamageTypes() {
        return damageTypes;
    }

    /**
     * @param damageTypesInput the damageTypes to set
     */
    public final void setDamageTypes(final List<DamageType> damageTypesInput) {
        this.damageTypes = damageTypesInput;
    }

    public DamageType getFirst() {
        if (!this.damageTypes.isEmpty()) {
            return this.damageTypes.get(0);
        }
        return null;
    }

}
