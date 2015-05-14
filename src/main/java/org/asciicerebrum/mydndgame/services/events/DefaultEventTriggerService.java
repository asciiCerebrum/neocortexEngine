package org.asciicerebrum.mydndgame.services.events;

import java.util.List;
import org.asciicerebrum.mydndgame.domain.events.EventEntry;
import org.asciicerebrum.mydndgame.mechanics.eventlisteners.EventEntryListener;

/**
 *
 * @author species8472
 */
public class DefaultEventTriggerService implements EventTriggerService {

    /**
     * The list of registered event listeners.
     */
    private List<EventEntryListener> eventEntryListeners;

    @Override
    public final void trigger(final EventEntry eventEntry) {

        for (final EventEntryListener listener
                : this.getEventEntryListeners()) {
            if (listener.isApplicable(eventEntry)) {
                listener.trigger(eventEntry);
            }
        }
    }

    /**
     * @return the eventEntryListeners
     */
    public final List<EventEntryListener> getEventEntryListeners() {
        return eventEntryListeners;
    }

    /**
     * @param eventEntryListenersInput the eventEntryListeners to set
     */
    public final void setEventEntryListeners(
            final List<EventEntryListener> eventEntryListenersInput) {
        this.eventEntryListeners = eventEntryListenersInput;
    }
}
