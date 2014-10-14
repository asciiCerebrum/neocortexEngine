package org.asciicerebrum.mydndgame.managers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.naming.OperationNotSupportedException;
import org.asciicerebrum.mydndgame.CombatRound;
import org.asciicerebrum.mydndgame.Interaction;
import org.asciicerebrum.mydndgame.InteractionType;
import org.asciicerebrum.mydndgame.exceptions.CombatRoundInitializationException;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.ICombatRound;
import org.asciicerebrum.mydndgame.interfaces.entities.IInteraction;
import org.asciicerebrum.mydndgame.interfaces.entities.IInteractionResponse;
import org.asciicerebrum.mydndgame.interfaces.entities.IInteractionType;
import org.asciicerebrum.mydndgame.interfaces.entities.IWorkflow;
import org.asciicerebrum.mydndgame.interfaces.entities.InteractionResponseKey;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author species8472
 */
public class CombatRoundManagerTest {

    private CombatRoundManager crManager;

    private List<ICharacter> participants;

    private IWorkflow initializeWorkflow;

    private ICombatRound combatRound;

    IInteractionResponse response;

    public CombatRoundManagerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws OperationNotSupportedException {
        this.crManager = new CombatRoundManager();
        this.participants = new ArrayList<ICharacter>();
        this.initializeWorkflow = mock(IWorkflow.class);

        this.response = mock(IInteractionResponse.class);
        this.combatRound = mock(ICombatRound.class);
        when(this.initializeWorkflow.runWorkflow((IInteraction) anyObject()))
                .thenReturn(this.response);
        when(this.response.getValue(InteractionResponseKey.COMBAT_ROUND,
                ICombatRound.class)).thenReturn(this.combatRound);

        this.crManager.setInitializeCombatRoundWorkflow(this.initializeWorkflow);

        ICharacter char1 = mock(ICharacter.class);
        ICharacter char2 = mock(ICharacter.class);
        ICharacter char3 = mock(ICharacter.class);

        this.participants.add(char1);
        this.participants.add(char2);
        this.participants.add(char3);

        when(this.combatRound.getCurrentParticipant()).thenReturn(char1);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of initiateCombatRound method, of class CombatRoundManager.
     */
    @Test
    public void testInitiateCombatRound() throws OperationNotSupportedException {
        Boolean isInit = this.crManager.initiateCombatRound(this.participants);
        assertTrue(isInit);
    }

    @Test
    public void testInitiateCombatRoundInitCall() throws OperationNotSupportedException {
        this.crManager.initiateCombatRound(this.participants);
        verify(this.initializeWorkflow, times(1)).runWorkflow(
                (IInteraction) anyObject());
    }

    @Test
    public void testInitiateCombatRoundWithOldCombatRound() throws OperationNotSupportedException {
        this.crManager.initiateCombatRound(this.participants);
        Boolean isInit = this.crManager.initiateCombatRound(this.participants);
        assertFalse(isInit);
    }

    @Test(expected = CombatRoundInitializationException.class)
    public void testInitiateCombatRoundFailure() throws OperationNotSupportedException {
        when(this.response.getValue(InteractionResponseKey.COMBAT_ROUND,
                ICombatRound.class)).thenReturn(null);
        this.crManager.initiateCombatRound(this.participants);
    }

    /**
     * Test of resumeCombatRound method, of class CombatRoundManager.
     */
    @Test
    public void testResumeCombatRound() {
        Boolean isResume = this.crManager.resumeCombatRound(new CombatRound());
        assertTrue(isResume);
    }

    @Test
    public void testResumeCombatRoundWithOldCombatRound() throws OperationNotSupportedException {
        this.crManager.initiateCombatRound(this.participants);
        Boolean isResume = this.crManager.resumeCombatRound(new CombatRound());
        assertFalse(isResume);
    }

    /**
     * Test of isCurrentParticipant method, of class CombatRoundManager.
     */
    @Test
    public void testIsCurrentParticipant() throws OperationNotSupportedException {
        this.crManager.initiateCombatRound(this.participants);
        Boolean isCurrent = this.crManager.isCurrentParticipant(
                this.participants.get(0));
        assertTrue(isCurrent);
    }

    @Test
    public void testIsCurrentParticipantNotCurrent() throws OperationNotSupportedException {
        this.crManager.initiateCombatRound(this.participants);
        Boolean isCurrent = this.crManager.isCurrentParticipant(
                this.participants.get(1));
        assertFalse(isCurrent);
    }

    @Test
    public void testIsCurrentParticipantNoCombat() {
        Boolean isCurrent = this.crManager.isCurrentParticipant(
                this.participants.get(0));
        assertFalse(isCurrent);
    }

    /**
     * Test of executeInteraction method, of class CombatRoundManager.
     */
    @Test
    public void testExecuteInteraction() throws OperationNotSupportedException {
        IWorkflow workflow1 = mock(IWorkflow.class);
        IWorkflow workflow2 = mock(IWorkflow.class);
        IInteractionType interType = new InteractionType();
        interType.setWorkflows(Arrays.asList(workflow1, workflow2));
        IInteraction interaction = new Interaction();
        interaction.setTriggeringCharacter(this.participants.get(0));
        interaction.setInteractionType(interType);

        this.crManager.initiateCombatRound(this.participants);
        this.crManager.executeInteraction(interaction);
        verify(workflow1, times(1)).runWorkflow(eq(interaction),
                (IInteractionResponse) anyObject());
    }

    @Test
    public void testExecuteInteractionWorkflow2() throws OperationNotSupportedException {
        IWorkflow workflow1 = mock(IWorkflow.class);
        IWorkflow workflow2 = mock(IWorkflow.class);
        IInteractionType interType = new InteractionType();
        interType.setWorkflows(Arrays.asList(workflow1, workflow2));
        IInteraction interaction = new Interaction();
        interaction.setTriggeringCharacter(this.participants.get(0));
        interaction.setInteractionType(interType);

        this.crManager.initiateCombatRound(this.participants);
        this.crManager.executeInteraction(interaction);
        verify(workflow2, times(1)).runWorkflow(eq(interaction),
                (IInteractionResponse) anyObject());
    }

    @Test
    public void testExecuteInteractionInvalidParticipant() throws OperationNotSupportedException {
        IWorkflow workflow1 = mock(IWorkflow.class);
        IWorkflow workflow2 = mock(IWorkflow.class);
        IInteractionType interType = new InteractionType();
        interType.setWorkflows(Arrays.asList(workflow1, workflow2));
        IInteraction interaction = new Interaction();
        interaction.setTriggeringCharacter(this.participants.get(1));
        interaction.setInteractionType(interType);

        this.crManager.initiateCombatRound(this.participants);
        this.crManager.executeInteraction(interaction);
        verify(workflow1, times(0)).runWorkflow(eq(interaction),
                (IInteractionResponse) anyObject());
    }

}
