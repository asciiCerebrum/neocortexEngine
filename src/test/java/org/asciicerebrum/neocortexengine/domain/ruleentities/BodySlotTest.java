package org.asciicerebrum.neocortexengine.domain.ruleentities;

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
public class BodySlotTest {

    private BodySlot bodySlot;

    private BodySlotType bodySlotType;

    public BodySlotTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.bodySlot = new BodySlot();
        this.bodySlotType = new BodySlotType();

        this.bodySlot.setBodySlotType(this.bodySlotType);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void isOfTypeTrueTest() {
        assertTrue(this.bodySlot.isOfType(this.bodySlotType));
    }

    @Test
    public void isOfTypeFalseTest() {
        assertFalse(this.bodySlot.isOfType(new BodySlotType()));
    }

}
