package org.asciicerebrum.mydndgame.domain.ruleentities.composition;

import org.asciicerebrum.mydndgame.domain.ruleentities.Ability;
import org.asciicerebrum.mydndgame.domain.core.particles.AbilityScore;

/**
 *
 * @author species8472
 */
public class BaseAbilityEntry {

    /**
     * The ability this entry is associated with.
     */
    private Ability ability;

    /**
     * The ability score of this entry.
     */
    private AbilityScore abilityValue;

    /**
     * @return the ability
     */
    public final Ability getAbility() {
        return ability;
    }

    /**
     * @param abilityInput the ability to set
     */
    public final void setAbility(final Ability abilityInput) {
        this.ability = abilityInput;
    }

    /**
     * @return the abilityValue
     */
    public final AbilityScore getAbilityValue() {
        return abilityValue;
    }

    /**
     * @param abilityValueInput the abilityValue to set
     */
    public final void setAbilityValue(final AbilityScore abilityValueInput) {
        this.abilityValue = abilityValueInput;
    }

}
