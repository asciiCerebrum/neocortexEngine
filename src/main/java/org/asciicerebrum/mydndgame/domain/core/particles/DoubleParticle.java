package org.asciicerebrum.mydndgame.domain.core.particles;

/**
 *
 * @author species8472
 */
public class DoubleParticle {

    /**
     * Arithmetic modes of modification. As always, the values are rounded down.
     */
    public static enum Operation {

        /**
         * Multiply with given value.
         */
        MULTIPLICATION {

                    /**
                     * {@inheritDoc}
                     */
                    @Override
                    final LongParticle operate(final DoubleParticle operandA,
                            final DoubleParticle operandB) {
                        return new LongParticle(
                                new DoubleParticle(operandA.getValue()
                                        * operandB.getValue()));
                    }
                },
        /**
         * Divide by given value.
         */
        DIVISION {
                    /**
                     * {@inheritDoc}
                     */
                    @Override
                    final LongParticle operate(final DoubleParticle operandA,
                            final DoubleParticle operandB) {
                        return new LongParticle(
                                new DoubleParticle(operandA.getValue()
                                        / operandB.getValue()));
                    }
                };

        /**
         * The abstract modificating method with two operands.
         *
         * @param operandA the first argument of the operation.
         * @param operandB the second argument of the operation.
         * @return the result of the operation.
         */
        abstract LongParticle operate(DoubleParticle operandA,
                DoubleParticle operandB);
    }

    /**
     * The value of this long instance.
     */
    private double value = 0d;

    public DoubleParticle() {

    }

    public DoubleParticle(final double valueInput) {
        this.value = valueInput;
    }

    /**
     * @return the value
     */
    public final double getValue() {
        return value;
    }

    /**
     * @param valueInput the value to set
     */
    public final void setValue(final double valueInput) {
        this.value = valueInput;
    }
}
