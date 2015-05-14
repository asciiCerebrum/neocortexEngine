package org.asciicerebrum.mydndgame.mechanics.eventlisteners;

import org.asciicerebrum.mydndgame.domain.events.EventEntry;

/**
 *
 * @author species8472
 */
public interface EventEntryListener {
    
    void trigger(EventEntry eventEntry);
    
    boolean isApplicable(EventEntry eventEntry);
}
