package org.asciicerebrum.mydndgame.conditionevaluator;

import org.asciicerebrum.mydndgame.Proficiency;
import org.asciicerebrum.mydndgame.interfaces.entities.IBodySlotType;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.IInventoryItem;
import org.asciicerebrum.mydndgame.interfaces.entities.IProficiency;
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
public class CorrectProficiencyEvaluatorTest {

    private CorrectProficiencyEvaluator cpEval;

    private ISituationContext mockSitCon;

    private Slotable mockBodySlot;

    public CorrectProficiencyEvaluatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        this.cpEval = new CorrectProficiencyEvaluator();

        this.mockSitCon = mock(ISituationContext.class);

        ICharacter mockCharacter = mock(ICharacter.class);
        IBodySlotType bsType = mock(IBodySlotType.class);
        this.mockBodySlot = mock(Slotable.class);
        IWeapon mockWeapon = mock(IWeapon.class);

        when(this.mockSitCon.getCharacter()).thenReturn(mockCharacter);
        when(this.mockSitCon.getBodySlotType()).thenReturn(bsType);

        when(mockCharacter.getBodySlotByType(bsType))
                .thenReturn(this.mockBodySlot);

        when(this.mockBodySlot.getItem()).thenReturn(mockWeapon);

        IProficiency someProficiency = new Proficiency();

        when(mockWeapon.getProficiency()).thenReturn(someProficiency);

        this.cpEval.setProficiency(someProficiency);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of evaluate method, of class CorrectProficiencyEvaluator.
     */
    @Test
    public void testEvaluate() {
        Boolean isCorrect = this.cpEval.evaluate(this.mockSitCon);

        assertTrue(isCorrect);
    }

    /**
     * Test of evaluate method, of class CorrectProficiencyEvaluator. Null
     * proficiency.
     */
    @Test
    public void testEvaluateNullProficiency() {
        this.cpEval.setProficiency(null);
        Boolean isCorrect = this.cpEval.evaluate(this.mockSitCon);

        assertFalse(isCorrect);
    }

    /**
     * Test of evaluate method, of class CorrectProficiencyEvaluator. No item in
     * slot.
     */
    @Test
    public void testEvaluateNoItem() {
        when(this.mockBodySlot.getItem()).thenReturn(null);

        Boolean isCorrect = this.cpEval.evaluate(this.mockSitCon);

        assertFalse(isCorrect);
    }

    /**
     * Test of evaluate method, of class CorrectProficiencyEvaluator. Item in
     * slot is not a weapon.
     */
    @Test
    public void testEvaluateNoWeapon() {
        when(this.mockBodySlot.getItem())
                .thenReturn(mock(IInventoryItem.class));

        Boolean isCorrect = this.cpEval.evaluate(this.mockSitCon);

        assertFalse(isCorrect);
    }

}
