package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.interfaces.entities.IWorldDate;

/**
 *
 * @author species8472
 */
public class WorldDate implements IWorldDate {

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
    private Long combatRoundNumber;

    /**
     * The position within the combat round.
     */
    private String combatRoundPosition;

    /**
     * There cannot be a date without values.
     *
     * @param combatRoundNumberInput the initial combat round number.
     * @param combatRoundPositionInput the initial position within the combat
     * round.
     */
    public WorldDate(final Long combatRoundNumberInput,
            final String combatRoundPositionInput) {
        this.combatRoundNumber = combatRoundNumberInput;
        this.combatRoundPosition = combatRoundPositionInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setCombatRoundNumber(final Long combatRoundNumberInput) {
        this.combatRoundNumber = combatRoundNumberInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Long getCombatRoundNumber() {
        return this.combatRoundNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setCombatRoundPosition(
            final String combatRoundPositionInput) {
        this.combatRoundPosition = combatRoundPositionInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String getCombatRoundPosition() {
        return this.combatRoundPosition;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int compareTo(final IWorldDate t) {
        int comparison = this.combatRoundNumber.compareTo(
                t.getCombatRoundNumber());
        if (comparison == 0) {
            // negativate because of inverse order!
            comparison = -1 * this.combatRoundPosition.compareTo(
                    t.getCombatRoundPosition());
        }
        return comparison;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean equals(final Object o) {
        if (o instanceof IWorldDate) {
            IWorldDate oDate = (IWorldDate) o;

            return this.combatRoundNumber
                    .equals(oDate.getCombatRoundNumber())
                    && this.combatRoundPosition
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
        if (this.combatRoundNumber != null) {
            hash = hash + this.combatRoundNumber.hashCode();
        }
        hash = HASH_FACTOR * hash;
        if (this.combatRoundPosition != null) {
            hash = hash + this.combatRoundPosition.hashCode();
        }
        return hash;
    }

}
