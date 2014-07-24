package org.asciicerebrum.mydndgame.interfaces.entities;

import java.util.List;

/**
 *
 * @author species8472
 */
public interface BonusSource {

    /**
     *
     * @return the list of all boni this source can provide.
     */
    List<IBonus> getBoni();

}
