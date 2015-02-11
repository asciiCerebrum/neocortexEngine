package org.asciicerebrum.mydndgame.domain.core.particles;

/**
 *
 * @author species8472
 */
public class DiceRoll extends LongParticle {

    /**
     * Creates a dice roll instance from a long primitive.
     *
     * @param value the long to create the instance from.
     */
    public DiceRoll(final long value) {
        this.setValue(value);
    }

    /**
     * Generates a new instance with a value as the sum of the original instance
     * and an additionally given bonus value.
     *
     * @param bonusValue the value to add to the dice roll.
     * @return the new instance with the summated value.
     */
    public final DiceRoll add(final BonusValue bonusValue) {
        return new DiceRoll(this.getValue() + bonusValue.getValue());
    }

    /**
     * Generates a dice roll which has the maximum value of two given dice
     * rolls.
     *
     * @param dr1 the first dice roll.
     * @param dr2 the second dice roll.
     * @return a new dice roll with the maximum value of both.
     */
    public static final DiceRoll max(final DiceRoll dr1, final DiceRoll dr2) {
        if (dr1.getValue() >= dr2.getValue()) {
            return dr1;
        }
        return dr2;
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
