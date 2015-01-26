package org.asciicerebrum.mydndgame.domain.core.particles;

/**
 *
 * @author species8472
 */
public class BonusRank extends LongParticle implements Comparable<BonusRank> {

    public final static BonusRank RANK_0 = new BonusRank(0);
    
    public final static BonusRank RANK_1 = new BonusRank(1);
    
    public final static BonusRank RANK_2 = new BonusRank(2);
    
    public final static BonusRank RANK_3 = new BonusRank(3);
    
    public BonusRank(final long rankValue) {
        this.setValue(rankValue);
    }
    
    @Override
    public int compareTo(final BonusRank rank) {
        return Long.valueOf(this.getValue()).compareTo(rank.getValue());
    }

}
