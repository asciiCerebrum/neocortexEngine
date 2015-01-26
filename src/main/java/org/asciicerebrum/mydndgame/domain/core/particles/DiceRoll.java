package org.asciicerebrum.mydndgame.domain.core.particles;

/**
 *
 * @author species8472
 */
public class DiceRoll extends LongParticle {

    public DiceRoll(final long value) {
        this.setValue(value);
    }

    public final DiceRoll add(final BonusValue bonusValue) {
        return new DiceRoll(this.getValue() + bonusValue.getValue());
    }

    public static final DiceRoll max(final DiceRoll dr1, final DiceRoll dr2) {
        if (dr1.getValue() >= dr2.getValue()) {
            return dr1;
        }
        return dr2;
    }

}
