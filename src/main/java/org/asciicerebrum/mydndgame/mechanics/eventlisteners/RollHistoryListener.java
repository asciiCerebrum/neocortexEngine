package org.asciicerebrum.mydndgame.mechanics.eventlisteners;

import org.asciicerebrum.mydndgame.domain.events.RollHistoryEntry;

/**
 *
 * @author species8472
 */
public interface RollHistoryListener {

    /**
     * Executes code needed to handle the given roll history event.
     *
     * @param rollHistoryEntry the event fired.
     */
    void broadcast(RollHistoryEntry rollHistoryEntry);

}
