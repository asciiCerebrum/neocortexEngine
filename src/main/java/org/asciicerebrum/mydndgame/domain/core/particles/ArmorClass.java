package org.asciicerebrum.mydndgame.domain.core.particles;

/**
 *
 * @author species8472
 */
public class ArmorClass extends LongParticle {

    /**
     * Constructs the ac from a default value.
     *
     */
    public ArmorClass() {
    }

    /**
     * Constructs the ac from a long.
     *
     * @param acLong the ac as long.
     */
    public ArmorClass(final long acLong) {
        this.setValue(acLong);
    }

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
        if (!(o instanceof ArmorClass)) {
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
        return new ArmorClass();
    }

}
