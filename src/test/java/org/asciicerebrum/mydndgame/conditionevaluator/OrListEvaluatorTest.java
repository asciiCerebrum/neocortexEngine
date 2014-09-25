package org.asciicerebrum.mydndgame.conditionevaluator;

import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.mydndgame.interfaces.entities.ConditionEvaluator;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author species8472
 */
public class OrListEvaluatorTest {

    private OrListEvaluator orListEvaluator;

    private List<ConditionEvaluator> subEvaluators;

    private ConditionEvaluator condEval1;

    private ConditionEvaluator condEval2;

    private ICharacter character;

    public OrListEvaluatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.orListEvaluator = new OrListEvaluator();

        this.character = mock(ICharacter.class);

        this.condEval1 = mock(ConditionEvaluator.class);
        this.condEval2 = mock(ConditionEvaluator.class);

        when(this.condEval1.evaluate(this.character)).thenReturn(Boolean.TRUE);

        this.subEvaluators = new ArrayList<ConditionEvaluator>();
        this.subEvaluators.add(this.condEval1);
        this.subEvaluators.add(this.condEval2);

        this.orListEvaluator.setConditionEvaluators(this.subEvaluators);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of evaluate method, of class OrListEvaluator.
     */
    @Test
    public void testEvaluate() {
        Boolean evalResult = this.orListEvaluator.evaluate(this.character);

        assertTrue(evalResult);
    }

    @Test
    public void testEvaluateUncheckedSecondEvaluator() {
        this.orListEvaluator.evaluate(this.character);

        verify(this.condEval2, never()).evaluate(this.character);
    }

    @Test
    public void testEvaluateFirstEvaluatorFalseResult() {
        when(this.condEval1.evaluate(this.character)).thenReturn(Boolean.FALSE);
        when(this.condEval2.evaluate(this.character)).thenReturn(Boolean.TRUE);

        Boolean evalResult = this.orListEvaluator.evaluate(this.character);

        assertTrue(evalResult);
    }

    @Test
    public void testEvaluateFirstEvaluatorFalse() {
        when(this.condEval1.evaluate(this.character)).thenReturn(Boolean.FALSE);
        when(this.condEval2.evaluate(this.character)).thenReturn(Boolean.TRUE);

        this.orListEvaluator.evaluate(this.character);

        verify(this.condEval2, times(1)).evaluate(this.character);
    }

    @Test
    public void testEvaluateEmptyList() {
        this.subEvaluators.clear();

        Boolean evalResult = this.orListEvaluator.evaluate(this.character);

        assertFalse(evalResult);
    }

    @Test
    public void testEvaluateBothFalse() {
        when(this.condEval1.evaluate(this.character)).thenReturn(Boolean.FALSE);
        when(this.condEval2.evaluate(this.character)).thenReturn(Boolean.FALSE);

        Boolean evalResult = this.orListEvaluator.evaluate(this.character);

        assertFalse(evalResult);
    }
}
