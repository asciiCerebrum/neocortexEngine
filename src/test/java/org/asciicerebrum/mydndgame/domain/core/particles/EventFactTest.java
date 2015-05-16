package org.asciicerebrum.mydndgame.domain.core.particles;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author species8472
 */
public class EventFactTest {

    private EventFact fact;

    public EventFactTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.fact = new EventFact("fact");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void equalsSameInstanceTest() {
        assertTrue(this.fact.equals(this.fact));
    }

    @Test
    public void equalsDiffInstanceTrueTest() {
        assertTrue(this.fact.equals(new EventFact("fact")));
    }

    @Test
    public void equalsDiffInstanceFalseTest() {
        assertFalse(this.fact.equals(new EventFact("fact2")));
    }

    @Test
    public void equalsOtherClassTest() {
        assertFalse(this.fact.equals(new UniqueId("uniqueId")));
    }

}
