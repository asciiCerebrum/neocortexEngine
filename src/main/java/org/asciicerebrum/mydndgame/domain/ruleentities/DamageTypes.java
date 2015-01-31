package org.asciicerebrum.mydndgame.domain.ruleentities;

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
    private List<DamageType> elements = new ArrayList<DamageType>();

    /**
     * @return the damageTypes
     */
    public final List<DamageType> getDamageTypes() {
        return elements;
    }

    /**
     * @param damageTypesInput the damageTypes to set
     */
    public final void setDamageTypes(final List<DamageType> damageTypesInput) {
        this.elements = damageTypesInput;
    }

    public DamageType getFirst() {
        if (!this.elements.isEmpty()) {
            return this.elements.get(0);
        }
        return null;
    }

}
