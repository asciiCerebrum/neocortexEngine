package org.asciicerebrum.mydndgame.mechanics.eventlisteners.impl;

import org.apache.commons.lang3.StringUtils;
import org.asciicerebrum.mydndgame.domain.events.EventEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author species8472
 */
public class LoggingWhomEventEntryListener
        extends LoggingTemplateBasedEventEntryListener {

    /**
     * The logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(
            LoggingWhomEventEntryListener.class);

    private String listSeparator;

    @Override
    public final void trigger(final EventEntry eventEntry) {

        final String idsString
                = StringUtils.join(eventEntry.getWhom().iterator(),
                        this.getListSeparator());

        LOG.info(this.getLogTemplate(), idsString);
    }

    /**
     * @return the listSeparator
     */
    public final String getListSeparator() {
        return listSeparator;
    }

    /**
     * @param listSeparatorInput the listSeparator to set
     */
    public final void setListSeparator(final String listSeparatorInput) {
        this.listSeparator = listSeparatorInput;
    }

}
