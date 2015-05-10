package org.asciicerebrum.mydndgame.mechanics.rollhistorylisteners.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.RollHistoryEntry;
import org.asciicerebrum.mydndgame.mechanics.rollhistorylisteners.RollHistoryListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author species8472
 */
public class LoggingRollHistoryListener implements RollHistoryListener {

    /**
     * The logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(
            LoggingRollHistoryListener.class);

    /**
     * Format for the bonus value. The sign has to be included.
     */
    private static final DecimalFormat FMT = new DecimalFormat("+#;-#");

    @Override
    public final void broadcast(final RollHistoryEntry rollHistoryEntry) {

        final StringBuilder logMessage = new StringBuilder(
                "Roll History: {} rolls for {}");
        final List<Object> logVars = new ArrayList<Object>();
        logVars.add(rollHistoryEntry.getSourceDndCharacterId().getValue());
        logVars.add(rollHistoryEntry.getDiceActionId().getValue());

        if (rollHistoryEntry.getContextEntityId() != null) {
            logMessage.append(" (with {})");
            logVars.add(rollHistoryEntry.getContextEntityId().getValue());
        }

        logMessage.append(": {}d{}");
        logVars.add(rollHistoryEntry.getDiceNumber().getValue());
        logVars.add(rollHistoryEntry.getDiceSides().getValue());

        if (rollHistoryEntry.getBonusValue() != null
                && rollHistoryEntry.getBonusValue().getValue() != 0) {
            logMessage.append("{}");
            logVars.add(FMT.format(
                    rollHistoryEntry.getBonusValue().getValue()));
        }

        logMessage.append(" = {}.");
        logVars.add(rollHistoryEntry.getTotalResult().getValue());

        LOG.info(logMessage.toString(), logVars.toArray());
    }

}
