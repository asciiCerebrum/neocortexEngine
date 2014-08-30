package org.asciicerebrum.mydndgame.conditionevaluator;

import org.asciicerebrum.mydndgame.conditionevaluator.BonusValueComparisonEvaluator.ArithmeticComparator;
import org.asciicerebrum.mydndgame.interfaces.entities.BonusValueProvider;
import org.asciicerebrum.mydndgame.interfaces.entities.ISituationContext;
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
public class BonusValueComparisonEvaluatorTest {

    private BonusValueComparisonEvaluator abcEvaluator;

    private ISituationContext sitCon;

    private BonusValueProvider bvProvider;

    public BonusValueComparisonEvaluatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.abcEvaluator = new BonusValueComparisonEvaluator();

        this.abcEvaluator.setReferenceValue(42.0D);

        this.sitCon = mock(ISituationContext.class);

        this.bvProvider = mock(BonusValueProvider.class);
        when(this.bvProvider.getDynamicValue(this.sitCon)).thenReturn(37L);

        this.abcEvaluator.setBonusValueProvider(this.bvProvider);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of evaluate method, of class AbilityBonusComparisonEvaluator.
     */
    @Test
    public void testEvaluateLtTrue() {
        ArithmeticComparator lt
                = BonusValueComparisonEvaluator.ArithmeticComparator.LT;

        this.abcEvaluator.setComparator(lt);
        Boolean evalResult = this.abcEvaluator.evaluate(this.sitCon);

        assertTrue(evalResult);
    }

    @Test
    public void testEvaluateLtFalse() {
        when(this.bvProvider.getDynamicValue(this.sitCon)).thenReturn(45L);

        ArithmeticComparator lt
                = BonusValueComparisonEvaluator.ArithmeticComparator.LT;

        this.abcEvaluator.setComparator(lt);
        Boolean evalResult = this.abcEvaluator.evaluate(this.sitCon);

        assertFalse(evalResult);
    }

    @Test
    public void testEvaluateLeTrue() {
        when(this.bvProvider.getDynamicValue(this.sitCon)).thenReturn(42L);

        ArithmeticComparator le
                = BonusValueComparisonEvaluator.ArithmeticComparator.LE;

        this.abcEvaluator.setComparator(le);
        Boolean evalResult = this.abcEvaluator.evaluate(this.sitCon);

        assertTrue(evalResult);
    }

    @Test
    public void testEvaluateLeFalse() {
        when(this.bvProvider.getDynamicValue(this.sitCon)).thenReturn(45L);

        ArithmeticComparator le
                = BonusValueComparisonEvaluator.ArithmeticComparator.LE;

        this.abcEvaluator.setComparator(le);
        Boolean evalResult = this.abcEvaluator.evaluate(this.sitCon);

        assertFalse(evalResult);
    }

    @Test
    public void testEvaluateEqTrue() {
        when(this.bvProvider.getDynamicValue(this.sitCon)).thenReturn(42L);

        ArithmeticComparator eq
                = BonusValueComparisonEvaluator.ArithmeticComparator.EQ;

        this.abcEvaluator.setComparator(eq);
        Boolean evalResult = this.abcEvaluator.evaluate(this.sitCon);

        assertTrue(evalResult);
    }

    @Test
    public void testEvaluateEqFalse() {
        when(this.bvProvider.getDynamicValue(this.sitCon)).thenReturn(45L);

        ArithmeticComparator eq
                = BonusValueComparisonEvaluator.ArithmeticComparator.EQ;

        this.abcEvaluator.setComparator(eq);
        Boolean evalResult = this.abcEvaluator.evaluate(this.sitCon);

        assertFalse(evalResult);
    }

    @Test
    public void testEvaluateGeTrue() {
        when(this.bvProvider.getDynamicValue(this.sitCon)).thenReturn(45L);

        ArithmeticComparator ge
                = BonusValueComparisonEvaluator.ArithmeticComparator.GE;

        this.abcEvaluator.setComparator(ge);
        Boolean evalResult = this.abcEvaluator.evaluate(this.sitCon);

        assertTrue(evalResult);
    }

    @Test
    public void testEvaluateGeFalse() {
        when(this.bvProvider.getDynamicValue(this.sitCon)).thenReturn(37L);

        ArithmeticComparator ge
                = BonusValueComparisonEvaluator.ArithmeticComparator.GE;

        this.abcEvaluator.setComparator(ge);
        Boolean evalResult = this.abcEvaluator.evaluate(this.sitCon);

        assertFalse(evalResult);
    }

    @Test
    public void testEvaluateGtTrue() {
        when(this.bvProvider.getDynamicValue(this.sitCon)).thenReturn(45L);

        ArithmeticComparator gt
                = BonusValueComparisonEvaluator.ArithmeticComparator.GT;

        this.abcEvaluator.setComparator(gt);
        Boolean evalResult = this.abcEvaluator.evaluate(this.sitCon);

        assertTrue(evalResult);
    }

    @Test
    public void testEvaluateGtFalse() {
        when(this.bvProvider.getDynamicValue(this.sitCon)).thenReturn(37L);

        ArithmeticComparator gt
                = BonusValueComparisonEvaluator.ArithmeticComparator.GT;

        this.abcEvaluator.setComparator(gt);
        Boolean evalResult = this.abcEvaluator.evaluate(this.sitCon);

        assertFalse(evalResult);
    }

}
