package org.asciicerebrum.neocortexengine.mechanics.eventlisteners;

import org.asciicerebrum.neocortexengine.domain.events.RollHistoryEntry;

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
