package org.asciicerebrum.mydndgame.mechanics.rollhistorylisteners;

import org.asciicerebrum.mydndgame.domain.ruleentities.composition.RollHistoryEntry;

/**
 *
 * @author species8472
 */
public interface RollHistoryListener {

    void broadcast(RollHistoryEntry rollHistoryEntry);
    
}
