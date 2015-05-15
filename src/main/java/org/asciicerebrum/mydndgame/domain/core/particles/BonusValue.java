package org.asciicerebrum.mydndgame.domain.core.particles;

/**
 *
 * @author species8472
 */
public class BonusValue extends LongParticle {

    /**
     * Creates a bonus value without any actual value. As it extends
     * LongParticle, its default value is 0.
     */
    public BonusValue() {

    }

    /**
     * Creates the instance from a string.
     *
     * @param longString the string.
     */
    public BonusValue(final String longString) {
        this.setValue(longString);
    }

    /**
     * Creates a bonus value from a long.
     *
     * @param valueInput the long primitive.
     */
    public BonusValue(final long valueInput) {
        this.setValue(valueInput);
    }

    /**
     * Creates a new bonus value from the value of another bonus value. De facto
     * makes a clone.
     *
     * @param bonusValueInput the bonus value to copy.
     */
    public BonusValue(final BonusValue bonusValueInput) {
        this.setValue(bonusValueInput.getValue());
    }

    /**
     * Sets the value from a string.
     *
     * @param longString the string.
     */
    public final void setValue(final String longString) {
        super.setValue(Long.parseLong(longString));
    }

    /**
     * Creates a new bonus value whose value is the difference between this
     * instance and a given other bonus value.
     *
     * @param subtrahend the bonus value to substract.
     * @return the new bonus value with the difference value.
     */
    public final BonusValue subtract(final BonusValue subtrahend) {
        return new BonusValue(this.getValue() - subtrahend.getValue());
    }

    /**
     * Creates a new bonus value whose value is the sum of this instance and a
     * given other bonus value.
     *
     * @param summand the bonus value to add.
     * @return the new bonus value with the summated value.
     */
    public final BonusValue add(final BonusValue summand) {
        long sum = this.getValue() + summand.getValue();
        return new BonusValue(sum);
    }

    /**
     * Creates a new bonus value whose value is the product of this instance and
     * a given advancement number. This is for example needed when calculating
     * the max hp of a character.
     *
     * @param advNumber the advancement number to multiply with.
     * @return the product of both.
     */
    public final BonusValue multiply(final AdvancementNumber advNumber) {
        long product = this.getValue() * advNumber.getValue();
        return new BonusValue(product);
    }

    /**
     * Tests for the non-zeroness of this instance of a bonus value.
     *
     * @return true if not zero. False otherwise.
     */
    public final boolean isNonZero() {
        return this.getValue() != 0L;
    }

    /**
     * Applies an operation on this instance with a given double particle of any
     * kind. It affects the value of this bonus value, e.g. making a quotient
     * with rounding, etc.
     *
     * @param operation the operation to perform. It is a mathematical one.
     * @param operand the other operand for the operation, e.g. a divisor, etc.
     */
    public final void applyOperation(final DoubleParticle.Operation operation,
            final DoubleParticle operand) {
        operation.operate(
                new DoubleParticle(this.getValue()), operand, this);
    }

    @Override
    public final boolean equals(final Object o) {
        if (!(o instanceof BonusValue)) {
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
        return new BonusValue();
    }

}
