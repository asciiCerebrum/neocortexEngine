package org.asciicerebrum.mydndgame.mechanics.conditionevaluators;

import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.mechanics.interfaces.ConditionEvaluator;
import org.asciicerebrum.mydndgame.domain.mechanics.interfaces.DynamicValueProvider;

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
                    final boolean compare(final Double leftOperand,
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
                    final boolean compare(final Double leftOperand,
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
                    final boolean compare(final Double leftOperand,
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
                    final boolean compare(final Double leftOperand,
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
                    final boolean compare(final Double leftOperand,
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
        abstract boolean compare(Double leftOperand, Double rightOperand);
    }

    /**
     * The ability bonus value provider to retrieve the ability bonus from the
     * character.
     */
    private DynamicValueProvider bonusValueProvider;

    /**
     * The comparator to make the correct comparison.
     */
    private ArithmeticComparator comparator;

    /**
     * The ability bonus is compared to this value in respect to the comparator.
     */
    private BonusValue referenceValue;

    /**
     * {@inheritDoc} Compares an ability of the dnd character in the situation
     * context with a given number. The comparator is also definable.
     */
    @Override
    public final boolean evaluate(final DndCharacter dndCharacter,
            final UniqueEntity uniqueEntity) {
        BonusValue abilityBonus
                = (BonusValue) this.bonusValueProvider
                .getDynamicValue(dndCharacter);

        return this.comparator.compare(
                (double) abilityBonus.getValue(),
                (double) this.referenceValue.getValue());
    }

    /**
     * @param bonusValueProviderInput the abilityBonusValueProvider to set
     */
    public final void setBonusValueProvider(
            final DynamicValueProvider bonusValueProviderInput) {
        this.bonusValueProvider = bonusValueProviderInput;
    }

    /**
     * @param comparatorInput the comparator to set
     */
    public final void setComparator(
            final ArithmeticComparator comparatorInput) {
        this.comparator = comparatorInput;
    }

    /**
     * @param referenceValueInput the referenceValue to set
     */
    public final void setReferenceValue(final BonusValue referenceValueInput) {
        this.referenceValue = referenceValueInput;
    }

}
