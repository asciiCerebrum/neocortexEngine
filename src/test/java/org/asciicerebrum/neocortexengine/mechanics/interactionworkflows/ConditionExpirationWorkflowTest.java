package org.asciicerebrum.neocortexengine.mechanics.interactionworkflows;

import org.asciicerebrum.neocortexengine.domain.core.particles.CombatRoundPosition;
import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueId;
import org.asciicerebrum.neocortexengine.domain.game.Campaign;
import org.asciicerebrum.neocortexengine.domain.game.CombatRound;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.mechanics.WorldDate;
import org.asciicerebrum.neocortexengine.domain.mechanics.workflow.Interaction;
import org.asciicerebrum.neocortexengine.services.application.ConditionApplicationService;
import org.asciicerebrum.neocortexengine.services.context.EntityPoolService;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author species8472
 */
public class ConditionExpirationWorkflowTest {

    private ConditionExpirationWorkflow workflow;

    private ConditionApplicationService conditionApplicationService;
    
    private EntityPoolService entityPoolService;

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
        this.entityPoolService = mock(EntityPoolService.class);

        this.workflow.setConditionService(this.conditionApplicationService);
        this.workflow.setEntityPoolService(this.entityPoolService);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void runWorkflowTest() {
        final Interaction interaction = new Interaction();
        final Campaign campaign = new Campaign();
        final DndCharacter characterA = new DndCharacter();
        characterA.setUniqueId(new UniqueId("characterA"));
        final DndCharacter characterB = new DndCharacter();
        characterB.setUniqueId(new UniqueId("characterB"));
        final CombatRound combatRound = new CombatRound();
        combatRound.addParticipant(characterA.getUniqueId(),
                new CombatRoundPosition("0"));
        combatRound.addParticipant(characterB.getUniqueId(),
                new CombatRoundPosition("1"));
        campaign.setCombatRound(combatRound);
        
        when(this.entityPoolService.getEntityById((UniqueId) anyObject()))
                .thenReturn(new DndCharacter());

        this.workflow.runWorkflow(interaction, campaign);
        verify(this.conditionApplicationService, times(2))
                .removeExpiredConditions((DndCharacter) anyObject(),
                        (WorldDate) anyObject());
    }

}
