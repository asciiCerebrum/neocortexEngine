package org.asciicerebrum.mydndgame.domain.core.particles;

/**
 *
 * @author species8472
 */
public class BonusRank extends LongParticle implements Comparable<BonusRank> {

    public static final BonusRank RANK_0 = new BonusRank(0);

    public static final BonusRank RANK_1 = new BonusRank(1);

    public static final BonusRank RANK_2 = new BonusRank(2);

    public static final BonusRank RANK_3 = new BonusRank(3);

    public BonusRank(final long rankValue) {
        this.setValue(rankValue);
    }

    @Override
    public final int compareTo(final BonusRank rank) {
        return Long.valueOf(this.getValue()).compareTo(rank.getValue());
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof BonusRank)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        return super.equals(obj);
    }

}
