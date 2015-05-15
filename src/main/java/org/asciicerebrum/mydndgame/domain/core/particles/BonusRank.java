package org.asciicerebrum.mydndgame.domain.core.particles;

/**
 *
 * @author species8472
 */
public class BonusRank extends LongParticle implements Comparable<BonusRank> {

    /**
     * Constant for the bonus rank of 0.
     */
    public static final BonusRank RANK_0 = new BonusRank(0);
    /**
     * Constant for the bonus rank of 1.
     */
    public static final BonusRank RANK_1 = new BonusRank(1);
    /**
     * Constant for the bonus rank of 2.
     */
    public static final BonusRank RANK_2 = new BonusRank(2);
    /**
     * Constant for the bonus rank of 3.
     */
    public static final BonusRank RANK_3 = new BonusRank(3);

    /**
     * Constructor for creating a bonus rank out of a long.
     *
     * @param rankValue the long value to create to bonus rank from.
     */
    public BonusRank(final long rankValue) {
        this.setValue(rankValue);
    }

    /**
     * Constructor for creating a bonus rank out of a default long value.
     *
     */
    public BonusRank() {
    }

    @Override
    public final int compareTo(final BonusRank rank) {
        return Long.valueOf(this.getValue()).compareTo(rank.getValue());
    }

    @Override
    public final boolean equals(final Object o) {
        if (!(o instanceof BonusRank)) {
            return false;
        }
        return this.equalsHelper(o);
    }

    @Override
    public final int hashCode() {
        return this.hashCodeHelper();
    }

    @Override
    public final LongParticle getNewInstanceOfSameType() {
        return new BonusRank();
    }

}
