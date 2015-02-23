package org.asciicerebrum.mydndgame.mechanics.interactionworkflows;

import org.asciicerebrum.mydndgame.domain.core.particles.CombatRoundPosition;
import org.asciicerebrum.mydndgame.domain.game.CombatRound;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.mechanics.WorldDate;
import org.asciicerebrum.mydndgame.domain.mechanics.workflow.Interaction;
import org.asciicerebrum.mydndgame.services.application.ConditionApplicationService;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 *
 * @author species8472
 */
public class ConditionExpirationWorkflowTest {

    private ConditionExpirationWorkflow workflow;

    private ConditionApplicationService conditionApplicationService;

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
        this.workflow = new ConditionExpirationWorkflow();
        this.conditionApplicationService
                = mock(ConditionApplicationService.class);

        this.workflow.setConditionService(this.conditionApplicationService);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void runWorkflowTest() {
        final Interaction interaction = new Interaction();
        final DndCharacter characterA = new DndCharacter();
        final DndCharacter characterB = new DndCharacter();
        final CombatRound combatRound = new CombatRound();
        combatRound.addParticipant(characterA, new CombatRoundPosition("0"));
        combatRound.addParticipant(characterB, new CombatRoundPosition("1"));
        interaction.setCombatRound(combatRound);

        this.workflow.runWorkflow(interaction);
        verify(this.conditionApplicationService, times(2))
                .removeExpiredConditions((DndCharacter) anyObject(),
                        (WorldDate) anyObject());
    }

}
