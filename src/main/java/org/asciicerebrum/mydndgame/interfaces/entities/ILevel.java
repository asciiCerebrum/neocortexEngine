package org.asciicerebrum.mydndgame.interfaces.entities;

import java.util.List;

/**
 *
 * @author Tabea Raab
 */
public interface ILevel {

    /**
     *
     * @return the level of this ILevel.
     */
    Integer getLevel();

    /**
     *
     * @param rank the rank of the base attack bonus. E.g. when you have
     * +10/+5/+1 then the bonus +5 is of rank 1, as rank starts with 0.
     * @return the one base attack bonus of the given rank.
     */
    IBonus getBaseAtkBonusByRank(Long rank);

    /**
     * @return the baseAtkBoni.
     */
    List<IBonus> getBaseAtkBoni();

    /**
     * Calculate the difference of base attack bonus of given rank of this level
     * and the previous one.
     *
     * @param rank the rank of the class level in question.
     * @return the difference of base attack boni.
     */
    Long getBaseAtkBonusValueDeltaByRank(Long rank);
}
