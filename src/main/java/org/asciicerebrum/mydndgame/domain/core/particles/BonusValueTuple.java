package org.asciicerebrum.mydndgame.domain.core.particles;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import org.apache.commons.collections.ListUtils;
import org.asciicerebrum.mydndgame.domain.core.particles.DoubleParticle.Operation;

/**
 * The rank for the bonus. rank for multiple boni in a row, e.g. base attack
 * boni +2/+1 and the like; rank start from 0
 *
 * @author species8472
 */
public class BonusValueTuple {

    /**
     * The bonus values are automatically ordered by their rank.
     */
    private final Map<BonusRank, BonusValue> rankedBoni
            = new TreeMap<BonusRank, BonusValue>();

    public final BonusValue getBonusValueByRank(final BonusRank rank) {
        return this.rankedBoni.get(rank);
    }

    public void addBonusValue(final BonusRank rank,
            final BonusValue bonusValue) {
        this.rankedBoni.put(rank, bonusValue);
    }

    @Override
    public boolean equals(Object o) {
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
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.rankedBoni != null
                ? this.rankedBoni.hashCode() : 0);
        return hash;
    }

    public final BonusValueTuple subtract(final BonusValueTuple subtrahend) {
        BonusValueTuple subtraction = new BonusValueTuple();

        for (Entry<BonusRank, BonusValue> tupleEntry
                : this.rankedBoni.entrySet()) {

            BonusValue subtrahendValue
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
     * @param summand
     */
    public void add(final BonusValueTuple summand) {
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
     * @param summand
     */
    public void add(final BonusValue summand) {
        for (Entry<BonusRank, BonusValue> tupleEntry
                : this.rankedBoni.entrySet()) {

            this.rankedBoni.put(tupleEntry.getKey(),
                    tupleEntry.getValue().add(summand));
        }
    }

    public void applyOperation(final Operation operation,
            final DoubleParticle operand) {
        for (Entry<BonusRank, BonusValue> tupleEntry
                : this.rankedBoni.entrySet()) {
            tupleEntry.getValue().applyOperation(operation, operand);
        }
    }
}
