package org.asciicerebrum.mydndgame.conditionevaluator;

import org.asciicerebrum.mydndgame.interfaces.entities.IBodySlotType;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.IEncumbrance;
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
public class CorrectWeaponEncumbranceEvaluatorTest {

    private CorrectWeaponEncumbranceEvaluator cweEvaluator;

    private ISituationContext sitCon;

    private IEncumbrance refEncumbrance;

    private Slotable bodySlotMock;

    private IWeapon weaponMock;

    public CorrectWeaponEncumbranceEvaluatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.cweEvaluator = new CorrectWeaponEncumbranceEvaluator();

        this.sitCon = mock(ISituationContext.class);

        ICharacter characterMock = mock(ICharacter.class);
        IBodySlotType bodySlotTypeMock = mock(IBodySlotType.class);
        this.bodySlotMock = mock(Slotable.class);
        this.weaponMock = mock(IWeapon.class);
        this.refEncumbrance = mock(IEncumbrance.class);

        when(this.sitCon.getBodySlotType()).thenReturn(bodySlotTypeMock);
        when(this.sitCon.getCharacter()).thenReturn(characterMock);
        when(characterMock.getBodySlotByType(bodySlotTypeMock))
                .thenReturn(this.bodySlotMock);
        when(this.bodySlotMock.getItem()).thenReturn(this.weaponMock);
        when(this.weaponMock.getEncumbrance()).thenReturn(this.refEncumbrance);

        this.cweEvaluator.setEncumbrance(this.refEncumbrance);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of evaluate method, of class CorrectWeaponEncumbranceEvaluator.
     */
    @Test
    public void testEvaluate() {
        Boolean resultEval = this.cweEvaluator.evaluate(this.sitCon);

        assertTrue(resultEval);
    }

    @Test
    public void testEvaluateNullRefEncumbrance() {
        this.cweEvaluator.setEncumbrance(null);
        Boolean resultEval = this.cweEvaluator.evaluate(this.sitCon);

        assertFalse(resultEval);
    }

    @Test
    public void testEvaluateDifferentRefEncumbrance() {
        this.cweEvaluator.setEncumbrance(mock(IEncumbrance.class));
        Boolean resultEval = this.cweEvaluator.evaluate(this.sitCon);

        assertFalse(resultEval);
    }

    @Test
    public void testEvaluateNoItemInSlot() {
        when(this.bodySlotMock.getItem()).thenReturn(null);
        Boolean resultEval = this.cweEvaluator.evaluate(this.sitCon);

        assertFalse(resultEval);
    }

    @Test
    public void testEvaluateNotWeaponInSlot() {
        when(this.bodySlotMock.getItem())
                .thenReturn(mock(IInventoryItem.class));
        Boolean resultEval = this.cweEvaluator.evaluate(this.sitCon);

        assertFalse(resultEval);
    }

    @Test
    public void testEvaluateNoEncumbrance() {
        when(this.weaponMock.getEncumbrance()).thenReturn(null);

        Boolean resultEval = this.cweEvaluator.evaluate(this.sitCon);

        assertFalse(resultEval);
    }

}
