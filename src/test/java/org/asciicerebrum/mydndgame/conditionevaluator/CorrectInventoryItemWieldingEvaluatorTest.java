package org.asciicerebrum.mydndgame.conditionevaluator;

import org.asciicerebrum.mydndgame.interfaces.entities.IBodySlotType;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.IInventoryItem;
import org.asciicerebrum.mydndgame.interfaces.entities.ISituationContext;
import org.asciicerebrum.mydndgame.interfaces.entities.Slotable;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author species8472
 */
public class CorrectInventoryItemWieldingEvaluatorTest {

    private CorrectInventoryItemWieldingEvaluator ciiwEvaluator;

    private ISituationContext sitCon;

    private ICharacter character;

    private IBodySlotType bodySlotTypeMock;

    private IBodySlotType counterSlotTypeMock;

    private Slotable bodySlotMock;

    private Slotable counterSlot;

    private IInventoryItem itemMock;

    public CorrectInventoryItemWieldingEvaluatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        this.ciiwEvaluator = new CorrectInventoryItemWieldingEvaluator();

        this.sitCon = mock(ISituationContext.class);
        this.bodySlotTypeMock = mock(IBodySlotType.class);
        this.bodySlotMock = mock(Slotable.class);
        this.itemMock = mock(IInventoryItem.class);
        this.counterSlotTypeMock = mock(IBodySlotType.class);
        this.counterSlot = mock(Slotable.class);
        this.character = mock(ICharacter.class);

        when(this.character.getSituationContext()).thenReturn(this.sitCon);
        when(this.sitCon.getBodySlotType()).thenReturn(this.bodySlotTypeMock);
        when(this.character.getBodySlotByType(this.bodySlotTypeMock))
                .thenReturn(this.bodySlotMock);
        when(this.bodySlotMock.getItem()).thenReturn(this.itemMock);
        when(this.bodySlotMock.getBodySlotType()).thenReturn(this.bodySlotTypeMock);
        when(this.bodySlotTypeMock.getCounterSlot())
                .thenReturn(this.counterSlotTypeMock);
        when(this.character.getBodySlotByType(this.counterSlotTypeMock))
                .thenReturn(this.counterSlot);
        when(this.counterSlot.getItem()).thenReturn(null);

        when(this.bodySlotTypeMock.getIsPrimaryAttackSlot())
                .thenReturn(Boolean.FALSE);

        this.ciiwEvaluator.setWieldingType(
                CorrectInventoryItemWieldingEvaluator.WieldingType.SECONDARY);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of evaluate method, of class CorrectInventoryItemWieldingEvaluator.
     */
    @Test
    public void testEvaluate() {
        Boolean resultEval = this.ciiwEvaluator.evaluate(this.character);

        assertTrue(resultEval);
    }

    @Test
    public void testEvaluateIsPrimary() {
        when(this.bodySlotTypeMock.getIsPrimaryAttackSlot())
                .thenReturn(Boolean.TRUE);

        Boolean resultEval = this.ciiwEvaluator.evaluate(this.character);

        assertFalse(resultEval);
    }

    @Test
    public void testEvaluateNoWieldingType() {
        this.ciiwEvaluator.setWieldingType(null);

        Boolean resultEval = this.ciiwEvaluator.evaluate(this.character);

        assertFalse(resultEval);
    }

    @Test
    public void testEvaluateTypeBothFalse() {
        this.ciiwEvaluator.setWieldingType(
                CorrectInventoryItemWieldingEvaluator.WieldingType.BOTH);

        Boolean resultEval = this.ciiwEvaluator.evaluate(this.character);

        assertFalse(resultEval);
    }

    @Test
    public void testEvaluateTypeBothTrue() {
        when(this.counterSlot.getItem()).thenReturn(this.itemMock);

        this.ciiwEvaluator.setWieldingType(
                CorrectInventoryItemWieldingEvaluator.WieldingType.BOTH);

        Boolean resultEval = this.ciiwEvaluator.evaluate(this.character);

        assertTrue(resultEval);
    }

    @Test
    public void testEvaluateTypeBothFalseNoItemInBothSlots() {
        when(this.bodySlotMock.getItem()).thenReturn(null);

        this.ciiwEvaluator.setWieldingType(
                CorrectInventoryItemWieldingEvaluator.WieldingType.BOTH);

        Boolean resultEval = this.ciiwEvaluator.evaluate(this.character);

        assertFalse(resultEval);
    }

    @Test
    public void testEvaluateTypeBothFalseNoItemInMainSlot() {
        when(this.bodySlotMock.getItem()).thenReturn(null);
        when(this.counterSlot.getItem()).thenReturn(this.itemMock);

        this.ciiwEvaluator.setWieldingType(
                CorrectInventoryItemWieldingEvaluator.WieldingType.BOTH);

        Boolean resultEval = this.ciiwEvaluator.evaluate(this.character);

        assertFalse(resultEval);
    }

}
