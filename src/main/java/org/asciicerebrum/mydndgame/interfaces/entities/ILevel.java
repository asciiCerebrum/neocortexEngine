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

    IBonus getBaseAtkBonusByRank(Long rank);

    List<IBonus> getBaseAtkBoni();

    Long getBaseAtkBonusValueDeltaByRank(Long rank);
}
