package org.asciicerebrum.neocortexengine.domain.ruleentities;

import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.ContextBoni;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.source.UniqueEntityResolver;

/**
 *
 * @author species8472
 */
public class DamageType extends Feature {

    /**
     * This damage type could consist of futher subdamage types which all apply
     * at once. E.g. morningstar: Bludgeoning and piercing.
     */
    private DamageTypes compoundDamageTypes;

    /**
     * @return the compoundDamageTypes
     */
    public final DamageTypes getCompoundDamageTypes() {
        return compoundDamageTypes;
    }

    /**
     * @param compoundDamageTypesInput the compoundDamageTypes to set
     */
    public final void setCompoundDamageTypes(
            final DamageTypes compoundDamageTypesInput) {
        this.compoundDamageTypes = compoundDamageTypesInput;
    }

    @Override
    public final ContextBoni getBoni(final UniqueEntity context,
            final UniqueEntityResolver resolver) {
        return this.getFeatureBoni(context);
    }

}
