package org.asciicerebrum.mydndgame.mechanics.interactionworkflows;

import org.asciicerebrum.mydndgame.domain.core.particles.CriticalFactor;
import org.asciicerebrum.mydndgame.domain.game.Armor;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.domain.mechanics.workflow.IWorkflow;
import org.asciicerebrum.mydndgame.domain.mechanics.workflow.Interaction;
import org.asciicerebrum.mydndgame.facades.game.WeaponServiceFacade;
import org.asciicerebrum.mydndgame.services.context.SituationContextService;
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

        this.workflow.setDamageWorkflow(this.damageWorkflow);
        this.workflow.setSituationContextService(this.sitConService);
        this.workflow.setWeaponServiceFacade(this.weaponServiceFacade);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void runWorkflowNoWeaponTest() {
        final Interaction interaction = new Interaction();
        final DndCharacter triggerCharacter = new DndCharacter();
        interaction.setTriggeringCharacter(triggerCharacter);

        when(this.sitConService.getActiveItem(triggerCharacter))
                .thenReturn(new Armor());

        this.workflow.runWorkflow(interaction);
        verify(this.damageWorkflow, times(0)).runWorkflow(interaction);
    }

    @Test
    public void runWorkflowNormalTest() {
        final Interaction interaction = new Interaction();
        final DndCharacter triggerCharacter = new DndCharacter();
        final CriticalFactor critFactor = new CriticalFactor(3L);
        interaction.setTriggeringCharacter(triggerCharacter);

        when(this.sitConService.getActiveItem(triggerCharacter))
                .thenReturn(new Weapon());
        when(this.weaponServiceFacade.getCriticalFactor((Weapon) anyObject(),
                eq(triggerCharacter))).thenReturn(critFactor);

        this.workflow.runWorkflow(interaction);
        verify(this.damageWorkflow, times(3)).runWorkflow(interaction);
    }

}
