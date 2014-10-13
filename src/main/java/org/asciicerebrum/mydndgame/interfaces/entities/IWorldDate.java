package org.asciicerebrum.mydndgame.interfaces.entities;

/**
 *
 * @author species8472
 */
public interface IWorldDate extends Comparable<IWorldDate> {

    /**
     * @param combatRoundNumber the round number in the combat encounter.
     */
    void setCombatRoundNumber(Long combatRoundNumber);

    /**
     * @return the round number in the combat encounter.
     */
    Long getCombatRoundNumber();

    /**
     * @param combatRoundPosition the position number within the combat round.
     */
    void setCombatRoundPosition(String combatRoundPosition);

    /**
     * @return the position number within the combat round.
     */
    String getCombatRoundPosition();

}
