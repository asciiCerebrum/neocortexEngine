package org.asciicerebrum.mydndgame.domain.ruleentities.composition;

import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.core.particles.DiceRoll;

/**
 *
 * @author species8472
 */
public class RollResult {

    private DiceRoll rollResult;

    private BonusValue bonusValue;

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
