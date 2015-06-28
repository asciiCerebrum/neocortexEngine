package org.asciicerebrum.neocortexengine.domain.core.particles;

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
                    final void operate(final DoubleParticle operandA,
                            final DoubleParticle operandB,
                            final LongParticle result) {
                        result.setValue(
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
                    final void operate(final DoubleParticle operandA,
                            final DoubleParticle operandB,
                            final LongParticle result) {
                        result.setValue(
                                new DoubleParticle(operandA.getValue()
                                        / operandB.getValue()));
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
        abstract void operate(DoubleParticle operandA,
                DoubleParticle operandB, LongParticle result);
    }

    /**
     * The value of this long instance.
     */
    private double value = 0d;

    /**
     * Standard constructor with default value 0.
     */
    public DoubleParticle() {

    }

    /**
     * Creates a double particle instance from a double primitive.
     *
     * @param valueInput the double to create the instance from.
     */
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
