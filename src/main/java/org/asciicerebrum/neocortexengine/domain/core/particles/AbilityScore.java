package org.asciicerebrum.neocortexengine.domain.core.particles;

/**
 *
 * @author species8472
 */
public class AbilityScore extends LongParticle {

    /**
     * Constructs the ability score from a string.
     *
     * @param abilityValueString the ability value as string.
     */
    public AbilityScore(final String abilityValueString) {
        this.setValue(abilityValueString);
    }

    /**
     * Constructs the ability score from a long value.
     *
     * @param abilityValueLong the ability value as long.
     */
    public AbilityScore(final long abilityValueLong) {
        this.setValue(abilityValueLong);
    }

    /**
     * Constructs the ability score with default value.
     *
     */
    public AbilityScore() {
    }

    /**
     *
     * @param abilityValueString the ability value as string.
     */
    public final void setValue(final String abilityValueString) {
        super.setValue(Long.parseLong(abilityValueString));
    }

    /**
     * Modifies the ability score by adding the ability score to its value.
     *
     * @param abilityValue the ability value to add.
     */
    public final void add(final AbilityScore abilityValue) {
        this.setValue(this.getValue() + abilityValue.getValue());
    }

    /**
     * Modifies the ability score by adding the given bonus to its value.
     *
     * @param bonusValue the bonus value to add.
     */
    public final void add(final BonusValue bonusValue) {
        if (bonusValue != null) {
            this.setValue(this.getValue() + bonusValue.getValue());
        }
    }

    @Override
    public final boolean equals(final Object o) {
        if (!(o instanceof AbilityScore)) {
            return false;
        }
        return this.equalsHelper(o);
    }

    @Override
    public final int hashCode() {
        return this.hashCodeHelper();
    }

    @Override
    public final LongParticle getNewInstanceOfSameType() {
        return new AbilityScore();
    }

}
