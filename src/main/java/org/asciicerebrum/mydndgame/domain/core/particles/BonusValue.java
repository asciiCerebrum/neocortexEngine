package org.asciicerebrum.mydndgame.domain.core.particles;

/**
 *
 * @author species8472
 */
public class BonusValue extends LongParticle {

    public BonusValue() {

    }

    public BonusValue(final long valueInput) {
        this.setValue(valueInput);
    }

    public BonusValue(final BonusValue bonusValueInput) {
        this.setValue(bonusValueInput.getValue());
    }

    public BonusValue subtract(final BonusValue subtrahend) {
        return new BonusValue(this.getValue() - subtrahend.getValue());
    }

    public final BonusValue add(final BonusValue summand) {
        long sum = this.getValue() + summand.getValue();
        return new BonusValue(sum);
    }

    public final boolean isNonZero() {
        return this.getValue() != 0L;
    }

    public void applyOperation(final DoubleParticle.Operation operation,
            final DoubleParticle operand) {
        this.setValue(operation.operate(
                new DoubleParticle(this.getValue()), operand).getValue());
    }

}
