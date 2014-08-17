package org.asciicerebrum.mydndgame.conditionevaluator;

import org.asciicerebrum.mydndgame.interfaces.entities.IBodySlotType;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.IInventoryItem;
import org.asciicerebrum.mydndgame.interfaces.entities.ISituationContext;
import org.asciicerebrum.mydndgame.interfaces.entities.IWeapon;
import org.asciicerebrum.mydndgame.interfaces.entities.IWeaponCategory;
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
public class CorrectWeaponAttackModeEvaluatorTest {

    private CorrectWeaponAttackModeEvaluator cwamEval;

    private ISituationContext mockSitCon;

    private Slotable bodySlot;

    public CorrectWeaponAttackModeEvaluatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        this.cwamEval = new CorrectWeaponAttackModeEvaluator();

        this.mockSitCon = mock(ISituationContext.class);

        IWeaponCategory attackMode = mock(IWeaponCategory.class);
        ICharacter mockCharacter = mock(ICharacter.class);
        IBodySlotType bsType = mock(IBodySlotType.class);
        this.bodySlot = mock(Slotable.class);
        IWeapon mockWeapon = mock(IWeapon.class);

        when(this.mockSitCon.getAttackMode()).thenReturn(attackMode);
        when(this.mockSitCon.getCharacter()).thenReturn(mockCharacter);
        when(this.mockSitCon.getBodySlotType()).thenReturn(bsType);

        when(mockCharacter.getBodySlotByType(bsType)).thenReturn(this.bodySlot);

        when(this.bodySlot.getItem()).thenReturn(mockWeapon);

        when(mockWeapon.isAttackModeCompatible(attackMode))
                .thenReturn(Boolean.TRUE);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of evaluate method, of class CorrectWeaponAttackModeEvaluator.
     */
    @Test
    public void testEvaluate() {
        Boolean isCorrect = this.cwamEval.evaluate(this.mockSitCon);

        assertTrue(isCorrect);
    }

    /**
     * Test of evaluate method, of class CorrectWeaponAttackModeEvaluator.
     * Attack mode is null.
     */
    @Test
    public void testEvaluateNullAttackMode() {
        when(this.mockSitCon.getAttackMode()).thenReturn(null);

        Boolean isCorrect = this.cwamEval.evaluate(this.mockSitCon);

        assertFalse(isCorrect);
    }

    /**
     * Test of evaluate method, of class CorrectWeaponAttackModeEvaluator. No
     * item in body slot.
     */
    @Test
    public void testEvaluateNoItem() {
        when(this.bodySlot.getItem()).thenReturn(null);

        Boolean isCorrect = this.cwamEval.evaluate(this.mockSitCon);

        assertFalse(isCorrect);
    }

    /**
     * Test of evaluate method, of class CorrectWeaponAttackModeEvaluator. Item
     * is not a weapon.
     */
    @Test
    public void testEvaluateNoWeapon() {
        when(this.bodySlot.getItem()).thenReturn(mock(IInventoryItem.class));

        Boolean isCorrect = this.cwamEval.evaluate(this.mockSitCon);

        assertFalse(isCorrect);
    }

}
