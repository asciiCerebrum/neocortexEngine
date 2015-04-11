package org.asciicerebrum.mydndgame.domain.core.particles;

/**
 *
 * @author species8472
 */
public final class CombatRoundNumber extends LongParticle
        implements Comparable {

    /**
     * Creates an instance from a string.
     *
     * @param stringInput the string to create the combat round number from.
     */
    public CombatRoundNumber(final String stringInput) {
        this.setValue(stringInput);
    }

    /**
     * Creates an instance from a long primitive.
     *
     * @param longInput the long to create the combat round number from.
     */
    public CombatRoundNumber(final long longInput) {
        this.setValue(longInput);
    }

    /**
     * Creates an instance from a long primitive default value.
     *
     */
    public CombatRoundNumber() {
    }

    /**
     * Sets the value of the combat round number.
     *
     * @param stringInput the string to set the value from.
     */
    public void setValue(final String stringInput) {
        this.setValue(Long.parseLong(stringInput));
    }

    @Override
    public int compareTo(final Object object) {
        final CombatRoundNumber crn = (CombatRoundNumber) object;
        return Long.valueOf(this.getValue())
                .compareTo(crn.getValue());
    }

    @Override
    public boolean equals(final Object o) {
        return this.equalsHelper(o);
    }

    @Override
    public int hashCode() {
        return this.hashCodeHelper();
    }

    @Override
    public LongParticle getNewInstanceOfSameType() {
        return new CombatRoundNumber();
    }

}
