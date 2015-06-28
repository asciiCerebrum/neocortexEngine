package org.asciicerebrum.neocortexengine.domain.ruleentities;

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
    private final List<DamageType> elements = new ArrayList<DamageType>();

    /**
     * @return the damageTypes
     */
    public final List<DamageType> getDamageTypes() {
        return elements;
    }

    /**
     * Adds a further element to the collection.
     *
     * @param damageType the element to add.
     */
    public final void addDamageType(final DamageType damageType) {
        this.elements.add(damageType);
    }

    /**
     * @param damageTypesInput the damageTypes to set
     */
    public final void setDamageTypes(final List<DamageType> damageTypesInput) {
        this.elements.clear();
        this.elements.addAll(damageTypesInput);
    }

    /**
     * @return the first damage type element of the collection.
     */
    public final DamageType getFirst() {
        if (!this.elements.isEmpty()) {
            return this.elements.get(0);
        }
        return null;
    }

}
