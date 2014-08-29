package org.asciicerebrum.mydndgame.conditionevaluator;

import org.asciicerebrum.mydndgame.interfaces.entities.ConditionEvaluator;
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
public class NotEvaluatorTest {

    private NotEvaluator notEvaluator;

    private ISituationContext sitCon;

    public NotEvaluatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.notEvaluator = new NotEvaluator();
        this.sitCon = mock(ISituationContext.class);

        ConditionEvaluator subEvaluator = mock(ConditionEvaluator.class);
        when(subEvaluator.evaluate(this.sitCon)).thenReturn(Boolean.TRUE);

        this.notEvaluator.setConditionEvaluator(subEvaluator);

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of evaluate method, of class NotEvaluator.
     */
    @Test
    public void testEvaluate() {
        Boolean evalResult = this.notEvaluator.evaluate(this.sitCon);

        assertFalse(evalResult);
    }

    @Test
    public void testEvaluateOpposite() {
        ConditionEvaluator subEvaluator = mock(ConditionEvaluator.class);
        when(subEvaluator.evaluate(this.sitCon)).thenReturn(Boolean.FALSE);

        this.notEvaluator.setConditionEvaluator(subEvaluator);

        Boolean evalResult = this.notEvaluator.evaluate(this.sitCon);

        assertTrue(evalResult);
    }

}
