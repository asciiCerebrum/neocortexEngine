package org.asciicerebrum.mydndgame.domain.game.aspect;

import org.asciicerebrum.mydndgame.domain.core.particles.CombatRoundNumber;
import org.asciicerebrum.mydndgame.domain.core.particles.CombatRoundPosition;

/**
 *
 * @author species8472
 */
public class WorldDate {

    /**
     * Base value for hash sum.
     */
    private static final int HASH_BASE = 3;
    /**
     * Factor for hash sum.
     */
    private static final int HASH_FACTOR = 83;

    /**
     * The round number within the combat encounter.
     */
    private CombatRoundNumber combatRoundNumber;

    /**
     * The position within the combat round.
     */
    private CombatRoundPosition combatRoundPosition;

    public WorldDate() {

    }

    public WorldDate(final WorldDate worldDate) {
        this.combatRoundNumber = worldDate.combatRoundNumber;
        this.combatRoundPosition = worldDate.combatRoundPosition;
    }

    public final boolean isAfter(final WorldDate t) {
        return this.compareTo(t) > 0;
    }

    /**
     * {@inheritDoc}
     */
    public final int compareTo(final WorldDate t) {
        int comparison = this.getCombatRoundNumber().compareTo(
                t.getCombatRoundNumber());
        if (comparison == 0) {
            // negativate because of inverse order!
            comparison = -1 * this.getCombatRoundPosition().compareTo(
                    t.getCombatRoundPosition());
        }
        return comparison;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean equals(final Object o) {
        if (o instanceof WorldDate) {
            WorldDate oDate = (WorldDate) o;

            return this.getCombatRoundNumber()
                    .equals(oDate.getCombatRoundNumber())
                    && this.getCombatRoundPosition()
                    .equals(oDate.getCombatRoundPosition());
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int hashCode() {
        int hash = HASH_BASE;
        hash = HASH_FACTOR * hash;
        if (this.getCombatRoundNumber() != null) {
            hash = hash + this.getCombatRoundNumber().hashCode();
        }
        hash = HASH_FACTOR * hash;
        if (this.getCombatRoundPosition() != null) {
            hash = hash + this.getCombatRoundPosition().hashCode();
        }
        return hash;
    }

    public final void incrementRoundNumber() {
        this.combatRoundNumber = new CombatRoundNumber(
                this.combatRoundNumber.getValue() + 1);
    }

    /**
     * @return the combatRoundNumber
     */
    public final CombatRoundNumber getCombatRoundNumber() {
        return combatRoundNumber;
    }

    /**
     * @param combatRoundNumberInput the combatRoundNumber to set
     */
    public final void setCombatRoundNumber(
            final CombatRoundNumber combatRoundNumberInput) {
        this.combatRoundNumber = combatRoundNumberInput;
    }

    /**
     * @return the combatRoundPosition
     */
    public final CombatRoundPosition getCombatRoundPosition() {
        return combatRoundPosition;
    }

    /**
     * @param combatRoundPositionInput the combatRoundPosition to set
     */
    public final void setCombatRoundPosition(
            final CombatRoundPosition combatRoundPositionInput) {
        this.combatRoundPosition = combatRoundPositionInput;
    }

}
