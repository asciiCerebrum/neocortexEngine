package org.asciicerebrum.mydndgame.mechanics.interactionworkflows;

import org.asciicerebrum.mydndgame.domain.game.CombatRound;
import org.asciicerebrum.mydndgame.domain.game.DndCharacters;
import org.asciicerebrum.mydndgame.domain.mechanics.workflow.Interaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 *
 * @author species8472
 */
public class InitializeCombatRoundWorkflowTest {

    private InitializeCombatRoundWorkflow wf;
    private Interaction interaction;

    public InitializeCombatRoundWorkflowTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.wf = spy(InitializeCombatRoundWorkflow.class);
        this.interaction = mock(Interaction.class);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testRunWorkflow() {
        doNothing().when(this.wf).rollInitiative((DndCharacters) anyObject(),
                (CombatRound) anyObject());
        doNothing().when(this.wf).resolveTies((CombatRound) anyObject());
        doNothing().when(this.wf).applyFlatFooted((CombatRound) anyObject());

        this.wf.runWorkflow(this.interaction);
        verify(this.wf, times(1)).rollInitiative((DndCharacters) anyObject(),
                (CombatRound) anyObject());
    }

}
