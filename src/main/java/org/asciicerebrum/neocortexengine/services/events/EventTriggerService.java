package org.asciicerebrum.neocortexengine.services.events;

import org.asciicerebrum.neocortexengine.domain.events.EventEntry;

/**
 *
 * @author species8472
 */
public interface EventTriggerService {

    /**
     * Triggers all registered listeners with the given event.
     *
     * @param eventEntry the event fired.
     */
    void trigger(EventEntry eventEntry);

}
