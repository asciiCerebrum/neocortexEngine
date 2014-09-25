package org.asciicerebrum.mydndgame.conditionevaluator;

import org.asciicerebrum.mydndgame.conditionevaluator.BonusValueComparisonEvaluator.ArithmeticComparator;
import org.asciicerebrum.mydndgame.interfaces.entities.BonusValueProvider;
import org.asciicerebrum.mydndgame.interfaces.entities.ConditionEvaluator;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;

/**
 *
 * @author species8472
 */
public class BonusValueComparisonEvaluator implements ConditionEvaluator {

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
                    final Boolean compare(final Double leftOperand,
                            final Double rightOperand) {
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
                    final Boolean compare(final Double leftOperand,
                            final Double rightOperand) {
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
                    final Boolean compare(final Double leftOperand,
                            final Double rightOperand) {
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
                    final Boolean compare(final Double leftOperand,
                            final Double rightOperand) {
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
                    final Boolean compare(final Double leftOperand,
                            final Double rightOperand) {
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
    private BonusValueProvider bonusValueProvider;

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
    public final Boolean evaluate(final ICharacter character) {
        Long abilityBonus
                = this.getBonusValueProvider()
                .getDynamicValue(character);

        return this.getComparator().compare(
                abilityBonus.doubleValue(),
                this.getReferenceValue());
    }

    /**
     * @return the abilityBonusValueProvider
     */
    public final BonusValueProvider getBonusValueProvider() {
        return bonusValueProvider;
    }

    /**
     * @param bonusValueProviderInput the abilityBonusValueProvider to set
     */
    public final void setBonusValueProvider(
            final BonusValueProvider bonusValueProviderInput) {
        this.bonusValueProvider = bonusValueProviderInput;
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
