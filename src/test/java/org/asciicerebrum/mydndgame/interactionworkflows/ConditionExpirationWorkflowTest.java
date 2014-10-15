package org.asciicerebrum.mydndgame.interactionworkflows;

import java.util.Arrays;
import java.util.HashSet;
import javax.naming.OperationNotSupportedException;
import org.asciicerebrum.mydndgame.WorldDate;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.ICombatRound;
import org.asciicerebrum.mydndgame.interfaces.entities.IInteraction;
import org.asciicerebrum.mydndgame.interfaces.entities.IInteractionResponse;
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
public class ConditionExpirationWorkflowTest {

    private ConditionExpirationWorkflow condExWorkflow;

    private IInteractionResponse response;

    private IInteraction interaction;

    private ICombatRound combatRound;

    private ICharacter participant;

    private WorldDate worldDate;

    public ConditionExpirationWorkflowTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.condExWorkflow = new ConditionExpirationWorkflow();
        this.response = mock(IInteractionResponse.class);
        this.combatRound = mock(ICombatRound.class);
        this.interaction = mock(IInteraction.class);
        this.participant = mock(ICharacter.class);
        this.worldDate = new WorldDate(0L, "");

        when(this.response.getValue(
                InteractionResponseKey.COMBAT_ROUND, ICombatRound.class))
                .thenReturn(this.combatRound);
        when(this.combatRound.getParticipants()).thenReturn(
                new HashSet<ICharacter>(Arrays.asList(
                                this.participant, this.participant)));
        when(this.combatRound.getCurrentDate()).thenReturn(this.worldDate);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of runWorkflow method, of class ConditionExpirationWorkflow.
     */
    @Test
    public void testRunWorkflow_IInteraction_IInteractionResponseReturn() {
        IInteractionResponse returnResponse
                = this.condExWorkflow.runWorkflow(
                        this.interaction, this.response);

        assertEquals(this.response, returnResponse);
    }

    @Test
    public void testRunWorkflow_IInteraction_IInteractionResponseCallParticipant() {
        this.condExWorkflow.runWorkflow(
                this.interaction, this.response);

        // it is called only once because it is a set!
        verify(this.participant, times(1)).removeConditionsByExpiryDate(
                this.worldDate);
    }

    /**
     * Test of runWorkflow method, of class ConditionExpirationWorkflow.
     */
    @Test(expected = OperationNotSupportedException.class)
    public void testRunWorkflow_IInteraction()
            throws OperationNotSupportedException {
        this.condExWorkflow.runWorkflow(this.interaction);
    }

}
