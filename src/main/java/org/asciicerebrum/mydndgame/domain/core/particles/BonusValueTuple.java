package org.asciicerebrum.mydndgame.domain.core.particles;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.asciicerebrum.mydndgame.domain.core.particles.DoubleParticle.Operation;

/**
 * The rank for the bonus. rank for multiple boni in a row, e.g. base attack
 * boni +2/+1 and the like; rank start from 0
 *
 * @author species8472
 */
public class BonusValueTuple {

    /**
     * Initial value for hash code calculation.
     */
    private static final int INITIAL_NON_ZERO_ODD_NUMBER = 7;

    /**
     * Modifier for hash code calculation.
     */
    private static final int MULTIPLIER_NON_ZERO_ODD_NUMBER = 67;

    /**
     * The bonus values are automatically ordered by their rank.
     */
    private final Map<BonusRank, BonusValue> rankedBoni
            = new TreeMap<BonusRank, BonusValue>();

    /**
     * Retrieve the bonus value of a given rank.
     *
     * @param rank the rank the bonus should have.
     * @return the bonus value of that rank.
     */
    public final BonusValue getBonusValueByRank(final BonusRank rank) {
        return this.rankedBoni.get(rank);
    }

    /**
     * Put a new bonus-rank-combination into the map.
     *
     * @param rank the rank of the bonus.
     * @param bonusValue the bonus value of that rank.
     */
    public final void addBonusValue(final BonusRank rank,
            final BonusValue bonusValue) {
        this.rankedBoni.put(rank, bonusValue);
    }

    @Override
    public final boolean equals(final Object o) {
        if (!(o instanceof BonusValueTuple)) {
            return false;
        }
        if (o == this) {
            return true;
        }
        BonusValueTuple oTuple = (BonusValueTuple) o;

        return ListUtils.isEqualList(this.rankedBoni.entrySet(),
                oTuple.rankedBoni.entrySet())
                && ListUtils.isEqualList(this.rankedBoni.values(),
                        oTuple.rankedBoni.values());
    }

    @Override
    public final int hashCode() {
        return new HashCodeBuilder(INITIAL_NON_ZERO_ODD_NUMBER,
                MULTIPLIER_NON_ZERO_ODD_NUMBER)
                .append(this.rankedBoni)
                .toHashCode();
    }

    /**
     * Rank-wise subtraction of bonus-values. Adding new bonus rank if rank not
     * yet present.
     *
     * @param subtrahend the tuple used for subtraction.
     * @return the result of the rank-wise subtraction.
     */
    public final BonusValueTuple subtract(final BonusValueTuple subtrahend) {
        BonusValueTuple subtraction = new BonusValueTuple();

        for (Entry<BonusRank, BonusValue> tupleEntry
                : this.rankedBoni.entrySet()) {

            final BonusValue subtrahendValue
                    = subtrahend.getBonusValueByRank(tupleEntry.getKey());

            if (subtrahendValue == null) {
                subtraction.addBonusValue(tupleEntry.getKey(),
                        tupleEntry.getValue());
                continue;
            }

            subtraction.addBonusValue(tupleEntry.getKey(),
                    tupleEntry.getValue().subtract(subtrahendValue));
        }

        return subtraction;
    }

    /**
     * Rank-wise summation of bonus-value. Adding new bonus rank if rank not yet
     * present.
     *
     * @param summand the tuple used for adding.
     */
    public final void add(final BonusValueTuple summand) {
        if (summand == null) {
            return;
        }
        for (Entry<BonusRank, BonusValue> tupleEntry
                : summand.rankedBoni.entrySet()) {

            BonusValue bonusValue
                    = this.rankedBoni.get(tupleEntry.getKey());
            BonusValue newBonusValue;
            if (bonusValue == null) {
                newBonusValue = new BonusValue(tupleEntry.getValue());
            } else {
                newBonusValue = bonusValue.add(tupleEntry.getValue());
            }

            this.rankedBoni.put(tupleEntry.getKey(), newBonusValue);
        }
    }

    /**
     * Adds the bonus value to all entries of the tuple.
     *
     * @param summand the bonus value used for adding.
     */
    public final void add(final BonusValue summand) {
        for (Entry<BonusRank, BonusValue> tupleEntry
                : this.rankedBoni.entrySet()) {

            this.rankedBoni.put(tupleEntry.getKey(),
                    tupleEntry.getValue().add(summand));
        }
    }

    /**
     * Applies an operation on this instance with a given double particle of any
     * kind. It affects all bonus values in the make the same way, e.g. making a
     * quotient with rounding, etc. The operation is delegated to the bonus
     * value.
     *
     * @param operation the operation to perform. It is a mathematical one.
     * @param operand the other operand for the operation, e.g. a divisor, etc.
     */
    public final void applyOperation(final Operation operation,
            final DoubleParticle operand) {
        for (Entry<BonusRank, BonusValue> tupleEntry
                : this.rankedBoni.entrySet()) {
            tupleEntry.getValue().applyOperation(operation, operand);
        }
    }
}
