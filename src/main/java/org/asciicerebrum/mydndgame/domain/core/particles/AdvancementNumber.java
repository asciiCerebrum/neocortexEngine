package org.asciicerebrum.mydndgame.domain.core.particles;

/**
 *
 * @author species8472
 */
public class AdvancementNumber extends LongParticle {

    /**
     * Constant for adv number 0.
     */
    public static final AdvancementNumber ADV_NO_0 = new AdvancementNumber(0L);

    /**
     * Constant for adv number 1.
     */
    public static final AdvancementNumber ADV_NO_1 = new AdvancementNumber(1L);

    /**
     * Constant for adv number 2.
     */
    public static final AdvancementNumber ADV_NO_2 = new AdvancementNumber(2L);

    /**
     * Constant for adv number 3.
     */
    public static final AdvancementNumber ADV_NO_3 = new AdvancementNumber(3L);

    /**
     * Constant for adv number 4.
     */
    public static final AdvancementNumber ADV_NO_4 = new AdvancementNumber(4L);

    /**
     * Constant for adv number 5.
     */
    public static final AdvancementNumber ADV_NO_5 = new AdvancementNumber(5L);

    /**
     * Constant for adv number 6.
     */
    public static final AdvancementNumber ADV_NO_6 = new AdvancementNumber(6L);

    /**
     * Constant for adv number 7.
     */
    public static final AdvancementNumber ADV_NO_7 = new AdvancementNumber(7L);

    /**
     * Constant for adv number 8.
     */
    public static final AdvancementNumber ADV_NO_8 = new AdvancementNumber(8L);

    /**
     * Constant for adv number 9.
     */
    public static final AdvancementNumber ADV_NO_9 = new AdvancementNumber(9L);

    /**
     * Constant for adv number 10.
     */
    public static final AdvancementNumber ADV_NO_10
            = new AdvancementNumber(10L);

    /**
     * Constant for adv number 11.
     */
    public static final AdvancementNumber ADV_NO_11
            = new AdvancementNumber(11L);

    /**
     * Constant for adv number 12.
     */
    public static final AdvancementNumber ADV_NO_12
            = new AdvancementNumber(12L);

    /**
     * Constant for adv number 13.
     */
    public static final AdvancementNumber ADV_NO_13
            = new AdvancementNumber(13L);

    /**
     * Constant for adv number 14.
     */
    public static final AdvancementNumber ADV_NO_14
            = new AdvancementNumber(14L);

    /**
     * Constant for adv number 15.
     */
    public static final AdvancementNumber ADV_NO_15
            = new AdvancementNumber(15L);

    /**
     * Constant for adv number 16.
     */
    public static final AdvancementNumber ADV_NO_16
            = new AdvancementNumber(16L);

    /**
     * Constant for adv number 17.
     */
    public static final AdvancementNumber ADV_NO_17
            = new AdvancementNumber(17L);

    /**
     * Constant for adv number 18.
     */
    public static final AdvancementNumber ADV_NO_18
            = new AdvancementNumber(18L);

    /**
     * Constant for adv number 19.
     */
    public static final AdvancementNumber ADV_NO_19
            = new AdvancementNumber(19L);

    /**
     * Constructs the adv number from a string.
     *
     * @param advNumberString the adv number as string.
     */
    public AdvancementNumber(final String advNumberString) {
        this.setValue(advNumberString);
    }

    /**
     * Constructs the adv number from a long.
     *
     * @param advNumberLong the adv number as long.
     */
    public AdvancementNumber(final long advNumberLong) {
        this.setValue(advNumberLong);
    }

    /**
     *
     * @param advNumberString the adv number.
     */
    public final void setValue(final String advNumberString) {
        super.setValue(Long.parseLong(advNumberString));
    }

    @Override
    public final boolean equals(final Object o) {
        return this.equalsHelper(o);
    }

    @Override
    public final int hashCode() {
        return this.hashCodeHelper();
    }
}
