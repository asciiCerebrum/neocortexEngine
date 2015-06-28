package org.asciicerebrum.neocortexengine.mechanics.conditionevaluators.impl;

import org.asciicerebrum.neocortexengine.domain.core.ICharacter;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusValue;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.game.Weapon;
import org.asciicerebrum.neocortexengine.domain.mechanics.bonus.DynamicValueProvider;
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

    private BonusValueComparisonEvaluator evaluator;

    private DynamicValueProvider bonusValueProvider;

    private BonusValue referenceValue;

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
        this.evaluator = new BonusValueComparisonEvaluator();
        this.bonusValueProvider = mock(DynamicValueProvider.class);
        this.referenceValue = new BonusValue(10L);

        this.evaluator.setBonusValueProvider(this.bonusValueProvider);
        this.evaluator.setComparator(
                BonusValueComparisonEvaluator.ArithmeticComparator.LT);
        this.evaluator.setReferenceValue(this.referenceValue);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void evaluateNullReferenceTest() {
        final ICharacter dndCharacter = new DndCharacter();
        final UniqueEntity contextItem = new Weapon();

        this.evaluator.setReferenceValue(null);
        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);

        assertFalse(result);
    }

    @Test
    public void evaluateFalseLtTest() {
        final ICharacter dndCharacter = new DndCharacter();
        final UniqueEntity contextItem = new Weapon();

        when(this.bonusValueProvider.getDynamicValue(
                dndCharacter, contextItem)).thenReturn(new BonusValue(20L));

        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);

        assertFalse(result);
    }

    @Test
    public void evaluateTrueLtTest() {
        final ICharacter dndCharacter = new DndCharacter();
        final UniqueEntity contextItem = new Weapon();

        when(this.bonusValueProvider.getDynamicValue(
                dndCharacter, contextItem)).thenReturn(new BonusValue(5L));

        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);

        assertTrue(result);
    }

    @Test
    public void leCompareTrueTest() {

        final boolean result
                = BonusValueComparisonEvaluator.ArithmeticComparator.LE
                .compare(Double.valueOf(10D), Double.valueOf(10D));
        assertTrue(result);
    }

    @Test
    public void leCompareFalseTest() {

        final boolean result
                = BonusValueComparisonEvaluator.ArithmeticComparator.LE
                .compare(Double.valueOf(10D), Double.valueOf(5D));
        assertFalse(result);
    }

    @Test
    public void eqCompareTrueTest() {

        final boolean result
                = BonusValueComparisonEvaluator.ArithmeticComparator.EQ
                .compare(Double.valueOf(10D), Double.valueOf(10D));
        assertTrue(result);
    }

    @Test
    public void eqCompareFalseTest() {

        final boolean result
                = BonusValueComparisonEvaluator.ArithmeticComparator.EQ
                .compare(Double.valueOf(10D), Double.valueOf(5D));
        assertFalse(result);
    }

    @Test
    public void geCompareTrueTest() {

        final boolean result
                = BonusValueComparisonEvaluator.ArithmeticComparator.GE
                .compare(Double.valueOf(10D), Double.valueOf(10D));
        assertTrue(result);
    }

    @Test
    public void geCompareFalseTest() {

        final boolean result
                = BonusValueComparisonEvaluator.ArithmeticComparator.GE
                .compare(Double.valueOf(5D), Double.valueOf(10D));
        assertFalse(result);
    }

    @Test
    public void gtCompareTrueTest() {

        final boolean result
                = BonusValueComparisonEvaluator.ArithmeticComparator.GT
                .compare(Double.valueOf(100D), Double.valueOf(10D));
        assertTrue(result);
    }

    @Test
    public void gtCompareFalseTest() {

        final boolean result
                = BonusValueComparisonEvaluator.ArithmeticComparator.GT
                .compare(Double.valueOf(10D), Double.valueOf(50D));
        assertFalse(result);
    }
}
