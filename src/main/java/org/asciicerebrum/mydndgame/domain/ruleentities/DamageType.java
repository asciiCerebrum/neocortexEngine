package org.asciicerebrum.mydndgame.domain.ruleentities;

import java.util.List;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSources;

/**
 *
 * @author species8472
 */
public class DamageType extends Feature {

    /**
     * This damage type could consist of futher subdamage types which all apply
     * at once. E.g. morningstar: Bludgeoning and piercing.
     */
    private List<DamageType> compoundDamageTypes;

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

    @Override
    public final BonusSources getBonusSources() {
        return BonusSources.EMPTY_BONUSSOURCES;
    }
}
