package org.asciicerebrum.mydndgame.mechanics.eventlisteners;

import org.asciicerebrum.mydndgame.domain.events.RollHistoryEntry;

/**
 *
 * @author species8472
 */
public interface RollHistoryListener {

    void broadcast(RollHistoryEntry rollHistoryEntry);
    
}
