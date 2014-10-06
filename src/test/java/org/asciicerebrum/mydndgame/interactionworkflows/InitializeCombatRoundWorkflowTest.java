package org.asciicerebrum.mydndgame.interactionworkflows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.asciicerebrum.mydndgame.CombatRound;
import org.asciicerebrum.mydndgame.Interaction;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.ICombatRound;
import org.asciicerebrum.mydndgame.interfaces.entities.IDiceAction;
import org.asciicerebrum.mydndgame.interfaces.entities.IInteraction;
import org.asciicerebrum.mydndgame.interfaces.entities.IInteractionResponse;
import org.asciicerebrum.mydndgame.interfaces.entities.InteractionResponseKey;
import org.asciicerebrum.mydndgame.interfaces.managers.IDiceRollManager;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author species8472
 */
public class InitializeCombatRoundWorkflowTest {

    private InitializeCombatRoundWorkflow icrWf;

    private IDiceRollManager drManager;

    private IDiceAction initAction;

    private IInteraction interaction;

    private IInteractionResponse response;

    private ICombatRound combatRound;

    private ICharacter char2;

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
        this.icrWf = new InitializeCombatRoundWorkflow();

        this.drManager = mock(IDiceRollManager.class);
        this.initAction = mock(IDiceAction.class);

        this.icrWf.setDiceRollManager(this.drManager);
        this.icrWf.setInitiativeAction(this.initAction);

        this.combatRound = mock(ICombatRound.class);
        this.interaction = new Interaction();
        this.response = mock(IInteractionResponse.class);

        when(this.response.getValue(InteractionResponseKey.COMBAT_ROUND,
                ICombatRound.class)).thenReturn(this.combatRound);

        ICharacter char1 = mock(ICharacter.class);
        this.char2 = mock(ICharacter.class);
        ICharacter char3 = mock(ICharacter.class);

        when(char1.getInitBonus()).thenReturn(8L);
        when(this.char2.getInitBonus()).thenReturn(12L);
        when(char3.getInitBonus()).thenReturn(2L);

        when(this.drManager.rollDice(this.initAction)).thenReturn(15L, 13L, 16L);

        List<ICharacter> chars = new ArrayList<ICharacter>();
        chars.add(char1);
        chars.add(this.char2);
        chars.add(char3);

        this.interaction.setTargetCharacters(chars);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of runWorkflow method, of class InitializeCombatRoundWorkflow.
     */
    @Test
    public void testRunWorkflowPostionChar2() {

        this.icrWf.runWorkflow(this.interaction, this.response);
        verify(this.combatRound).addParticipant(this.char2, "025012");
    }

    @Test
    public void testRunWorkflowParticipantCount() {

        this.icrWf.runWorkflow(this.interaction, this.response);

        verify(this.combatRound, times(3)).addParticipant(
                (ICharacter) anyObject(), anyString());
    }

    @Test
    public void testGetTieingParticipants() {
        ICharacter char1 = mock(ICharacter.class);
        ICharacter char3 = mock(ICharacter.class);

        when(this.combatRound.getOrderedPositions()).thenReturn(
                new TreeSet<String>(Arrays.asList("025013", "025012", "025012")));
        when(this.combatRound.getParticipantsForPosition("025013")).thenReturn(
                new HashSet<ICharacter>(Arrays.asList(char1)));
        when(this.combatRound.getParticipantsForPosition("025012")).thenReturn(
                new HashSet<ICharacter>(Arrays.asList(this.char2, char3)));

        Set<ICharacter> ties
                = this.icrWf.getTieingParticipants(this.combatRound);

        assertEquals(2, ties.size());
    }

    @Test
    public void testGetTieingParticipantsContent() {
        ICharacter char1 = mock(ICharacter.class);
        ICharacter char3 = mock(ICharacter.class);

        when(this.combatRound.getOrderedPositions()).thenReturn(
                new TreeSet<String>(Arrays.asList("025013", "025012", "025012")));
        when(this.combatRound.getParticipantsForPosition("025013")).thenReturn(
                new HashSet<ICharacter>(Arrays.asList(char1)));
        when(this.combatRound.getParticipantsForPosition("025012")).thenReturn(
                new HashSet<ICharacter>(Arrays.asList(this.char2, char3)));

        Set<ICharacter> ties
                = this.icrWf.getTieingParticipants(this.combatRound);

        assertTrue(ties.contains(this.char2));
    }

    @Test
    public void testGetTieingParticipantsFalseContent() {
        ICharacter char1 = mock(ICharacter.class);
        ICharacter char3 = mock(ICharacter.class);

        when(this.combatRound.getOrderedPositions()).thenReturn(
                new TreeSet<String>(Arrays.asList("025013", "025012", "025012")));
        when(this.combatRound.getParticipantsForPosition("025013")).thenReturn(
                new HashSet<ICharacter>(Arrays.asList(char1)));
        when(this.combatRound.getParticipantsForPosition("025012")).thenReturn(
                new HashSet<ICharacter>(Arrays.asList(this.char2, char3)));

        Set<ICharacter> ties
                = this.icrWf.getTieingParticipants(this.combatRound);

        assertFalse(ties.contains(char1));
    }

    @Test
    public void testResolveTies() {
        ICharacter char1 = mock(ICharacter.class);
        ICharacter char3 = mock(ICharacter.class);

        this.combatRound = new CombatRound();
        this.combatRound.addParticipant(char1, "025013");
        this.combatRound.addParticipant(this.char2, "025012");
        this.combatRound.addParticipant(char3, "025012");

        when(this.drManager.rollDice(this.initAction)).thenReturn(8L, 8L, 10L, 5L);

        this.icrWf.resolveTies(this.combatRound);

        assertEquals("025012008005",
                this.combatRound.getPositionForParticipant(this.char2));
    }

}
