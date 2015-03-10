package org.asciicerebrum.mydndgame.domain.ruleentities;

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
public class BodySlotsTest {

    private BodySlots bodySlots;

    private BodySlotType correctType;

    public BodySlotsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.bodySlots = new BodySlots();
        this.correctType = new BodySlotType();

        final BodySlot slotA = new BodySlot();
        slotA.setBodySlotType(new BodySlotType());

        final BodySlot slotB = new BodySlot();
        slotB.setBodySlotType(this.correctType);

        final BodySlot slotC = new BodySlot();
        slotC.setBodySlotType(new BodySlotType());

        this.bodySlots.add(slotA);
        this.bodySlots.add(slotB);
        this.bodySlots.add(slotC);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void containsTypeFalseTest() {
        assertFalse(this.bodySlots.containsType(new BodySlotType()));
    }

    @Test
    public void containsTypeTrueTest() {
        assertTrue(this.bodySlots.containsType(this.correctType));
    }

}
