package org.asciicerebrum.mydndgame.domain.core.particles;

/**
 *
 * @author species8472
 */
public class ArmorClass extends LongParticle {

    public ArmorClass add(final BonusValue bonusValue) {
        this.setValue(this.getValue() + bonusValue.getValue());
        return this;
    }

    public ArmorClass add(final DiceConstant diceConstant) {
        this.setValue(this.getValue() + diceConstant.getValue());
        return this;
    }

}
