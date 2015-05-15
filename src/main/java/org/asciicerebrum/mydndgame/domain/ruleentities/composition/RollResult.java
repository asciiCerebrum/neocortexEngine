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
    private DiceRoll diceRoll;

    /**
     * The corresponding bonus value combined to the dice roll.
     */
    private BonusValue bonusValue;

    /**
     * Creates a roll result instance from the contained attributes.
     *
     * @param diceRollInput the dice roll.
     * @param bonusValueInput the bonus value.
     */
    public RollResult(final DiceRoll diceRollInput,
            final BonusValue bonusValueInput) {
        this.diceRoll = diceRollInput;
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
        return this.diceRoll.add(this.bonusValue);
    }

    /**
     * @return the rollResult
     */
    public final DiceRoll getDiceRoll() {
        return this.diceRoll;
    }

    /**
     * @param diceRollInput the rollResult to set
     */
    public final void setDiceRoll(final DiceRoll diceRollInput) {
        this.diceRoll = diceRollInput;
    }

}
