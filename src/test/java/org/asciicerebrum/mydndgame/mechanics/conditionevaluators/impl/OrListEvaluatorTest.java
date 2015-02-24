package org.asciicerebrum.mydndgame.mechanics.conditionevaluators.impl;

import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.mydndgame.domain.core.ICharacter;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.mechanics.conditionevaluators.ConditionEvaluator;
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
public class OrListEvaluatorTest {

    private OrListEvaluator evaluator;

    private ConditionEvaluator evalA;

    private ConditionEvaluator evalB;

    private List<ConditionEvaluator> evals;

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
        this.evaluator = new OrListEvaluator();
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
    public void evaluateOneTrueTest() {
        final ICharacter dndCharacter = new DndCharacter();
        final UniqueEntity contextItem = new Weapon();

        when(this.evalA.evaluate(dndCharacter, contextItem))
                .thenReturn(Boolean.FALSE);
        when(this.evalB.evaluate(dndCharacter, contextItem))
                .thenReturn(Boolean.TRUE);

        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);
        assertTrue(result);
    }

    @Test
    public void evaluateBothFalseTest() {
        final ICharacter dndCharacter = new DndCharacter();
        final UniqueEntity contextItem = new Weapon();

        when(this.evalA.evaluate(dndCharacter, contextItem))
                .thenReturn(Boolean.FALSE);
        when(this.evalB.evaluate(dndCharacter, contextItem))
                .thenReturn(Boolean.FALSE);

        final boolean result = this.evaluator.evaluate(
                dndCharacter, contextItem);
        assertFalse(result);
    }

}
