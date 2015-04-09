package org.asciicerebrum.mydndgame.domain.core.particles;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 *
 * @author species8472
 */
public class CombatRoundPosition extends StringParticle implements Comparable {

    /**
     * Initial value for hash code calculation.
     */
    private static final int INITIAL_NON_ZERO_ODD_NUMBER = 17;

    /**
     * Modifier for hash code calculation.
     */
    private static final int MULTIPLIER_NON_ZERO_ODD_NUMBER = 31;

    /**
     * Format string for the elements of the round position.
     */
    private static final String ROUND_POSITION_FORMAT = "%03d";

    /**
     * Creates the position from a string.
     *
     * @param combatRoundPosition the string to create the position from.
     */
    public CombatRoundPosition(final String combatRoundPosition) {
        this.setValue(combatRoundPosition);
    }

    /**
     * Creates the position from a dice roll and the init bonus value.
     *
     * @param initRoll the init roll result.
     * @param initBonus the init bonus value.
     */
    public CombatRoundPosition(final DiceRoll initRoll,
            final BonusValue initBonus) {
        this.setValue(String.format(ROUND_POSITION_FORMAT,
                initRoll.getValue())
                + String.format(ROUND_POSITION_FORMAT,
                        initBonus.getValue()));
    }

    /**
     * Creates the position from a previous position and an init reroll. As it
     * is done when two combatants have the same init-bonus/init-roll
     * combination and the tie has to be resolved.
     *
     * @param roundPosition the previous round position.
     * @param initReRoll the result of the init reroll.
     */
    public CombatRoundPosition(final CombatRoundPosition roundPosition,
            final DiceRoll initReRoll) {
        this.setValue(roundPosition
                + String.format(ROUND_POSITION_FORMAT, initReRoll.getValue()));
    }

    /**
     * Compares this instance with another combat round position. The comparison
     * is done by value.
     *
     * @param crp the instance to compare with.
     * @return the result of the comparison.
     */
    public final int compareTo(final CombatRoundPosition crp) {
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
    public final int compareTo(final Object o) {
        if (!(o instanceof CombatRoundPosition)) {
            return -1;
        }
        CombatRoundPosition oCrPos = (CombatRoundPosition) o;
        return new CompareToBuilder()
                .append(this.getValue(), oCrPos.getValue()).toComparison();
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof CombatRoundPosition)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        CombatRoundPosition oParticle = (CombatRoundPosition) obj;
        return new EqualsBuilder()
                .append(this.getValue(), oParticle.getValue())
                .isEquals();
    }

    @Override
    public final int hashCode() {
        return new HashCodeBuilder(INITIAL_NON_ZERO_ODD_NUMBER,
                MULTIPLIER_NON_ZERO_ODD_NUMBER)
                .append(this.getValue())
                .toHashCode();
    }

}
