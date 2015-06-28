package org.asciicerebrum.neocortexengine.services.events;

import java.util.List;
import org.asciicerebrum.neocortexengine.domain.events.EventEntry;
import org.asciicerebrum.neocortexengine.mechanics.eventlisteners.EventEntryListener;

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
