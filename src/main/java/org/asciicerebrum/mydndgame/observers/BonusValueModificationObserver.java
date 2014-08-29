package org.asciicerebrum.mydndgame.observers;

import java.util.List;
import org.asciicerebrum.mydndgame.interfaces.entities.IBonus;
import org.asciicerebrum.mydndgame.interfaces.entities.ISituationContext;

/**
 *
 * @author species8472
 */
public class BonusValueModificationObserver extends AbstractObserver {

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
                    final Long operate(final Double operandA,
                            final Double operandB) {
                        return Double.valueOf(Math.floor(operandA * operandB))
                        .longValue();
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
                    final Long operate(final Double operandA,
                            final Double operandB) {
                        return Double.valueOf(Math.floor(operandA / operandB))
                        .longValue();
                    }
                };

        /**
         * The abstract modificating method with two operands.
         *
         * @param operandA the first argument of the operation.
         * @param operandB the second argument of the operation.
         * @return the result of the operation.
         */
        abstract Long operate(Double operandA, Double operandB);
    }

    /**
     * Defines how to arithmetically modify the value.
     */
    private Operation operation;

    /**
     * The value of modification.
     */
    private Double modValue;

    /**
     * The bonus resembling the one to modify.
     */
    private IBonus referenceBonus;

    /**
     * {@inheritDoc} If a bonus resembling the reference bonus is encountered in
     * the list, its value is modified. If the value was based on a dynamic
     * value provider, it is replaced by a static value.
     */
    @Override
    protected final Object triggerCallback(final Object object,
            final ISituationContext situationContext) {

        List<IBonus> boni = (List<IBonus>) object;

        if (this.getReferenceBonus() == null) {
            return boni;
        }

        for (IBonus bonus : boni) {
            // it is enough to check for bonus type and target
            if (!this.getReferenceBonus().getBonusType()
                    .equals(bonus.getBonusType())
                    || !this.getReferenceBonus().getTarget()
                    .equals(bonus.getTarget())) {
                continue;
            }

            Long modifiedBonus = this.getOperation()
                    .operate(bonus.getEffectiveValue(situationContext)
                            .doubleValue(), this.getModValue());

            bonus.setValue(modifiedBonus);
            bonus.setDynamicValueProvider(null);
        }

        return boni;
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
     * @return the modValue
     */
    public final Double getModValue() {
        return modValue;
    }

    /**
     * @param modValueInput the modValue to set
     */
    public final void setModValue(final Double modValueInput) {
        this.modValue = modValueInput;
    }

    /**
     * @return the referenceBonus
     */
    public final IBonus getReferenceBonus() {
        return referenceBonus;
    }

    /**
     * @param referenceBonusInput the referenceBonus to set
     */
    public final void setReferenceBonus(final IBonus referenceBonusInput) {
        this.referenceBonus = referenceBonusInput;
    }

}
