package org.asciicerebrum.mydndgame.conditionevaluator;

import org.asciicerebrum.mydndgame.interfaces.entities.ISituationContext;
import org.asciicerebrum.mydndgame.interfaces.entities.IWeaponCategory;
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
public class CorrectAttackModeEvaluatorTest {

    private CorrectAttackModeEvaluator camEvaluator;

    private IWeaponCategory weaponCategory;

    private ISituationContext sitCon;

    public CorrectAttackModeEvaluatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        this.camEvaluator = new CorrectAttackModeEvaluator();

        this.weaponCategory = mock(IWeaponCategory.class);
        this.sitCon = mock(ISituationContext.class);
        when(this.sitCon.getAttackMode()).thenReturn(this.weaponCategory);

        this.camEvaluator.setWeaponCategory(this.weaponCategory);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of evaluate method, of class CorrectAttackModeEvaluator.
     */
    @Test
    public void testEvaluate() {
        Boolean evalResult = this.camEvaluator.evaluate(this.sitCon);

        assertTrue(evalResult);
    }

    @Test
    public void testEvaluateNoReferenceAttackMode() {
        this.camEvaluator.setWeaponCategory(null);
        Boolean evalResult = this.camEvaluator.evaluate(this.sitCon);

        assertFalse(evalResult);
    }

    @Test
    public void testEvaluateNoSitConAttackMode() {
        when(this.sitCon.getAttackMode()).thenReturn(null);
        Boolean evalResult = this.camEvaluator.evaluate(this.sitCon);

        assertFalse(evalResult);
    }

}
