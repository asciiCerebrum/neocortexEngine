package org.asciicerebrum.mydndgame.domain.core.particles;

/**
 *
 * @author species8472
 */
public class ArmorClass extends LongParticle {

    /**
     * Modifies the armor class by adding the given bonus value. Returns itself.
     *
     * @param bonusValue the bonus value to add to the armor class.
     * @return itself.
     */
    public final ArmorClass add(final BonusValue bonusValue) {
        this.setValue(this.getValue() + bonusValue.getValue());
        return this;
    }

    /**
     * Modifies the armor class by adding the given dice constant value. Returns
     * itself.
     *
     * @param diceConstant the dice constant to add to the armor class.
     * @return itself.
     */
    public final ArmorClass add(final DiceConstant diceConstant) {
        this.setValue(this.getValue() + diceConstant.getValue());
        return this;
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
