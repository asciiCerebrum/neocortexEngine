package org.asciicerebrum.mydndgame.observers;

import org.asciicerebrum.mydndgame.interfaces.entities.ISituationContext;

/**
 *
 * @author species8472
 */
public class SimpleArithmeticObserver extends AbstractObserver {

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
                    Long operate(final Long operandA, final Long operandB) {
                        return operandA + operandB;
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
                    Long operate(final Long operandA, final Long operandB) {
                        return operandA * operandB;
                    }
                };

        /**
         * The abstract modificating method with two operands.
         *
         * @param operandA the first argument of the operation.
         * @param operandB the second argument of the operation.
         * @return the result of the operation.
         */
        abstract Long operate(Long operandA, Long operandB);
    }

    /**
     * Numeric modificatioin of the base value called numeric.
     */
    private Long modValue;

    /**
     * How to modificate the base value.
     */
    private Operation operation;

    /**
     * {@inheritDoc}
     */
    @Override
    public final Object trigger(final Object object,
            final ISituationContext situationContext) {

        Long numeric = (Long) object;

        return getOperation().operate(numeric, this.getModValue());
    }

    /**
     * @return the modValue
     */
    public final Long getModValue() {
        return modValue;
    }

    /**
     * @param modValueInput the modValue to set
     */
    public final void setModValue(final Long modValueInput) {
        this.modValue = modValueInput;
    }

    /**
     * @return the operation
     */
    public final Operation getOperation() {
        return operation;
    }

    /**
     * @param operationInput the operation to set
     */
    public final void setOperation(final Operation operationInput) {
        this.operation = operationInput;
    }

}
