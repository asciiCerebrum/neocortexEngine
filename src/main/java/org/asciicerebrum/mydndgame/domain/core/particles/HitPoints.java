package org.asciicerebrum.mydndgame.domain.core.particles;

/**
 *
 * @author species8472
 */
public class HitPoints extends LongParticle {

    public HitPoints() {
        this.setValue(0L);
    }

    public HitPoints(final String hitPointsString) {
        this.setValue(hitPointsString);
    }

    public final void setValue(final String hitPointsString) {
        super.setValue(Long.parseLong(hitPointsString));
    }

    public final void add(final HitPoints hitPoints) {
        this.setValue(this.getValue() + hitPoints.getValue());
    }

    public final void add(final BonusValue bonusValue) {
        this.setValue(this.getValue() + bonusValue.getValue());
    }

    public final void add(final DiceSides diceSides) {
        this.setValue(this.getValue() + diceSides.getValue());
    }

    public final void multiply(final BonusValue bonusValue) {
        this.setValue(this.getValue() * bonusValue.getValue());
    }

    public final void subtract(final HitPoints hitPoints) {
        this.setValue(this.getValue() - hitPoints.getValue());
    }

    public final void subtract(final DiceRoll diceRoll) {
        this.setValue(this.getValue() - diceRoll.getValue());
    }

}
