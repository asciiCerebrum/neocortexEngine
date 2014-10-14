package org.asciicerebrum.mydndgame.interactionworkflows;

import javax.naming.OperationNotSupportedException;
import org.asciicerebrum.mydndgame.interfaces.entities.ICombatRound;
import org.asciicerebrum.mydndgame.interfaces.entities.IInteraction;
import org.asciicerebrum.mydndgame.interfaces.entities.IInteractionResponse;
import org.asciicerebrum.mydndgame.interfaces.entities.IWorkflow;
import org.asciicerebrum.mydndgame.interfaces.entities.InteractionResponseKey;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author species8472
 */
public class EndTurnWorkflowTest {

    private EndTurnWorkflow etWorkflow;

    private IInteractionResponse response;

    private IInteraction interaction;

    private ICombatRound combatRound;

    public EndTurnWorkflowTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.etWorkflow = new EndTurnWorkflow();
        this.response = mock(IInteractionResponse.class);
        this.combatRound = mock(ICombatRound.class);
        this.interaction = mock(IInteraction.class);

        when(this.response.getValue(
                InteractionResponseKey.COMBAT_ROUND, ICombatRound.class))
                .thenReturn(this.combatRound);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of runWorkflow method, of class EndTurnWorkflow.
     */
    @Test(expected = OperationNotSupportedException.class)
    public void testRunWorkflow_IInteraction()
            throws OperationNotSupportedException {
        this.etWorkflow.runWorkflow(this.interaction);
    }

    /**
     * Test of runWorkflow method, of class EndTurnWorkflow.
     */
    @Test
    public void testRunWorkflow_IInteraction_IInteractionResponse() {
        this.etWorkflow.runWorkflow(this.interaction, this.response);
        verify(this.combatRound, times(1)).moveToNextPosition();
    }

    @Test
    public void testRunWorkflow_IInteraction_IInteractionResponseReturn() {
        IInteractionResponse returnRespons
                = this.etWorkflow.runWorkflow(this.interaction, this.response);
        assertEquals(this.response, returnRespons);
    }

    @Test
    public void testRunWorkflow_IInteraction_IInteractionResponseExpWorkflow() {
        IWorkflow expWorkflow = mock(IWorkflow.class);
        this.etWorkflow.setConditionExpirationWorkflow(expWorkflow);

        this.etWorkflow.runWorkflow(this.interaction, this.response);
        verify(expWorkflow, times(1)).runWorkflow(this.interaction,
                this.response);
    }

}
