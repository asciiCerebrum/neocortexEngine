package org.asciicerebrum.mydndgame.mechanics.eventlisteners.impl;

import org.asciicerebrum.mydndgame.domain.events.EventEntry;
import org.asciicerebrum.mydndgame.domain.events.EventType;
import org.asciicerebrum.mydndgame.mechanics.eventlisteners.EventEntryListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author species8472
 */
public class LoggingWhoWhatEventEntryListener
        implements EventEntryListener {

    /**
     * The logging template containing two placeholders for who and what.
     */
    private String logTemplate;

    /**
     * The type of event to log.
     */
    private EventType eventType;

    /**
     * The logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(
            LoggingRollHistoryListener.class);

    @Override
    public final void trigger(final EventEntry eventEntry) {

        LOG.info(this.getLogTemplate(),
                eventEntry.getWho().getValue(),
                eventEntry.getWhat().getValue());
    }

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
