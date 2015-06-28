package org.asciicerebrum.neocortexengine.mechanics.eventlisteners.impl;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.Appender;
import org.asciicerebrum.neocortexengine.domain.core.particles.EventFact;
import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueId;
import org.asciicerebrum.neocortexengine.domain.events.EventEntry;
import org.asciicerebrum.neocortexengine.domain.events.EventType;
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
public class LoggingWhoHowEventEntryListenerTest {

    private LoggingWhoHowEventEntryListener listener;

    private Appender mockAppender;

    private ArgumentCaptor<LoggingEvent> captorLoggingEvent;

    public LoggingWhoHowEventEntryListenerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.listener = new LoggingWhoHowEventEntryListener();

        this.mockAppender = mock(Appender.class);
        this.captorLoggingEvent = ArgumentCaptor.forClass(LoggingEvent.class);

        final Logger logger = (Logger) LoggerFactory
                .getLogger("org.asciicerebrum.neocortexengine"
                        + ".mechanics.eventlisteners");
        logger.addAppender(this.mockAppender);
    }

    @After
    public void tearDown() {
        final Logger logger = (Logger) LoggerFactory.getLogger(
                "org.asciicerebrum.neocortexengine.mechanics.eventlisteners");
        logger.detachAppender(this.mockAppender);
    }

    @Test
    public void triggerWithHowsTest() {
        final EventEntry eventEntry = new EventEntry(
                EventType.COMBATROUND_PREINIT);

        eventEntry.addEventFact(new EventFact("how1"));
        eventEntry.addEventFact(new EventFact("how2"));

        eventEntry.setWho(new UniqueId("who"));

        this.listener.setLogTemplate("{} to {} to {}.");
        this.listener.trigger(eventEntry);

        verify(this.mockAppender).doAppend(this.captorLoggingEvent.capture());
        final LoggingEvent loggingEvent = this.captorLoggingEvent.getValue();
        assertThat(loggingEvent.getFormattedMessage(),
                is("who to how1 to how2."));
    }

}
