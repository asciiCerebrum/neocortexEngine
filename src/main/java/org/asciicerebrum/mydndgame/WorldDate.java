package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.interfaces.entities.IWorldDate;

/**
 *
 * @author species8472
 */
public class WorldDate implements IWorldDate {

    /**
     * The round number within the combat encounter.
     */
    private Long combatRoundNumber;

    /**
     * The position within the combat round.
     */
    private String combatRoundPosition;

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
    public int compareTo(IWorldDate t) {
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
    public boolean equals(Object o) {
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
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + (this.combatRoundNumber != null
                ? this.combatRoundNumber.hashCode() : 0);
        hash = 83 * hash + (this.combatRoundPosition != null
                ? this.combatRoundPosition.hashCode() : 0);
        return hash;
    }

}
