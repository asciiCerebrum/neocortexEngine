package org.asciicerebrum.mydndgame.mechanics.eventlisteners.impl;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.Appender;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.core.particles.DiceNumber;
import org.asciicerebrum.mydndgame.domain.core.particles.DiceRoll;
import org.asciicerebrum.mydndgame.domain.core.particles.DiceSides;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.events.RollHistoryEntry;
import static org.hamcrest.Matchers.is;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.slf4j.LoggerFactory;

/**
 *
 * @author species8472
 */
public class LoggingRollHistoryListenerTest {

    private LoggingRollHistoryListener listener;

    private Appender mockAppender;

    private ArgumentCaptor<LoggingEvent> captorLoggingEvent;

    public LoggingRollHistoryListenerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.listener = new LoggingRollHistoryListener();
        this.mockAppender = mock(Appender.class);
        this.captorLoggingEvent = ArgumentCaptor.forClass(LoggingEvent.class);

        final Logger logger = (Logger) LoggerFactory
                .getLogger("org.asciicerebrum.mydndgame"
                        + ".mechanics.eventlisteners");
        logger.addAppender(this.mockAppender);
    }

    @After
    public void tearDown() {
        final Logger logger = (Logger) LoggerFactory.getLogger(
                "org.asciicerebrum.mydndgame.mechanics.eventlisteners");
        logger.detachAppender(this.mockAppender);
    }

    private void completeEntry(final RollHistoryEntry rollHistoryEntry) {
        rollHistoryEntry.setSourceDndCharacterId(new UniqueId("source"));
        rollHistoryEntry.setDiceActionId(new UniqueId("action"));
        rollHistoryEntry.setDiceNumber(new DiceNumber(1L));
        rollHistoryEntry.setDiceSides(new DiceSides(20L));
        rollHistoryEntry.setTotalResult(new DiceRoll(16L));
    }

    @Test
    public void broadcastSimpleTest() {
        final RollHistoryEntry rollHistoryEntry = new RollHistoryEntry();
        this.completeEntry(rollHistoryEntry);

        this.listener.broadcast(rollHistoryEntry);

        verify(mockAppender).doAppend(captorLoggingEvent.capture());
        final LoggingEvent loggingEvent = this.captorLoggingEvent.getValue();
        assertThat(loggingEvent.getFormattedMessage(),
                is("Roll History: source rolls for action:"
                        + " 1d20 = 16."));
    }

    @Test
    public void broadcastWithContextTest() {
        final RollHistoryEntry rollHistoryEntry = new RollHistoryEntry();
        this.completeEntry(rollHistoryEntry);
        rollHistoryEntry.setContextEntityId(new UniqueId("context"));

        this.listener.broadcast(rollHistoryEntry);

        verify(mockAppender).doAppend(captorLoggingEvent.capture());
        final LoggingEvent loggingEvent = this.captorLoggingEvent.getValue();
        assertThat(loggingEvent.getFormattedMessage(),
                is("Roll History: source rolls for action (with context):"
                        + " 1d20 = 16."));
    }

    @Test
    public void broadcastWithBonusTest() {
        final RollHistoryEntry rollHistoryEntry = new RollHistoryEntry();
        this.completeEntry(rollHistoryEntry);
        rollHistoryEntry.setBonusValue(new BonusValue(5L));

        this.listener.broadcast(rollHistoryEntry);

        verify(mockAppender).doAppend(captorLoggingEvent.capture());
        final LoggingEvent loggingEvent = this.captorLoggingEvent.getValue();
        assertThat(loggingEvent.getFormattedMessage(),
                is("Roll History: source rolls for action:"
                        + " 1d20+5 = 16."));
    }

    @Test
    public void broadcastWithZeroBonusTest() {
        final RollHistoryEntry rollHistoryEntry = new RollHistoryEntry();
        this.completeEntry(rollHistoryEntry);
        rollHistoryEntry.setBonusValue(new BonusValue(0L));

        this.listener.broadcast(rollHistoryEntry);

        verify(mockAppender).doAppend(captorLoggingEvent.capture());
        final LoggingEvent loggingEvent = this.captorLoggingEvent.getValue();
        assertThat(loggingEvent.getFormattedMessage(),
                is("Roll History: source rolls for action:"
                        + " 1d20 = 16."));
    }

}
