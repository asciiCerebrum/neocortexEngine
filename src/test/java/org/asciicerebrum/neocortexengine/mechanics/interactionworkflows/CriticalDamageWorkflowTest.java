package org.asciicerebrum.neocortexengine.mechanics.interactionworkflows;

import org.asciicerebrum.neocortexengine.domain.core.particles.CriticalFactor;
import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueId;
import org.asciicerebrum.neocortexengine.domain.game.Armor;
import org.asciicerebrum.neocortexengine.domain.game.Campaign;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.game.Weapon;
import org.asciicerebrum.neocortexengine.domain.mechanics.workflow.IWorkflow;
import org.asciicerebrum.neocortexengine.domain.mechanics.workflow.Interaction;
import org.asciicerebrum.neocortexengine.facades.game.WeaponServiceFacade;
import org.asciicerebrum.neocortexengine.services.context.EntityPoolService;
import org.asciicerebrum.neocortexengine.services.context.SituationContextService;
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
import static org.mockito.Mockito.when;

/**
 *
 * @author species8472
 */
public class CriticalDamageWorkflowTest {

    private CriticalDamageWorkflow workflow;

    private IWorkflow damageWorkflow;

    private SituationContextService sitConService;

    private WeaponServiceFacade weaponServiceFacade;
    
    private EntityPoolService entityPoolService;

    public CriticalDamageWorkflowTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.workflow = new CriticalDamageWorkflow();
        this.damageWorkflow = mock(IWorkflow.class);
        this.sitConService = mock(SituationContextService.class);
        this.weaponServiceFacade = mock(WeaponServiceFacade.class);
        this.entityPoolService = mock(EntityPoolService.class);

        this.workflow.setDamageWorkflow(this.damageWorkflow);
        this.workflow.setSituationContextService(this.sitConService);
        this.workflow.setWeaponServiceFacade(this.weaponServiceFacade);
        this.workflow.setEntityPoolService(this.entityPoolService);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void runWorkflowNoWeaponTest() {
        final Interaction interaction = new Interaction();
        final Campaign campaign = new Campaign();
        final DndCharacter triggerCharacter = new DndCharacter();
        interaction.setTriggeringCharacter(triggerCharacter);

        when(this.entityPoolService.getEntityById((UniqueId) anyObject()))
                .thenReturn(new Armor());

        this.workflow.runWorkflow(interaction, campaign);
        verify(this.damageWorkflow, times(0)).runWorkflow(interaction,
                campaign);
    }

    @Test
    public void runWorkflowNormalTest() {
        final Interaction interaction = new Interaction();
        final Campaign campaign = new Campaign();
        final DndCharacter triggerCharacter = new DndCharacter();
        final CriticalFactor critFactor = new CriticalFactor(3L);
        interaction.setTriggeringCharacter(triggerCharacter);

        when(this.entityPoolService.getEntityById((UniqueId) anyObject()))
                .thenReturn(new Weapon());
        when(this.weaponServiceFacade.getCriticalFactor((Weapon) anyObject(),
                eq(triggerCharacter))).thenReturn(critFactor);

        this.workflow.runWorkflow(interaction, campaign);
        verify(this.damageWorkflow, times(3)).runWorkflow(interaction,
                campaign);
    }

}
