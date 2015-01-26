package org.asciicerebrum.mydndgame.domain.core.particles;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author species8472
 */
public class LongParticle {

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
                    LongParticle operate(final LongParticle operandA,
                            final LongParticle operandB) {
                        return new LongParticle(
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
                    LongParticle operate(final LongParticle operandA,
                            final LongParticle operandB) {
                        return new LongParticle(
                                operandA.getValue() * operandB.getValue());
                    }
                },
        /**
         * Use the minimum of both values.
         */
        MINIMUM {
                    @Override
                    LongParticle operate(final LongParticle operandA,
                            final LongParticle operandB) {
                        return new LongParticle(
                                Math.min(operandA.getValue(),
                                        operandB.getValue()));
                    }
                };

        /**
         * The abstract modificating method with two operands.
         *
         * @param operandA the first argument of the operation.
         * @param operandB the second argument of the operation.
         * @return the result of the operation.
         */
        abstract LongParticle operate(LongParticle operandA,
                LongParticle operandB);
    }

    /**
     * The value of this long instance.
     */
    private long value = 0L;

    public LongParticle() {

    }

    public LongParticle(final long valueInput) {
        this.value = valueInput;
    }

    public LongParticle(final DoubleParticle doubleParticle) {
        this.value = Double.valueOf(Math.floor(doubleParticle.getValue()))
                .longValue();
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

    public final int compareTo(final LongParticle lp) {
        return Long.valueOf(this.getValue()).compareTo(lp.getValue());
    }

    public final boolean lessThan(final LongParticle lp) {
        return this.compareTo(lp) < 0;
    }

    public final boolean greaterThan(final LongParticle lp) {
        return this.compareTo(lp) > 0;
    }

    public final boolean lessThanOrEqualTo(final LongParticle lp) {
        return this.compareTo(lp) <= 0;
    }

    public final boolean greaterThanOrEqualTo(final LongParticle lp) {
        return this.compareTo(lp) >= 0;
    }

    @Override
    public boolean equals(Object o) {
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
    public int hashCode() {
        return new HashCodeBuilder(17, 31)
                .append(value)
                .toHashCode();
    }

    public void applyOperation(final Operation operation,
            final LongParticle operand) {
        this.setValue(operation.operate(this, operand).getValue());
    }

}
