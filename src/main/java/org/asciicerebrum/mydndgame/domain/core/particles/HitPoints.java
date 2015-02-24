package org.asciicerebrum.mydndgame.domain.core.particles;

/**
 *
 * @author species8472
 */
public class HitPoints extends LongParticle {

    /**
     * Default constructor with default value of 0.
     */
    public HitPoints() {
    }

    /**
     * Creates an instance from a long.
     *
     * @param longInput the long to create the instance from.
     */
    public HitPoints(final long longInput) {
        this.setValue(longInput);
    }

    /**
     * Creates an instance from a string.
     *
     * @param hitPointsString the string to create the instance from.
     */
    public HitPoints(final String hitPointsString) {
        this.setValue(hitPointsString);
    }

    /**
     * Sets the value from a string.
     *
     * @param hitPointsString the string.
     */
    public final void setValue(final String hitPointsString) {
        super.setValue(Long.parseLong(hitPointsString));
    }

    /**
     * Modifies this instance by adding the given hitpoints to its value.
     *
     * @param hitPoints the hit points to add.
     */
    public final void add(final HitPoints hitPoints) {
        this.setValue(this.getValue() + hitPoints.getValue());
    }

    /**
     * Modifies this instance by adding the given bonusValue to its value.
     *
     * @param bonusValue the bonus value to add.
     */
    public final void add(final BonusValue bonusValue) {
        this.setValue(this.getValue() + bonusValue.getValue());
    }

    /**
     * Modifies this instance by adding the given dice sides to its value.
     *
     * @param diceSides the dice sides to add.
     */
    public final void add(final DiceSides diceSides) {
        this.setValue(this.getValue() + diceSides.getValue());
    }

    /**
     * Modifies this instance by multiplying the given bonus value to its value.
     *
     * @param bonusValue the bonus value to multiply with.
     */
    public final void multiply(final BonusValue bonusValue) {
        this.setValue(this.getValue() * bonusValue.getValue());
    }

    /**
     * Modifies this instance by subtracting the given hit points from its
     * value.
     *
     * @param hitPoints the hitpoints to subtract.
     */
    public final void subtract(final HitPoints hitPoints) {
        this.setValue(this.getValue() - hitPoints.getValue());
    }

    /**
     * Modifies this instance by subtracting the given dice roll from its value.
     *
     * @param diceRoll the dice roll to subtract.
     */
    public final void subtract(final DiceRoll diceRoll) {
        this.setValue(this.getValue() - diceRoll.getValue());
    }

    @Override
    public final boolean equals(final Object o) {
        return this.equalsHelper(o);
    }

    @Override
    public final int hashCode() {
        return this.hashCodeHelper();
    }
}
