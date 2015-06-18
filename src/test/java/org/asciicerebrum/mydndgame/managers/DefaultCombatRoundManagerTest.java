package org.asciicerebrum.mydndgame.managers;

import org.asciicerebrum.mydndgame.mechanics.managers.DefaultCombatRoundManager;
import javax.naming.OperationNotSupportedException;
import org.asciicerebrum.mydndgame.domain.core.particles.CombatRoundPosition;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.game.Campaign;
import org.asciicerebrum.mydndgame.domain.game.CombatRound;
import org.asciicerebrum.mydndgame.domain.game.CombatRoundEntry;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.DndCharacters;
import org.asciicerebrum.mydndgame.domain.mechanics.workflow.IWorkflow;
import org.asciicerebrum.mydndgame.domain.mechanics.workflow.Interaction;
import org.asciicerebrum.mydndgame.domain.mechanics.workflow.InteractionType;
import org.asciicerebrum.mydndgame.domain.mechanics.workflow.Workflows;
import org.asciicerebrum.mydndgame.services.events.EventTriggerService;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 *
 * @author species8472
 */
public class DefaultCombatRoundManagerTest {

    private DefaultCombatRoundManager manager;

    private IWorkflow conditionExpirationWorkflow;

    private IWorkflow initializeCombatRoundWorkflow;

    private EventTriggerService eventTriggerService;

    public DefaultCombatRoundManagerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws OperationNotSupportedException {
        this.manager = new DefaultCombatRoundManager();
        this.conditionExpirationWorkflow = mock(IWorkflow.class);
        this.initializeCombatRoundWorkflow = mock(IWorkflow.class);
        this.eventTriggerService = mock(EventTriggerService.class);

        this.manager.setConditionExpirationWorkflow(
                this.conditionExpirationWorkflow);
        this.manager.setInitializeCombatRoundWorkflow(
                this.initializeCombatRoundWorkflow);
        this.manager.setEventTriggerService(
                this.eventTriggerService);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void initiateCombatRoundConditionExpirationTest()
            throws OperationNotSupportedException {
        final DndCharacters dndCharacters = new DndCharacters();
        final Campaign campaign = new Campaign();

        this.manager.initiateCombatRound(campaign, dndCharacters);

        verify(this.conditionExpirationWorkflow, times(1)).runWorkflow(
                (Interaction) anyObject(), eq(campaign));
    }

    @Test
    public void initiateCombatRoundNullConditionExpirationTest()
            throws OperationNotSupportedException {
        final DndCharacters dndCharacters = new DndCharacters();
        final Campaign campaign = new Campaign();

        this.manager.setConditionExpirationWorkflow(null);
        this.manager.initiateCombatRound(campaign, dndCharacters);

        verify(this.conditionExpirationWorkflow, times(0)).runWorkflow(
                (Interaction) anyObject(), eq(campaign));
    }

    @Test
    public void initiateCombatRoundInitializeTest()
            throws OperationNotSupportedException {
        final DndCharacters dndCharacters = new DndCharacters();
        final Campaign campaign = new Campaign();

        this.manager.initiateCombatRound(campaign, dndCharacters);

        verify(this.initializeCombatRoundWorkflow, times(1)).runWorkflow(
                (Interaction) anyObject(), eq(campaign));
    }

    @Test
    public void initiateCombatRoundNullInitializeTest()
            throws OperationNotSupportedException {
        final DndCharacters dndCharacters = new DndCharacters();
        final Campaign campaign = new Campaign();

        this.manager.setInitializeCombatRoundWorkflow(null);
        this.manager.initiateCombatRound(campaign, dndCharacters);

        verify(this.initializeCombatRoundWorkflow, times(0)).runWorkflow(
                (Interaction) anyObject(), eq(campaign));
    }

    @Test
    public void executeInteractionNormalTest() {
        final Campaign campaign = new Campaign();
        final Interaction interaction = new Interaction();
        final InteractionType interactionType = new InteractionType();
        final CombatRound combatRound = new CombatRound();
        final DndCharacter dndCharacterA = new DndCharacter();
        final DndCharacter dndCharacterB = new DndCharacter();
        dndCharacterA.setUniqueId(new UniqueId("A"));
        dndCharacterB.setUniqueId(new UniqueId("B"));
        final IWorkflow flowA = mock(IWorkflow.class);
        final Workflows flows = new Workflows();
        flows.addWorkflow(flowA);
        flows.addWorkflow(flowA);

        interaction.setInteractionType(interactionType);
        interactionType.setWorkflows(flows);

        final CombatRoundEntry entryA = new CombatRoundEntry();
        entryA.setParticipantId(dndCharacterA.getUniqueId());
        entryA.setCombatRoundPosition(new CombatRoundPosition("1"));
        combatRound.addCombatRoundEntry(entryA);

        final CombatRoundEntry entryB = new CombatRoundEntry();
        entryB.setParticipantId(dndCharacterB.getUniqueId());
        entryB.setCombatRoundPosition(new CombatRoundPosition("0"));
        combatRound.addCombatRoundEntry(entryB);

        campaign.setCombatRound(combatRound);
        interaction.setTriggeringCharacter(dndCharacterA);

        this.manager.executeInteraction(campaign, interaction);

        verify(flowA, times(2)).runWorkflow(interaction, campaign);
    }

    @Test
    public void executeInteractionIncorrectParticipantTest() {
        final Campaign campaign = new Campaign();
        final Interaction interaction = new Interaction();
        final InteractionType interactionType = new InteractionType();
        final CombatRound combatRound = new CombatRound();
        final DndCharacter dndCharacterA = new DndCharacter();
        final DndCharacter dndCharacterB = new DndCharacter();
        dndCharacterA.setUniqueId(new UniqueId("A"));
        dndCharacterB.setUniqueId(new UniqueId("B"));
        final IWorkflow flowA = mock(IWorkflow.class);
        final Workflows flows = new Workflows();
        flows.addWorkflow(flowA);
        flows.addWorkflow(flowA);

        interaction.setInteractionType(interactionType);
        interactionType.setWorkflows(flows);

        final CombatRoundEntry entryA = new CombatRoundEntry();
        entryA.setParticipantId(dndCharacterA.getUniqueId());
        entryA.setCombatRoundPosition(new CombatRoundPosition("1"));
        combatRound.addCombatRoundEntry(entryA);

        final CombatRoundEntry entryB = new CombatRoundEntry();
        entryB.setParticipantId(dndCharacterB.getUniqueId());
        entryB.setCombatRoundPosition(new CombatRoundPosition("0"));
        combatRound.addCombatRoundEntry(entryB);

        campaign.setCombatRound(combatRound);
        interaction.setTriggeringCharacter(dndCharacterB);

        this.manager.executeInteraction(campaign, interaction);

        verify(flowA, times(0)).runWorkflow(interaction, campaign);
    }

}
