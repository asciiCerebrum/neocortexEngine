package org.asciicerebrum.mydndgame.domain.ruleentities.composition;

import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.core.particles.DiceRoll;

/**
 *
 * @author species8472
 */
public class RollResult {

    /**
     * The dice roll of the roll result.
     */
    private DiceRoll rollResult;

    /**
     * The corresponding bonus value combined to the dice roll.
     */
    private BonusValue bonusValue;

    /**
     * Creates a roll result instance from the contained attributes.
     *
     * @param rollResultInput the dice roll.
     * @param bonusValueInput the bonus value.
     */
    public RollResult(final DiceRoll rollResultInput,
            final BonusValue bonusValueInput) {
        this.rollResult = rollResultInput;
        this.bonusValue = bonusValueInput;
    }

    /**
     * @return the bonusValue
     */
    public final BonusValue getBonusValue() {
        return bonusValue;
    }

    /**
     * @param bonusValueInput the bonusValue to set
     */
    public final void setBonusValue(final BonusValue bonusValueInput) {
        this.bonusValue = bonusValueInput;
    }

    /**
     * @return the totalResult
     */
    public final DiceRoll calcTotalResult() {
        return this.rollResult.add(this.bonusValue);
    }

    /**
     * @return the rollResult
     */
    public final DiceRoll getRollResult() {
        return rollResult;
    }

    /**
     * @param rollResultInput the rollResult to set
     */
    public final void setRollResult(final DiceRoll rollResultInput) {
        this.rollResult = rollResultInput;
    }

}
