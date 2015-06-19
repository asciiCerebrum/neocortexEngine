package org.asciicerebrum.mydndgame.mechanics.interactionworkflows;

import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.core.particles.CombatRoundPosition;
import org.asciicerebrum.mydndgame.domain.core.particles.DiceRoll;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueIds;
import org.asciicerebrum.mydndgame.domain.game.Armor;
import org.asciicerebrum.mydndgame.domain.game.Campaign;
import org.asciicerebrum.mydndgame.domain.game.CombatRound;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.DndCharacters;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.domain.mechanics.WorldDate;
import org.asciicerebrum.mydndgame.domain.mechanics.damage.Damages;
import org.asciicerebrum.mydndgame.domain.mechanics.workflow.Interaction;
import org.asciicerebrum.mydndgame.domain.ruleentities.DiceAction;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.RollResult;
import org.asciicerebrum.mydndgame.facades.game.WeaponServiceFacade;
import org.asciicerebrum.mydndgame.mechanics.managers.RollResultManager;
import org.asciicerebrum.mydndgame.services.application.DamageApplicationService;
import org.asciicerebrum.mydndgame.services.context.EntityPoolService;
import org.asciicerebrum.mydndgame.services.context.SituationContextService;
import org.asciicerebrum.mydndgame.services.statistics.DamageCalculationService;
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
public class DamageWorkflowTest {

    private DamageWorkflow damageWorkflow;

    private DamageApplicationService damageAppService;

    private DamageCalculationService damageCalcService;

    private RollResultManager rollResultManager;

    private DiceRoll minimumDamage;

    private SituationContextService sitConService;

    private WeaponServiceFacade weaponFacade;
    
    private EntityPoolService entityPoolService;

    public DamageWorkflowTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.damageWorkflow = new DamageWorkflow();
        this.damageAppService = mock(DamageApplicationService.class);
        this.damageCalcService = mock(DamageCalculationService.class);
        this.rollResultManager = mock(RollResultManager.class);
        this.minimumDamage = new DiceRoll(1L);
        this.sitConService = mock(SituationContextService.class);
        this.weaponFacade = mock(WeaponServiceFacade.class);
        this.entityPoolService = mock(EntityPoolService.class);

        this.damageWorkflow.setDamageApplicationService(this.damageAppService);
        this.damageWorkflow.setDamageService(this.damageCalcService);
        this.damageWorkflow.setRollResultManager(this.rollResultManager);
        this.damageWorkflow.setMinimumDamage(this.minimumDamage);
        this.damageWorkflow.setSituationContextService(this.sitConService);
        this.damageWorkflow.setWeaponServiceFacade(this.weaponFacade);
        this.damageWorkflow.setEntityPoolService(this.entityPoolService);

        final RollResult result = new RollResult(new DiceRoll(4L),
                new BonusValue());

        when(this.rollResultManager.retrieveRollResult(
                (BonusValue) anyObject(),
                (DiceAction) anyObject(),
                (UniqueEntity) anyObject(),
                (DndCharacter) anyObject(),
                (UniqueIds) anyObject(),
                (WorldDate) anyObject(),
                (Campaign) anyObject()))
                .thenReturn(result);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void runWorkflowNoWeaponTest() {
        final Interaction interaction = new Interaction();
        final Campaign campaign = new Campaign();

        when(this.entityPoolService.getEntityById((UniqueId) anyObject()))
                .thenReturn(new Armor());

        this.damageWorkflow.runWorkflow(interaction, campaign);
        verify(this.damageAppService, times(0)).applyDamage(
                (DndCharacter) anyObject(), (Damages) anyObject());
    }

    @Test
    public void runWorkflowWithWeaponTest() {
        final Interaction interaction = new Interaction();
        final Campaign campaign = new Campaign();
        final DndCharacters targetCharacters = new DndCharacters();
        final DndCharacter firstTarget = new DndCharacter();
        final CombatRound combatRound = new CombatRound();
        targetCharacters.addDndCharacter(firstTarget);
        interaction.setTargetCharacters(targetCharacters);
        campaign.setCombatRound(combatRound);
        combatRound.addParticipant(new UniqueId("participant"),
                new CombatRoundPosition(""));

        when(this.entityPoolService.getEntityById((UniqueId) anyObject()))
                .thenReturn(new Weapon());

        this.damageWorkflow.runWorkflow(interaction, campaign);
        verify(this.damageAppService, times(1)).applyDamage(
                (DndCharacter) anyObject(), (Damages) anyObject());
    }
}
