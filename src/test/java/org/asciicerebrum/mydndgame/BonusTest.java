package org.asciicerebrum.mydndgame;

import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.mydndgame.interfaces.entities.BonusTarget;
import org.asciicerebrum.mydndgame.interfaces.entities.IBonus;
import org.asciicerebrum.mydndgame.interfaces.entities.IBonusType;
import org.asciicerebrum.mydndgame.interfaces.entities.ISituationContext;
import org.asciicerebrum.mydndgame.interfaces.entities.BonusValueProvider;
import org.asciicerebrum.mydndgame.interfaces.entities.ConditionEvaluator;
import org.asciicerebrum.mydndgame.interfaces.entities.IBonus.ResemblanceFacet;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author species8472
 */
public class BonusTest {

    private IBonus referenceBonus;
    private IBonus testBonus;
    private ISituationContext context;
    private ConditionEvaluator evaluator;

    public BonusTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        this.referenceBonus = new Bonus();
        this.testBonus = new Bonus();
        this.context = mock(ISituationContext.class);

        IBonusType bType = new BonusType();
        BonusTarget bTarget = new DiceAction();
        BonusValueProvider dvProvider = mock(BonusValueProvider.class);
        this.evaluator = mock(ConditionEvaluator.class);

        when(dvProvider.getDynamicValue(this.context)).thenReturn(42L);
        when(this.evaluator.evaluate(this.context)).thenReturn(Boolean.TRUE);

        this.referenceBonus.setBonusType(bType);
        this.referenceBonus.setTarget(bTarget);
        this.referenceBonus.setDynamicValueProvider(dvProvider);
        this.referenceBonus.setConditionEvaluator(this.evaluator);

        this.testBonus.setBonusType(bType);
        this.testBonus.setTarget(bTarget);
        this.testBonus.setDynamicValueProvider(dvProvider);

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of resembles method, of class Bonus.
     */
    @Test
    public void testResemblesTrue() {
        Boolean resembles = this.referenceBonus.resembles(this.testBonus);

        assertEquals(Boolean.TRUE, resembles);
    }

    @Test
    public void testResemblesFalseType() {
        this.testBonus.setBonusType(new BonusType());
        Boolean resembles = this.referenceBonus.resembles(this.testBonus);

        assertEquals(Boolean.FALSE, resembles);
    }

    @Test
    public void testResemblesFalseTarget() {
        this.testBonus.setTarget(new DiceAction());
        Boolean resembles = this.referenceBonus.resembles(this.testBonus);

        assertEquals(Boolean.FALSE, resembles);
    }

    @Test
    public void testResemblesFalseValueProviderNull() {
        this.testBonus.setDynamicValueProvider(null);
        Boolean resembles = this.referenceBonus.resembles(this.testBonus);

        assertEquals(Boolean.FALSE, resembles);
    }

    @Test
    public void testResemblesFalseValueProviderNullOther() {
        this.referenceBonus.setDynamicValueProvider(null);
        Boolean resembles = this.referenceBonus.resembles(this.testBonus);

        assertEquals(Boolean.FALSE, resembles);
    }

    @Test
    public void testResemblesFalseValueProviderNullBoth() {
        this.testBonus.setDynamicValueProvider(null);
        this.referenceBonus.setDynamicValueProvider(null);
        Boolean resembles = this.referenceBonus.resembles(this.testBonus);

        assertEquals(Boolean.TRUE, resembles);
    }

    @Test
    public void testResemblesFalseValueProviderDifferent() {
        this.testBonus.setDynamicValueProvider(mock(BonusValueProvider.class));
        Boolean resembles = this.referenceBonus.resembles(this.testBonus);

        assertEquals(Boolean.FALSE, resembles);
    }

    @Test
    public void testResemblesFalseValue() {
        this.testBonus.setValue(1L);
        Boolean resembles = this.referenceBonus.resembles(this.testBonus);

        assertEquals(Boolean.FALSE, resembles);
    }

    @Test
    public void testResemblesFalseRank() {
        this.testBonus.setRank(1L);
        Boolean resembles = this.referenceBonus.resembles(this.testBonus);

        assertEquals(Boolean.FALSE, resembles);
    }

    /**
     * Test of makeCopy method, of class Bonus.
     */
    @Test
    public void testMakeCopy() {

        IBonus cloneBonus = this.referenceBonus.makeCopy();

        assertEquals(Boolean.TRUE, this.referenceBonus.resembles(cloneBonus));
    }

    /**
     * Test of getEffectiveValue method, of class Bonus.
     */
    @Test
    public void testGetEffectiveValueByValue() {
        Long bonVal = 1L;
        this.referenceBonus.setValue(bonVal);

        assertEquals(bonVal, this.referenceBonus
                .getEffectiveValue(this.context));
    }

    @Test
    public void testGetEffectiveValueByValueProvider() {

        assertEquals(Long.valueOf(42), this.referenceBonus
                .getEffectiveValue(this.context));
    }

    @Test
    public void testGetEffectiveValueNull() {

        this.referenceBonus.setDynamicValueProvider(null);

        assertNull(this.referenceBonus
                .getEffectiveValue(this.context));
    }

    @Test
    public void testGetEffectiveValueNullEvaluator() {
        this.referenceBonus.setConditionEvaluator(null);
        Long bonVal = 1L;
        this.referenceBonus.setValue(bonVal);

        assertEquals(bonVal, this.referenceBonus
                .getEffectiveValue(this.context));
    }

    @Test
    public void testGetEffectiveValueFalseEvaluator() {
        when(this.evaluator.evaluate(this.context)).thenReturn(Boolean.FALSE);

        Long bonVal = 1L;
        this.referenceBonus.setValue(bonVal);

        assertNull(this.referenceBonus.getEffectiveValue(this.context));
    }

    @Test
    public void testResemblesWithFacetsNull() {
        List<ResemblanceFacet> facets = null;

        Boolean resembles = this.referenceBonus.resembles(this.testBonus,
                facets);

        assertEquals(Boolean.TRUE, resembles);
    }

    @Test
    public void testResemblesWithFacetsEmpty() {
        List<ResemblanceFacet> facets = new ArrayList<ResemblanceFacet>();

        Boolean resembles = this.referenceBonus.resembles(this.testBonus,
                facets);

        assertEquals(Boolean.TRUE, resembles);
    }

    @Test
    public void testResemblesWithFacetsBonusType() {
        List<ResemblanceFacet> facets = new ArrayList<ResemblanceFacet>();
        facets.add(ResemblanceFacet.BONUS_TYPE);

        Boolean resembles = this.referenceBonus.resembles(this.testBonus,
                facets);

        assertEquals(Boolean.TRUE, resembles);
    }

    @Test
    public void testResemblesWithFacetsTarget() {
        List<ResemblanceFacet> facets = new ArrayList<ResemblanceFacet>();
        facets.add(ResemblanceFacet.TARGET);

        Boolean resembles = this.referenceBonus.resembles(this.testBonus,
                facets);

        assertEquals(Boolean.TRUE, resembles);
    }

}
