package org.asciicerebrum.mydndgame.domain.core.particles;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author species8472
 */
public abstract class LongParticle {

    /**
     * Initial value for hash code calculation.
     */
    private static final int INITIAL_NON_ZERO_ODD_NUMBER = 17;

    /**
     * Modifier for hash code calculation.
     */
    private static final int MULTIPLIER_NON_ZERO_ODD_NUMBER = 31;

    /**
     * The kind of arithmetic operation between two numbers.
     */
    public static enum Operation {

        /**
         * The simple addition with "+".
         */
        ADDITION {

                    /**
                     * {@inheritDoc}
                     */
                    @Override
                    final void operate(final LongParticle operandA,
                            final LongParticle operandB,
                            final LongParticle result) {
                        result.setValue(
                                operandA.getValue() + operandB.getValue());
                    }

                },
        /**
         * The simple multiplication with "*".
         */
        MULTIPLICATION {

                    /**
                     * {@inheritDoc}
                     */
                    @Override
                    final void operate(final LongParticle operandA,
                            final LongParticle operandB,
                            final LongParticle result) {
                        result.setValue(
                                operandA.getValue() * operandB.getValue());
                    }
                },
        /**
         * Use the minimum of both values.
         */
        MINIMUM {
                    @Override
                    final void operate(final LongParticle operandA,
                            final LongParticle operandB,
                            final LongParticle result) {
                        result.setValue(
                                Math.min(operandA.getValue(),
                                        operandB.getValue()));
                    }
                };

        /**
         * The abstract modificating method with two operands.
         *
         * @param operandA the first argument of the operation.
         * @param operandB the second argument of the operation.
         * @param result the result long particle whose value is set according
         * to the result of the operation.
         */
        abstract void operate(LongParticle operandA,
                LongParticle operandB, LongParticle result);
    }

    /**
     * The value of this long instance.
     */
    private long value = 0L;

    /**
     * Standard constructor which uses the default value of 0.
     */
    public LongParticle() {

    }

    /**
     * Creates an instance from a long primitive.
     *
     * @param valueInput the long to create the instance from.
     */
    public LongParticle(final long valueInput) {
        this.value = valueInput;
    }

    /**
     * Sets the value from a double particle. Its value is always rounded down
     * (floor).
     *
     * @param doubleParticle the double particle to create the instance from.
     */
    public final void setValue(final DoubleParticle doubleParticle) {
        this.value = (long) Math.floor(doubleParticle.getValue());
    }

    /**
     * @return the value
     */
    public final long getValue() {
        return value;
    }

    /**
     * @param valueInput the value to set
     */
    public final void setValue(final long valueInput) {
        this.value = valueInput;
    }

    /**
     * Compares this instance with a given long particle.
     *
     * @param lp the instance to compare this with.
     * @return result of the comparison.
     */
    public final int compareTo(final LongParticle lp) {
        return Long.valueOf(this.getValue()).compareTo(lp.getValue());
    }

    /**
     * Tests if this instance's value is less than that of a given long
     * particle.
     *
     * @param lp the given long particle.
     * @return true if less than, otherwise false.
     */
    public final boolean lessThan(final LongParticle lp) {
        return this.compareTo(lp) < 0;
    }

    /**
     * Tests if this instance's value is greater than that of a given long
     * particle.
     *
     * @param lp the given long particle.
     * @return true if greater than, otherwise false.
     */
    public final boolean greaterThan(final LongParticle lp) {
        return this.compareTo(lp) > 0;
    }

    /**
     * Tests if this instance's value is less than or equals that of a given
     * long particle.
     *
     * @param lp the given long particle.
     * @return true if less than or equals, otherwise false.
     */
    public final boolean lessThanOrEqualTo(final LongParticle lp) {
        return this.compareTo(lp) <= 0;
    }

    /**
     * Tests if this instance's value is greater than or equals that of a given
     * long particle.
     *
     * @param lp the given long particle.
     * @return true if greater than or equals, otherwise false.
     */
    public final boolean greaterThanOrEqualTo(final LongParticle lp) {
        return this.compareTo(lp) >= 0;
    }

    @Override
    public abstract boolean equals(final Object o);

    /**
     * Helper function for testing equality between two long particle instances.
     * Is used in classes extending this one.
     *
     * @param o the object to check equality with.
     * @return the result of the check.
     */
    protected final boolean equalsHelper(final Object o) {
        if (!(o instanceof LongParticle)) {
            return false;
        }
        if (o == this) {
            return true;
        }
        LongParticle oParticle = (LongParticle) o;
        return new EqualsBuilder()
                .append(value, oParticle.value)
                .isEquals();
    }

    @Override
    public abstract int hashCode();

    /**
     * Helper function for calculating the hash code. Is used in the classes
     * extending this one.
     *
     * @return the calculated hash code.
     */
    protected final int hashCodeHelper() {
        return new HashCodeBuilder(INITIAL_NON_ZERO_ODD_NUMBER,
                MULTIPLIER_NON_ZERO_ODD_NUMBER)
                .append(value)
                .toHashCode();
    }

    /**
     * Applies a given mathematical operation on the given long particle.
     * Another long particle instance is used as an operand.
     *
     * @param operation the operation to apply.
     * @param operand the operand of the application.
     * @param result the resulting long particle whose value is set according to
     * the operation result.
     */
    public final void applyOperation(final Operation operation,
            final LongParticle operand, final LongParticle result) {
        operation.operate(this, operand, result);
    }

}
