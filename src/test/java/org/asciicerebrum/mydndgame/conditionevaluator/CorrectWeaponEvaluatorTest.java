package org.asciicerebrum.mydndgame.conditionevaluator;

import org.asciicerebrum.mydndgame.SituationContext;
import org.asciicerebrum.mydndgame.interfaces.entities.IBodySlotType;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.IInventoryItem;
import org.asciicerebrum.mydndgame.interfaces.entities.ISituationContext;
import org.asciicerebrum.mydndgame.interfaces.entities.IWeapon;
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
public class CorrectWeaponEvaluatorTest {

    private CorrectWeaponEvaluator correctWeaponEval;

    private ISituationContext mockContext;

    private ICharacter mockCharacter;

    public CorrectWeaponEvaluatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        IWeapon mockWeapon = mock(IWeapon.class);
        this.mockContext = mock(ISituationContext.class);

        this.mockCharacter = mock(ICharacter.class);
        IBodySlotType mockBsType = mock(IBodySlotType.class);
        Slotable mockBs = mock(Slotable.class);
        IWeapon mockRefWeapon = mock(IWeapon.class);

        when(this.mockCharacter.getSituationContext()).thenReturn(this.mockContext);
        when(this.mockContext.getBodySlotType()).thenReturn(mockBsType);

        when(this.mockCharacter.getBodySlotByType(mockBsType)).thenReturn(mockBs);
        when(mockBs.getItem()).thenReturn(mockRefWeapon);

        when(mockWeapon.resembles(mockRefWeapon)).thenReturn(Boolean.TRUE);

        this.correctWeaponEval = new CorrectWeaponEvaluator();
        this.correctWeaponEval.setWeapon(mockWeapon);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of evaluate method, of class CorrectWeaponEvaluator. Empty situation
     * context.
     */
    @Test
    public void testEvaluateEmptySitCon() {
        when(this.mockCharacter.getSituationContext())
                .thenReturn(new SituationContext());

        Boolean isCorrect = this.correctWeaponEval
                .evaluate(this.mockCharacter);

        assertFalse(isCorrect);
    }

    /**
     * Standard succeeding case of a resembling weapon.
     */
    @Test
    public void testEvaluate() {
        Boolean isCorrect = this.correctWeaponEval.evaluate(this.mockCharacter);

        assertTrue(isCorrect);
    }

    /**
     * Case of null weapon.
     */
    @Test
    public void testEvaluateNullWeapon() {
        this.correctWeaponEval.setWeapon(null);
        Boolean isCorrect = this.correctWeaponEval.evaluate(this.mockCharacter);

        assertTrue(isCorrect);
    }

    /**
     * No item in the body slot.
     */
    @Test
    public void testEvaluateEmptyBodySlot() {

        ICharacter noItemCharacter = mock(ICharacter.class);
        ISituationContext noItemContext = mock(ISituationContext.class);

        IBodySlotType mockBsType = mock(IBodySlotType.class);
        Slotable mockBs = mock(Slotable.class);

        when(noItemCharacter.getSituationContext()).thenReturn(noItemContext);
        when(noItemContext.getBodySlotType()).thenReturn(mockBsType);

        when(noItemCharacter.getBodySlotByType(mockBsType)).thenReturn(mockBs);
        when(mockBs.getItem()).thenReturn(null);

        Boolean isCorrect = this.correctWeaponEval.evaluate(noItemCharacter);

        assertFalse(isCorrect);
    }

    /**
     * Item in the body slot is not a weapon.
     */
    @Test
    public void testEvaluateWrongItemInBodySlot() {

        ICharacter wrongItemCharacter = mock(ICharacter.class);
        ISituationContext wrongItemContext = mock(ISituationContext.class);

        IBodySlotType mockBsType = mock(IBodySlotType.class);
        Slotable mockBs = mock(Slotable.class);

        when(wrongItemCharacter.getSituationContext()).thenReturn(wrongItemContext);
        when(wrongItemContext.getBodySlotType()).thenReturn(mockBsType);

        when(wrongItemCharacter.getBodySlotByType(mockBsType)).thenReturn(mockBs);
        when(mockBs.getItem()).thenReturn(mock(IInventoryItem.class));

        Boolean isCorrect = this.correctWeaponEval.evaluate(wrongItemCharacter);

        assertFalse(isCorrect);
    }

}
