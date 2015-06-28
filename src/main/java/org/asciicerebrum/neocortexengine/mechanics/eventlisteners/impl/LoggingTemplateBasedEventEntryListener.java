package org.asciicerebrum.neocortexengine.mechanics.eventlisteners.impl;

import org.asciicerebrum.neocortexengine.domain.events.EventEntry;
import org.asciicerebrum.neocortexengine.domain.events.EventType;
import org.asciicerebrum.neocortexengine.mechanics.eventlisteners.EventEntryListener;

/**
 *
 * @author species8472
 */
public abstract class LoggingTemplateBasedEventEntryListener
        implements EventEntryListener {

    /**
     * The logging template containing two placeholders for who and what.
     */
    private String logTemplate;

    /**
     * The type of event to log.
     */
    private EventType eventType;

    @Override
    public final boolean isApplicable(final EventEntry eventEntry) {
        return this.getEventType().equals(eventEntry.getEventType());
    }

    /**
     * @return the logTemplate
     */
    public final String getLogTemplate() {
        return logTemplate;
    }

    /**
     * @param logTemplateInput the logTemplate to set
     */
    public final void setLogTemplate(final String logTemplateInput) {
        this.logTemplate = logTemplateInput;
    }

    /**
     * @return the eventType
     */
    public final EventType getEventType() {
        return eventType;
    }

    /**
     * @param eventTypeInput the eventType to set
     */
    public final void setEventType(final EventType eventTypeInput) {
        this.eventType = eventTypeInput;
    }

}
