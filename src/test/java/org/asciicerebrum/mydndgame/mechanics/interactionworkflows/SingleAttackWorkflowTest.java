package org.asciicerebrum.mydndgame.mechanics.interactionworkflows;

import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.core.particles.ArmorClass;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusRank;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.core.particles.CriticalMinimumLevel;
import org.asciicerebrum.mydndgame.domain.core.particles.DiceRoll;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueIds;
import org.asciicerebrum.mydndgame.domain.game.Campaign;
import org.asciicerebrum.mydndgame.domain.game.CombatRound;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.domain.mechanics.WorldDate;
import org.asciicerebrum.mydndgame.domain.mechanics.workflow.IWorkflow;
import org.asciicerebrum.mydndgame.domain.mechanics.workflow.Interaction;
import org.asciicerebrum.mydndgame.domain.ruleentities.DiceAction;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.RollResult;
import org.asciicerebrum.mydndgame.facades.game.WeaponServiceFacade;
import org.asciicerebrum.mydndgame.mechanics.managers.RollResultManager;
import org.asciicerebrum.mydndgame.services.context.SituationContextService;
import org.asciicerebrum.mydndgame.services.statistics.AcCalculationService;
import org.asciicerebrum.mydndgame.services.statistics.AtkCalculationService;
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
public class SingleAttackWorkflowTest {

    private SingleAttackWorkflow singleAttackWf;

    private SituationContextService sitConService;

    private AcCalculationService acCalcService;

    private AtkCalculationService atkCalcService;

    private DiceAction attackAction;

    private DiceRoll autoFailureRoll;

    private DiceRoll autoSuccessRoll;

    private IWorkflow criticalDamageWorkflow;

    private IWorkflow damageWorkflow;

    private WeaponServiceFacade weaponServiceFacade;

    private RollResultManager rollResultManager;

    public SingleAttackWorkflowTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.singleAttackWf = new SingleAttackWorkflow();
        this.sitConService = mock(SituationContextService.class);
        this.acCalcService = mock(AcCalculationService.class);
        this.atkCalcService = mock(AtkCalculationService.class);
        this.attackAction = new DiceAction();
        this.autoFailureRoll = new DiceRoll(1L);
        this.autoSuccessRoll = new DiceRoll(20L);
        this.criticalDamageWorkflow = mock(IWorkflow.class);
        this.damageWorkflow = mock(IWorkflow.class);
        this.rollResultManager = mock(RollResultManager.class);
        this.weaponServiceFacade = mock(WeaponServiceFacade.class);

        this.singleAttackWf.setSituationContextService(this.sitConService);
        this.singleAttackWf.setAcService(this.acCalcService);
        this.singleAttackWf.setAtkBonusRank(BonusRank.RANK_0);
        this.singleAttackWf.setAtkService(this.atkCalcService);
        this.singleAttackWf.setAttackAction(this.attackAction);
        this.singleAttackWf.setAutoFailureRoll(this.autoFailureRoll);
        this.singleAttackWf.setAutoSuccessRoll(this.autoSuccessRoll);
        this.singleAttackWf.setCriticalDamageWorkflow(
                this.criticalDamageWorkflow);
        this.singleAttackWf.setDamageWorkflow(this.damageWorkflow);
        this.singleAttackWf.setRollResultManager(this.rollResultManager);
        this.singleAttackWf.setWeaponServiceFacade(this.weaponServiceFacade);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void determineCriticalNoThreatTest() {
        final DiceRoll atkRollResultRaw = new DiceRoll(10L);
        final BonusValue sourceAtkBonus = new BonusValue(2L);
        final ArmorClass targetAc = new ArmorClass();
        final CriticalMinimumLevel critMinLvl = new CriticalMinimumLevel(18L);
        final RollResult rollResult = new RollResult(atkRollResultRaw,
                sourceAtkBonus);
        final Interaction interaction = new Interaction();
        final Campaign campaign = new Campaign();
        final Weapon weapon = new Weapon();

        final boolean isCritical = this.singleAttackWf.determineCritical(
                rollResult, targetAc, critMinLvl, weapon, interaction,
                campaign);
        assertFalse(isCritical);
    }

    @Test
    public void determineCriticalThreatButNoHitTest() {
        final DiceRoll atkRollResultRaw = new DiceRoll(19L);
        final BonusValue sourceAtkBonus = new BonusValue(2L);
        final ArmorClass targetAc = new ArmorClass();
        targetAc.setValue(10L);
        final CriticalMinimumLevel critMinLvl = new CriticalMinimumLevel(18L);
        final Interaction interaction = new Interaction();
        final Campaign campaign = new Campaign();
        campaign.setCombatRound(new CombatRound());
        final Weapon weapon = new Weapon();

        final RollResult atkResult
                = new RollResult(atkRollResultRaw, sourceAtkBonus);
        final RollResult result = new RollResult(new DiceRoll(4L),
                new BonusValue());
        when(this.rollResultManager.retrieveRollResult(
                (BonusValue) anyObject(),
                eq(this.attackAction),
                (UniqueEntity) anyObject(),
                (DndCharacter) anyObject(),
                (UniqueIds) anyObject(),
                (WorldDate) anyObject(),
                (Campaign) anyObject()))
                .thenReturn(result);

        final boolean isCritical = this.singleAttackWf.determineCritical(
                atkResult, targetAc, critMinLvl, weapon, interaction, campaign);
        assertFalse(isCritical);
    }

    @Test
    public void determineCriticalThreatWithHitTest() {
        final DiceRoll atkRollResultRaw = new DiceRoll(19L);
        final BonusValue sourceAtkBonus = new BonusValue(2L);
        final ArmorClass targetAc = new ArmorClass();
        targetAc.setValue(10L);
        final CriticalMinimumLevel critMinLvl = new CriticalMinimumLevel(18L);
        final Interaction interaction = new Interaction();
        final Campaign campaign = new Campaign();
        final Weapon weapon = new Weapon();
        campaign.setCombatRound(new CombatRound());

        final RollResult atkResult
                = new RollResult(atkRollResultRaw, sourceAtkBonus);

        final RollResult result = new RollResult(new DiceRoll(16L),
                new BonusValue());
        when(this.rollResultManager.retrieveRollResult(
                (BonusValue) anyObject(),
                eq(this.attackAction),
                (UniqueEntity) anyObject(),
                (DndCharacter) anyObject(),
                (UniqueIds) anyObject(),
                (WorldDate) anyObject(),
                (Campaign) anyObject()))
                .thenReturn(result);

        final boolean isCritical = this.singleAttackWf.determineCritical(
                atkResult, targetAc, critMinLvl, weapon, interaction, campaign);
        assertTrue(isCritical);
    }

    @Test
    public void isHitAutoFailureTest() {
        final DiceRoll attackRoll = new DiceRoll(1L);
        final BonusValue atkBonus = new BonusValue(2L);
        final ArmorClass targetAc = new ArmorClass();
        targetAc.setValue(16L);
        final RollResult rollResult = new RollResult(attackRoll, atkBonus);

        final boolean isHit = this.singleAttackWf.isHit(rollResult, targetAc);
        assertFalse(isHit);
    }

    @Test
    public void isHitAutoSuccessTest() {
        final DiceRoll attackRoll = new DiceRoll(20L);
        final BonusValue atkBonus = new BonusValue(2L);
        final ArmorClass targetAc = new ArmorClass();
        targetAc.setValue(24L);
        final RollResult rollResult = new RollResult(attackRoll, atkBonus);

        final boolean isHit = this.singleAttackWf.isHit(rollResult, targetAc);
        assertTrue(isHit);
    }

    @Test
    public void isHitNormalSuccessTest() {
        final DiceRoll attackRoll = new DiceRoll(16L);
        final BonusValue atkBonus = new BonusValue(2L);
        final ArmorClass targetAc = new ArmorClass();
        targetAc.setValue(17L);
        final RollResult rollResult = new RollResult(attackRoll, atkBonus);

        final boolean isHit = this.singleAttackWf.isHit(rollResult, targetAc);
        assertTrue(isHit);
    }

    @Test
    public void isHitNormalFailureTest() {
        final DiceRoll attackRoll = new DiceRoll(16L);
        final BonusValue atkBonus = new BonusValue(2L);
        final ArmorClass targetAc = new ArmorClass();
        targetAc.setValue(19L);
        final RollResult rollResult = new RollResult(attackRoll, atkBonus);

        final boolean isHit = this.singleAttackWf.isHit(rollResult, targetAc);
        assertFalse(isHit);
    }

    @Test
    public void terminateCriticalWorkflowTest() {
        final Interaction interaction = new Interaction();
        final Campaign campaign = new Campaign();
        final ArmorClass targetAc = new ArmorClass();
        targetAc.setValue(19L);

        this.singleAttackWf.setEventTriggerService(null);
        this.singleAttackWf.terminate(true, interaction, campaign, targetAc);
        verify(this.criticalDamageWorkflow).runWorkflow(interaction, campaign);
    }

    @Test
    public void terminateNormalWorkflowTest() {
        final Interaction interaction = new Interaction();
        final Campaign campaign = new Campaign();
        final ArmorClass targetAc = new ArmorClass();
        targetAc.setValue(19L);

        this.singleAttackWf.setEventTriggerService(null);
        this.singleAttackWf.terminate(false, interaction, campaign, targetAc);
        verify(this.damageWorkflow).runWorkflow(interaction, campaign);
    }

    @Test
    public void terminateCriticalWorkflowNoNormalTest() {
        final Interaction interaction = new Interaction();
        final Campaign campaign = new Campaign();
        final ArmorClass targetAc = new ArmorClass();
        targetAc.setValue(19L);

        this.singleAttackWf.setEventTriggerService(null);
        this.singleAttackWf.terminate(true, interaction, campaign, targetAc);
        verify(this.damageWorkflow, times(0)).runWorkflow(interaction,
                campaign);
    }

    @Test
    public void terminateNormalWorkflowNoCriticalTest() {
        final Interaction interaction = new Interaction();
        final Campaign campaign = new Campaign();
        final ArmorClass targetAc = new ArmorClass();
        targetAc.setValue(19L);

        this.singleAttackWf.setEventTriggerService(null);
        this.singleAttackWf.terminate(false, interaction, campaign, targetAc);
        verify(this.criticalDamageWorkflow, times(0)).runWorkflow(interaction,
                campaign);
    }

}
