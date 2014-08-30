package org.asciicerebrum.mydndgame.conditionevaluator;

import org.asciicerebrum.mydndgame.conditionevaluator.AbilityBonusComparisonEvaluator.ArithmeticComparator;
import org.asciicerebrum.mydndgame.interfaces.entities.ConditionEvaluator;
import org.asciicerebrum.mydndgame.interfaces.entities.ISituationContext;
import org.asciicerebrum.mydndgame.valueproviders.AbilityBonusValueProvider;

/**
 *
 * @author species8472
 */
public class AbilityBonusComparisonEvaluator implements ConditionEvaluator {

    /**
     * Definition of the simple arithmetic comparators.
     */
    public static enum ArithmeticComparator {

        /**
         * less than.
         */
        LT {

                    /**
                     * {@inheritDoc}
                     */
                    @Override
                    Boolean compare(Double leftOperand, Double rightOperand) {
                        return leftOperand.compareTo(rightOperand) < 0;
                    }

                },
        /**
         * less or equal.
         */
        LE {
                    /**
                     * {@inheritDoc}
                     */
                    @Override
                    Boolean compare(Double leftOperand, Double rightOperand) {
                        return leftOperand.compareTo(rightOperand) <= 0;
                    }
                },
        /**
         * equal.
         */
        EQ {
                    /**
                     * {@inheritDoc}
                     */
                    @Override
                    Boolean compare(Double leftOperand, Double rightOperand) {
                        return leftOperand.compareTo(rightOperand) == 0;
                    }
                },
        /**
         * greater or equal.
         */
        GE {
                    /**
                     * {@inheritDoc}
                     */
                    @Override
                    Boolean compare(Double leftOperand, Double rightOperand) {
                        return leftOperand.compareTo(rightOperand) >= 0;
                    }
                },
        /**
         * greater than.
         */
        GT {
                    /**
                     * {@inheritDoc}
                     */
                    @Override
                    Boolean compare(Double leftOperand, Double rightOperand) {
                        return leftOperand.compareTo(rightOperand) > 0;
                    }
                };

        /**
         * Simple comparison of double values.
         *
         * @param leftOperand the left operand of the comparator.
         * @param rightOperand the right operand of the comparator.
         * @return result of the comparison.
         */
        abstract Boolean compare(Double leftOperand, Double rightOperand);
    }

    /**
     * The ability bonus value provider to retrieve the ability bonus from the
     * character.
     */
    private AbilityBonusValueProvider abilityBonusValueProvider;

    /**
     * The comparator to make the correct comparison.
     */
    private ArithmeticComparator comparator;

    /**
     * The ability bonus is compared to this value in respect to the comparator.
     */
    private Double referenceValue;

    /**
     * {@inheritDoc} Compares an ability of the dnd character in the situation
     * context with a given number. The comparator is also definable.
     */
    @Override
    public final Boolean evaluate(final ISituationContext situationContext) {
        Long abilityBonus
                = this.getAbilityBonusValueProvider()
                .getDynamicValue(situationContext);

        return this.getComparator().compare(
                abilityBonus.doubleValue(),
                this.getReferenceValue());
    }

    /**
     * @return the abilityBonusValueProvider
     */
    public final AbilityBonusValueProvider getAbilityBonusValueProvider() {
        return abilityBonusValueProvider;
    }

    /**
     * @param abilityBonusValueProviderInput the abilityBonusValueProvider to
     * set
     */
    public final void setAbilityBonusValueProvider(
            final AbilityBonusValueProvider abilityBonusValueProviderInput) {
        this.abilityBonusValueProvider = abilityBonusValueProviderInput;
    }

    /**
     * @return the comparator
     */
    public final ArithmeticComparator getComparator() {
        return comparator;
    }

    /**
     * @param comparatorInput the comparator to set
     */
    public final void setComparator(
            final ArithmeticComparator comparatorInput) {
        this.comparator = comparatorInput;
    }

    /**
     * @return the referenceValue
     */
    public final Double getReferenceValue() {
        return referenceValue;
    }

    /**
     * @param referenceValueInput the referenceValue to set
     */
    public final void setReferenceValue(final Double referenceValueInput) {
        this.referenceValue = referenceValueInput;
    }

}
