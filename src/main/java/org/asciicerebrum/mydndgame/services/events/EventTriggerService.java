package org.asciicerebrum.mydndgame.services.events;

import org.asciicerebrum.mydndgame.domain.events.EventEntry;

/**
 *
 * @author species8472
 */
public interface EventTriggerService {
    

    void trigger(EventEntry eventEntry);
    
    
}
