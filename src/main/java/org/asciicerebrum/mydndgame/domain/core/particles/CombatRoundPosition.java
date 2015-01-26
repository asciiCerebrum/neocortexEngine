package org.asciicerebrum.mydndgame.domain.core.particles;

import org.apache.commons.lang.builder.CompareToBuilder;

/**
 *
 * @author species8472
 */
public class CombatRoundPosition extends StringParticle implements Comparable {

    /**
     * Format string for the elements of the round position.
     */
    private static final String ROUND_POSITION_FORMAT = "%03d";

    public CombatRoundPosition(final String combatRoundPosition) {
        this.setValue(combatRoundPosition);
    }

    public CombatRoundPosition(final DiceRoll initRoll,
            final BonusValue initBonus) {
        this.setValue(String.format(ROUND_POSITION_FORMAT,
                initRoll.getValue())
                + String.format(ROUND_POSITION_FORMAT,
                        initBonus.getValue()));
    }

    public CombatRoundPosition(final CombatRoundPosition roundPosition,
            final DiceRoll initReRoll) {
        this.setValue(this.getValue()
                + String.format(ROUND_POSITION_FORMAT, initReRoll.getValue()));
    }

    public int compareTo(CombatRoundPosition crp) {
        return this.getValue().compareTo(crp.getValue());
    }

    /**
     * Natural order of combat round position is given by the alphanumeric order
     * of the corresponding string values.
     *
     * @param o the object to compare with.
     * @return the comparison result.
     */
    @Override
    public int compareTo(Object o) {
        if (!(o instanceof CombatRoundPosition)) {
            return -1;
        }
        CombatRoundPosition oCrPos = (CombatRoundPosition) o;
        return new CompareToBuilder()
                .append(this.getValue(), oCrPos.getValue()).toComparison();
    }

}
