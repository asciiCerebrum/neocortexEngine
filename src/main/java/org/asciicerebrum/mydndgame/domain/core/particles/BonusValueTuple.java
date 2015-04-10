package org.asciicerebrum.mydndgame.domain.core.particles;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.asciicerebrum.mydndgame.domain.core.particles.DoubleParticle.Operation;

/**
 * The rank for the bonus. rank for multiple boni in a row, e.g. base attack
 * boni +2/+1 and the like; rank start from 0
 *
 * @author species8472
 */
public class BonusValueTuple {

    /**
     * This class is used for easy creation of a bonus value tuple instance.
     * Thee bonus value is coupled with the bonus rank into one object that can
     * be used to instantiate a bonus value tuple.
     */
    public static class BonusValueEntry {

        /**
         * The rank of the bonus.
         */
        private final BonusRank bonusRank;

        /**
         * The numerical value of the bonus.
         */
        private final BonusValue bonusValue;

        /**
         * Constructor of the entry without a rank. The rank is set to 0.
         *
         * @param valueInput the value of the bonus.
         */
        public BonusValueEntry(final BonusValue valueInput) {
            this.bonusRank = BonusRank.RANK_0;
            this.bonusValue = valueInput;
        }

        /**
         * Constructor of the entry with rank and value.
         *
         * @param rankInput the rank of the bonus.
         * @param valueInput the value of the bonus.
         */
        public BonusValueEntry(final BonusRank rankInput,
                final BonusValue valueInput) {
            this.bonusRank = rankInput;
            this.bonusValue = valueInput;
        }

        /**
         * @return the rank of the bonus.
         */
        public final BonusRank getBonusRank() {
            return this.bonusRank;
        }

        /**
         * @return the value of the bonus.
         */
        public final BonusValue getBonusValue() {
            return this.bonusValue;
        }
    }

    /**
     * Iterator inner class for the bonus value entries of this tuple.
     */
    public static class BonusValueEntryIterator
            implements Iterator<BonusValueEntry> {

        /**
         * The map this iterator is based on.
         */
        private final Map<BonusRank, BonusValue> rankedBoni;

        /**
         * The subiterator for delegation.
         */
        private final Iterator<BonusRank> subIterator;

        /**
         * Constructing the iterator from a map.
         *
         * @param rankedBoniInput the map this iterator is based on.
         */
        public BonusValueEntryIterator(
                final Map<BonusRank, BonusValue> rankedBoniInput) {
            this.rankedBoni = rankedBoniInput;

            this.subIterator = rankedBoni.keySet().iterator();
        }

        @Override
        public final boolean hasNext() {
            return this.subIterator.hasNext();
        }

        @Override
        public final BonusValueEntry next() {
            final BonusRank rank = this.subIterator.next();
            return new BonusValueEntry(rank, rankedBoni.get(rank));
        }

        @Override
        public final void remove() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }

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
     * Empty constructor. The instance starts with an empty map of ranks and
     * values.
     */
    public BonusValueTuple() {

    }

    /**
     * Constructing the map from single bonus value. The rank is set to 0.
     *
     * @param value the value of the rank-0 bonus.
     */
    public BonusValueTuple(final BonusValue value) {
        this.addBonusValue(value);
    }

    /**
     * Constructing the map from a list of rank-value entries.
     *
     * @param entries the entries containing ranks and values.
     */
    public BonusValueTuple(final BonusValueEntry... entries) {
        for (BonusValueEntry entry : entries) {
            this.addBonusValueEntry(entry);
        }
    }

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
     * Put a new value-rank-combination into the map.
     *
     * @param rank the rank of the bonus.
     * @param bonusValue the bonus value of that rank.
     */
    public final void addBonusValue(final BonusRank rank,
            final BonusValue bonusValue) {
        this.rankedBoni.put(rank, bonusValue);
    }

    /**
     * Put a new value into the map. The rank is set to 0.
     *
     * @param bonusValue the value to put into the map.
     */
    public final void addBonusValue(final BonusValue bonusValue) {
        this.rankedBoni.put(BonusRank.RANK_0, bonusValue);
    }

    /**
     * Put a new value-rank-entry into the map.
     *
     * @param entry the entry to add to the map.
     */
    public final void addBonusValueEntry(final BonusValueEntry entry) {
        this.rankedBoni.put(entry.getBonusRank(), entry.getBonusValue());
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

        if (ListUtils.isEqualList(this.rankedBoni.entrySet(),
                oTuple.rankedBoni.entrySet())) {
            return ListUtils.isEqualList(this.rankedBoni.values(),
                    oTuple.rankedBoni.values());
        }
        return false;
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

    /**
     * Iterator over the bonus value entries of this tuple instance.
     *
     * @return the iterator.
     */
    public final Iterator<BonusValueEntry> iterator() {
        return new BonusValueEntryIterator(this.rankedBoni);
    }

    /**
     * Tests if this instance's value is less than that of a given bonus value
     * tuple. The rank-0 value is considered for this comparison.
     *
     * @param bonusValueTuple the given bonusValueTuple.
     * @return true if less than, otherwise false.
     */
    public final boolean lessThan(final BonusValueTuple bonusValueTuple) {
        return this.getBonusValueByRank(BonusRank.RANK_0)
                .lessThan(bonusValueTuple
                        .getBonusValueByRank(BonusRank.RANK_0));
    }
}
