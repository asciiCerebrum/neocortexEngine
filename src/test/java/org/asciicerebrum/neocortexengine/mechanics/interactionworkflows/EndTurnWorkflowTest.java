package org.asciicerebrum.neocortexengine.mechanics.interactionworkflows;

import org.asciicerebrum.neocortexengine.domain.core.particles.CombatRoundPosition;
import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueId;
import org.asciicerebrum.neocortexengine.domain.game.Campaign;
import org.asciicerebrum.neocortexengine.domain.game.CombatRound;
import org.asciicerebrum.neocortexengine.domain.mechanics.workflow.IWorkflow;
import org.asciicerebrum.neocortexengine.domain.mechanics.workflow.Interaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 *
 * @author species8472
 */
public class EndTurnWorkflowTest {

    private EndTurnWorkflow endFlow;

    private IWorkflow condExpFlow;

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
        this.endFlow = new EndTurnWorkflow();
        this.condExpFlow = mock(IWorkflow.class);

        this.endFlow.setConditionExpirationWorkflow(this.condExpFlow);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void runWorkflowNormalTest() {
        final Interaction interaction = new Interaction();
        final Campaign campaign = new Campaign();
        final CombatRound combatRound = new CombatRound();
        campaign.setCombatRound(combatRound);
        combatRound.addParticipant(new UniqueId("character"),
                new CombatRoundPosition("1"));

        this.endFlow.runWorkflow(interaction, campaign);
        verify(this.condExpFlow, times(1)).runWorkflow(interaction, campaign);
    }

    @Test
    public void runWorkflowNoCondExpTest() {
        final Interaction interaction = new Interaction();
        final Campaign campaign = new Campaign();
        final CombatRound combatRound = new CombatRound();
        campaign.setCombatRound(combatRound);
        combatRound.addParticipant(new UniqueId("character"),
                new CombatRoundPosition("1"));
        this.endFlow.setConditionExpirationWorkflow(null);
        this.endFlow.runWorkflow(interaction, campaign);
        verify(this.condExpFlow, times(0)).runWorkflow(interaction, campaign);
    }
}
