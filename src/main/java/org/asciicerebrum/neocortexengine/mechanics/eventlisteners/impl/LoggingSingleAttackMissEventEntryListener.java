package org.asciicerebrum.neocortexengine.mechanics.eventlisteners.impl;

import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.neocortexengine.domain.events.EventEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author species8472
 */
public class LoggingSingleAttackMissEventEntryListener
        extends LoggingTemplateBasedEventEntryListener {

    /**
     * The logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(
            LoggingSingleAttackMissEventEntryListener.class);

    @Override
    public final void trigger(final EventEntry eventEntry) {

        final List<Object> logList = new ArrayList<Object>();
        logList.add(eventEntry.getWho().getValue());
        if (eventEntry.getWhat() != null) {
            logList.add(eventEntry.getWhat().getValue());
        }
        logList.add(eventEntry.getWhom().iterator().next().getValue());
        logList.add(eventEntry.getHow().iterator().next().getValue());

        LOG.info(this.getLogTemplate(), logList.toArray());
    }

}
