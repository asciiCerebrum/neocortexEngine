package org.asciicerebrum.mydndgame.domain.core.particles;

/**
 *
 * @author species8472
 */
public class AdvancementNumber extends LongParticle {

    public final static AdvancementNumber ADV_NO_0 = new AdvancementNumber(0l);
    public final static AdvancementNumber ADV_NO_1 = new AdvancementNumber(1l);
    public final static AdvancementNumber ADV_NO_2 = new AdvancementNumber(2l);
    public final static AdvancementNumber ADV_NO_3 = new AdvancementNumber(3l);
    public final static AdvancementNumber ADV_NO_4 = new AdvancementNumber(4l);
    public final static AdvancementNumber ADV_NO_5 = new AdvancementNumber(5l);
    public final static AdvancementNumber ADV_NO_6 = new AdvancementNumber(6l);
    public final static AdvancementNumber ADV_NO_7 = new AdvancementNumber(7l);
    public final static AdvancementNumber ADV_NO_8 = new AdvancementNumber(8l);
    public final static AdvancementNumber ADV_NO_9 = new AdvancementNumber(9l);
    public final static AdvancementNumber ADV_NO_10
            = new AdvancementNumber(10l);
    public final static AdvancementNumber ADV_NO_11
            = new AdvancementNumber(11l);
    public final static AdvancementNumber ADV_NO_12
            = new AdvancementNumber(12l);
    public final static AdvancementNumber ADV_NO_13
            = new AdvancementNumber(13l);
    public final static AdvancementNumber ADV_NO_14
            = new AdvancementNumber(14l);
    public final static AdvancementNumber ADV_NO_15
            = new AdvancementNumber(15l);
    public final static AdvancementNumber ADV_NO_16
            = new AdvancementNumber(16l);
    public final static AdvancementNumber ADV_NO_17
            = new AdvancementNumber(17l);
    public final static AdvancementNumber ADV_NO_18
            = new AdvancementNumber(18l);
    public final static AdvancementNumber ADV_NO_19
            = new AdvancementNumber(19l);

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
