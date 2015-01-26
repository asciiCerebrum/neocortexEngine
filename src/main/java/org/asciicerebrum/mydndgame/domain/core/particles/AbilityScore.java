package org.asciicerebrum.mydndgame.domain.core.particles;

/**
 *
 * @author species8472
 */
public class AbilityScore extends LongParticle {
    
    public AbilityScore(final String abilityValueString) {
        this.setValue(abilityValueString);
    }
    
    public AbilityScore(final long abilityValueLong) {
        this.setValue(abilityValueLong);
    }
    
    public final void setValue(final String abilityValueString) {
        super.setValue(Long.parseLong(abilityValueString));
    }
    
    public final void add(final AbilityScore abilityValue) {
        this.setValue(this.getValue() + abilityValue.getValue());
    }
    
    public final void add(final BonusValue bonusValue) {
        this.setValue(this.getValue() + bonusValue.getValue());
    }
    
}
