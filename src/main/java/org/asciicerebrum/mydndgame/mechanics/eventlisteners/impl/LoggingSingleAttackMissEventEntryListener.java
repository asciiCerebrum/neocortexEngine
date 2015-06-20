package org.asciicerebrum.mydndgame.mechanics.eventlisteners.impl;

import org.asciicerebrum.mydndgame.domain.events.EventEntry;
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

        LOG.info(this.getLogTemplate(), eventEntry.getWho().getValue(),
                eventEntry.getWhom().iterator().next().getValue(),
                eventEntry.getHow().iterator().next().getValue());
    }

}
