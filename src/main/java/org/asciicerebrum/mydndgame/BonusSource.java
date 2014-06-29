package org.asciicerebrum.mydndgame;

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
    List<Bonus> getBoni();

}
