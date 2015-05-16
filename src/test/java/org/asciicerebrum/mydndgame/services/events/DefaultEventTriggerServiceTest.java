package org.asciicerebrum.mydndgame.services.events;

import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.mydndgame.domain.events.EventEntry;
import org.asciicerebrum.mydndgame.domain.events.EventType;
import org.asciicerebrum.mydndgame.mechanics.eventlisteners.EventEntryListener;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author species8472
 */
public class DefaultEventTriggerServiceTest {

    private DefaultEventTriggerService service;

    private EventEntryListener listenerA;

    private EventEntryListener listenerB;

    private List<EventEntryListener> listeners;

    public DefaultEventTriggerServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.service = new DefaultEventTriggerService();
        this.listenerA = mock(EventEntryListener.class);
        this.listenerB = mock(EventEntryListener.class);
        this.listeners = new ArrayList<EventEntryListener>();

        this.listeners.add(this.listenerA);
        this.listeners.add(this.listenerB);

        this.service.setEventEntryListeners(this.listeners);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void triggerApplicableTest() {
        final EventEntry event = new EventEntry(EventType.CONDITION_GAIN);

        when(this.listenerA.isApplicable(event)).thenReturn(Boolean.FALSE);
        when(this.listenerB.isApplicable(event)).thenReturn(Boolean.TRUE);

        this.service.trigger(event);

        verify(this.listenerB, times(1)).trigger(event);
    }

    @Test
    public void triggerNotApplicableTest() {
        final EventEntry event = new EventEntry(EventType.CONDITION_GAIN);

        when(this.listenerA.isApplicable(event)).thenReturn(Boolean.FALSE);
        when(this.listenerB.isApplicable(event)).thenReturn(Boolean.TRUE);

        this.service.trigger(event);

        verify(this.listenerA, times(0)).trigger(event);
    }

}
