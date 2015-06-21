package org.asciicerebrum.mydndgame.mechanics.eventlisteners.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.asciicerebrum.mydndgame.domain.core.particles.EventFact;
import org.asciicerebrum.mydndgame.domain.events.EventEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author species8472
 */
public class LoggingWhoHowEventEntryListener
        extends LoggingTemplateBasedEventEntryListener {

    /**
     * The logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(
            LoggingRollHistoryListener.class);

    @Override
    public final void trigger(final EventEntry eventEntry) {

        final List<String> logList = new ArrayList<String>();

        logList.add(eventEntry.getWho().getValue());
        final Iterator<EventFact> howIterator = eventEntry.getHow().iterator();
        while (howIterator.hasNext()) {
            logList.add(howIterator.next().getValue());
        }

        LOG.info(this.getLogTemplate(), logList.toArray());
    }

}
