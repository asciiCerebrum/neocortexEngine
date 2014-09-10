package org.asciicerebrum.mydndgame.observers;

import org.asciicerebrum.mydndgame.interfaces.entities.BonusValueProvider;
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
                },
        /**
         * Use the minimum of both values.
         */
        MINIMUM {
                    @Override
                    Long operate(final Long operandA, final Long operandB) {
                        return Math.min(operandA, operandB);
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
     * Not a static but a dynamic mod value. The dynamic value has priority over
     * the static one.
     */
    private BonusValueProvider modValueProvider;

    /**
     * How to modificate the base value.
     */
    private Operation operation;

    /**
     * {@inheritDoc}
     */
    @Override
    protected final Object triggerCallback(final Object object,
            final ISituationContext situationContext) {

        Long numeric = (Long) object;

        Long effectiveModValue = this.getModValue();
        if (this.getModValueProvider() != null) {
            effectiveModValue = this.getModValueProvider()
                    .getDynamicValue(situationContext);
        }

        if (effectiveModValue == null) {
            return numeric;
        }

        return getOperation().operate(numeric, effectiveModValue);
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

    /**
     * @return the modValueProvider
     */
    public final BonusValueProvider getModValueProvider() {
        return modValueProvider;
    }

    /**
     * @param modValueProviderInput the modValueProvider to set
     */
    public final void setModValueProvider(
            final BonusValueProvider modValueProviderInput) {
        this.modValueProvider = modValueProviderInput;
    }

}
