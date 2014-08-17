package org.asciicerebrum.mydndgame.conditionevaluator;

import java.util.ArrayList;
import java.util.List;
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
public class AndListEvaluatorTest {

    private AndListEvaluator andEval;

    private ISituationContext mockSitCon;

    private ConditionEvaluator subEval;

    public AndListEvaluatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.andEval = new AndListEvaluator();
        this.mockSitCon = mock(ISituationContext.class);

        this.subEval = mock(ConditionEvaluator.class);

        when(this.subEval.evaluate(this.mockSitCon)).thenReturn(Boolean.TRUE);

        List<ConditionEvaluator> condEvalList
                = new ArrayList<ConditionEvaluator>();
        condEvalList.add(this.subEval);

        this.andEval.setConditionEvaluators(condEvalList);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of evaluate method, of class AndListEvaluator.
     */
    @Test
    public void testEvaluate() {
        Boolean isCorrect = this.andEval.evaluate(this.mockSitCon);

        assertTrue(isCorrect);
    }

    @Test
    public void testEvaluateFalse() {
        when(this.subEval.evaluate(this.mockSitCon)).thenReturn(Boolean.FALSE);

        Boolean isCorrect = this.andEval.evaluate(this.mockSitCon);

        assertFalse(isCorrect);
    }

}
