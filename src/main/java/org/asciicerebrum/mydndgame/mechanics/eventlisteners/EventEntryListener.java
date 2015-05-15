package org.asciicerebrum.mydndgame.mechanics.eventlisteners;

import org.asciicerebrum.mydndgame.domain.events.EventEntry;

/**
 *
 * @author species8472
 */
public interface EventEntryListener {

    /**
     * Executes code needed to handle the given event.
     *
     * @param eventEntry the event fired.
     */
    void trigger(EventEntry eventEntry);

    /**
     * Tests if object is a valid event listener for the given event.
     *
     * @param eventEntry the event in question.
     * @return true if applicable, false otherwise.
     */
    boolean isApplicable(EventEntry eventEntry);
}
