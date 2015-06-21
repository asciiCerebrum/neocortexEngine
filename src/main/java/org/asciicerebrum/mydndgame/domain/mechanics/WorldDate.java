package org.asciicerebrum.mydndgame.domain.mechanics;

import org.asciicerebrum.mydndgame.domain.core.particles.CombatRoundNumber;
import org.asciicerebrum.mydndgame.domain.core.particles.CombatRoundPosition;

/**
 *
 * @author species8472
 */
public class WorldDate implements Comparable {

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

    /**
     * Default constructor without parameters.
     */
    public WorldDate() {

    }

    /**
     * Construct new world date from a given world date. Copy constructor.
     *
     * @param worldDate the world date to clone.
     */
    public WorldDate(final WorldDate worldDate) {
        this.combatRoundNumber = worldDate.combatRoundNumber;
        this.combatRoundPosition = worldDate.combatRoundPosition;
    }

    /**
     * Initializes a world date instance with very basic parameters. The initial
     * value of the combat round number is 0.
     *
     * @param position the position the world date should have.
     */
    public final void initializeDate(final CombatRoundPosition position) {
        this.setCombatRoundNumber(new CombatRoundNumber(0L));
        this.setCombatRoundPosition(position);
    }

    /**
     * Tests if given world date is after this instance.
     *
     * @param t the world date to test.
     * @return true if date is after this one, false otherwise.
     */
    public final boolean isAfter(final WorldDate t) {
        return this.compareTo(t) > 0;
    }

    /**
     * Tests if given world date is after this instance or at the same time.
     *
     * @param t the world date to test.
     * @return true if date is after this one or at the same time, false
     * otherwise.
     */
    public final boolean isAfterOrEqual(final WorldDate t) {
        return this.compareTo(t) >= 0;
    }

    @Override
    public final int compareTo(final Object o) {
        if (!(o instanceof WorldDate)) {
            return 0;
        }
        final WorldDate t = (WorldDate) o;
        int comparison = this.getCombatRoundNumber().compareTo(
                t.getCombatRoundNumber());
        if (comparison == 0) {
            comparison = this.getCombatRoundPosition().compareTo(
                    t.getCombatRoundPosition());
        }
        return comparison;
    }

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

    /**
     * Increments the round number of this world date by 1.
     */
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
