package org.asciicerebrum.mydndgame.domain.core.particles;

/**
 *
 * @author species8472
 */
public class AdvancementNumber extends LongParticle {

    public final static AdvancementNumber ADV_NO_0 = new AdvancementNumber(0L);
    public final static AdvancementNumber ADV_NO_1 = new AdvancementNumber(1L);
    public final static AdvancementNumber ADV_NO_2 = new AdvancementNumber(2L);
    public final static AdvancementNumber ADV_NO_3 = new AdvancementNumber(3L);
    public final static AdvancementNumber ADV_NO_4 = new AdvancementNumber(4L);
    public final static AdvancementNumber ADV_NO_5 = new AdvancementNumber(5L);
    public final static AdvancementNumber ADV_NO_6 = new AdvancementNumber(6L);
    public final static AdvancementNumber ADV_NO_7 = new AdvancementNumber(7L);
    public final static AdvancementNumber ADV_NO_8 = new AdvancementNumber(8L);
    public final static AdvancementNumber ADV_NO_9 = new AdvancementNumber(9L);
    public final static AdvancementNumber ADV_NO_10
            = new AdvancementNumber(10L);
    public final static AdvancementNumber ADV_NO_11
            = new AdvancementNumber(11L);
    public final static AdvancementNumber ADV_NO_12
            = new AdvancementNumber(12L);
    public final static AdvancementNumber ADV_NO_13
            = new AdvancementNumber(13L);
    public final static AdvancementNumber ADV_NO_14
            = new AdvancementNumber(14L);
    public final static AdvancementNumber ADV_NO_15
            = new AdvancementNumber(15L);
    public final static AdvancementNumber ADV_NO_16
            = new AdvancementNumber(16L);
    public final static AdvancementNumber ADV_NO_17
            = new AdvancementNumber(17L);
    public final static AdvancementNumber ADV_NO_18
            = new AdvancementNumber(18L);
    public final static AdvancementNumber ADV_NO_19
            = new AdvancementNumber(19L);

    public AdvancementNumber(final String advNumberString) {
        this.setValue(advNumberString);
    }

    public AdvancementNumber(final long advNumberLong) {
        this.setValue(advNumberLong);
    }

    public final void setValue(final String advNumberString) {
        super.setValue(Long.parseLong(advNumberString));
    }
}
