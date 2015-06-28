package org.asciicerebrum.neocortexengine.mechanics.eventlisteners.impl;

import org.asciicerebrum.neocortexengine.domain.events.EventEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author species8472
 */
public class LoggingWhoSingleWhomWithWhatEventEntryListener
        extends LoggingTemplateBasedEventEntryListener {

    /**
     * The logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(
            LoggingRollHistoryListener.class);

    @Override
    public final void trigger(final EventEntry eventEntry) {

        LOG.info(this.getLogTemplate(),
                eventEntry.getWho().getValue(),
                eventEntry.getWhom().iterator().next(),
                eventEntry.getWhat().getValue());
    }

}
