package org.asciicerebrum.neocortexengine.mechanics.conditionevaluators.impl;

import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.neocortexengine.domain.core.ICharacter;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.game.Weapon;
import org.asciicerebrum.neocortexengine.mechanics.conditionevaluators.ConditionEvaluator;
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

    private AndListEvaluator evaluator;

    private ConditionEvaluator evalA;

    private ConditionEvaluator evalB;

    private List<ConditionEvaluator> evals;

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
        this.evaluator = new AndListEvaluator();
        this.evals = new ArrayList<ConditionEvaluator>();
        this.evalA = mock(ConditionEvaluator.class);
        this.evalB = mock(ConditionEvaluator.class);
        this.evals.add(this.evalA);
        this.evals.add(this.evalB);

        this.evaluator.setConditionEvaluators(this.evals);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void evaluateOneFalseTest() {
        final ICharacter dndCharacter = new DndCharacter();
        final UniqueEntity contextItem = new Weapon();

        when(this.evalA.evaluate(dndCharacter, contextItem))
                .thenReturn(Boolean.FALSE);
        when(this.evalB.evaluate(dndCharacter, contextItem))
                .thenReturn(Boolean.TRUE);

        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);
        assertFalse(result);
    }

    @Test
    public void evaluateBothTrueTest() {
        final ICharacter dndCharacter = new DndCharacter();
        final UniqueEntity contextItem = new Weapon();

        when(this.evalA.evaluate(dndCharacter, contextItem))
                .thenReturn(Boolean.TRUE);
        when(this.evalB.evaluate(dndCharacter, contextItem))
                .thenReturn(Boolean.TRUE);

        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);
        assertTrue(result);
    }

}
