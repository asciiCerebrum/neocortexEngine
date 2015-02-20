package org.asciicerebrum.mydndgame.mechanics.interactionworkflows;

import org.asciicerebrum.mydndgame.domain.core.particles.ArmorClass;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusRank;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.core.particles.CriticalMinimumLevel;
import org.asciicerebrum.mydndgame.domain.core.particles.DiceRoll;
import org.asciicerebrum.mydndgame.domain.mechanics.workflow.IWorkflow;
import org.asciicerebrum.mydndgame.domain.mechanics.workflow.Interaction;
import org.asciicerebrum.mydndgame.domain.ruleentities.DiceAction;
import org.asciicerebrum.mydndgame.facades.game.WeaponServiceFacade;
import org.asciicerebrum.mydndgame.managers.DiceRollManager;
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

    private DiceRollManager diceRollManager;

    private WeaponServiceFacade weaponServiceFacade;

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
        this.diceRollManager = mock(DiceRollManager.class);
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
        this.singleAttackWf.setDiceRollManager(this.diceRollManager);
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
        final CriticalMinimumLevel critMinLvl = new CriticalMinimumLevel();
        critMinLvl.setValue(18L);

        final boolean isCritical = this.singleAttackWf.determineCritical(
                atkRollResultRaw, sourceAtkBonus, targetAc, critMinLvl);
        assertFalse(isCritical);
    }

    @Test
    public void determineCriticalThreatButNoHitTest() {
        final DiceRoll atkRollResultRaw = new DiceRoll(19L);
        final BonusValue sourceAtkBonus = new BonusValue(2L);
        final ArmorClass targetAc = new ArmorClass();
        targetAc.setValue(10L);
        final CriticalMinimumLevel critMinLvl = new CriticalMinimumLevel();
        critMinLvl.setValue(18L);

        final DiceRoll secondAtkRollResultRaw = new DiceRoll(4L);
        when(this.diceRollManager.rollDice(this.attackAction))
                .thenReturn(secondAtkRollResultRaw);

        final boolean isCritical = this.singleAttackWf.determineCritical(
                atkRollResultRaw, sourceAtkBonus, targetAc, critMinLvl);
        assertFalse(isCritical);
    }

    @Test
    public void determineCriticalThreatWithHitTest() {
        final DiceRoll atkRollResultRaw = new DiceRoll(19L);
        final BonusValue sourceAtkBonus = new BonusValue(2L);
        final ArmorClass targetAc = new ArmorClass();
        targetAc.setValue(10L);
        final CriticalMinimumLevel critMinLvl = new CriticalMinimumLevel();
        critMinLvl.setValue(18L);

        final DiceRoll secondAtkRollResultRaw = new DiceRoll(16L);
        when(this.diceRollManager.rollDice(this.attackAction))
                .thenReturn(secondAtkRollResultRaw);

        final boolean isCritical = this.singleAttackWf.determineCritical(
                atkRollResultRaw, sourceAtkBonus, targetAc, critMinLvl);
        assertTrue(isCritical);
    }

    @Test
    public void isHitAutoFailureTest() {
        final DiceRoll attackRoll = new DiceRoll(1L);
        final BonusValue atkBonus = new BonusValue(2L);
        final ArmorClass targetAc = new ArmorClass();
        targetAc.setValue(16L);

        final boolean isHit = this.singleAttackWf.isHit(
                attackRoll, atkBonus, targetAc);
        assertFalse(isHit);
    }

    @Test
    public void isHitAutoSuccessTest() {
        final DiceRoll attackRoll = new DiceRoll(20L);
        final BonusValue atkBonus = new BonusValue(2L);
        final ArmorClass targetAc = new ArmorClass();
        targetAc.setValue(24L);

        final boolean isHit = this.singleAttackWf.isHit(
                attackRoll, atkBonus, targetAc);
        assertTrue(isHit);
    }

    @Test
    public void isHitNormalSuccessTest() {
        final DiceRoll attackRoll = new DiceRoll(16L);
        final BonusValue atkBonus = new BonusValue(2L);
        final ArmorClass targetAc = new ArmorClass();
        targetAc.setValue(17L);

        final boolean isHit = this.singleAttackWf.isHit(
                attackRoll, atkBonus, targetAc);
        assertTrue(isHit);
    }

    @Test
    public void isHitNormalFailureTest() {
        final DiceRoll attackRoll = new DiceRoll(16L);
        final BonusValue atkBonus = new BonusValue(2L);
        final ArmorClass targetAc = new ArmorClass();
        targetAc.setValue(19L);

        final boolean isHit = this.singleAttackWf.isHit(
                attackRoll, atkBonus, targetAc);
        assertFalse(isHit);
    }

    @Test
    public void terminateCriticalWorkflowTest() {
        final Interaction interaction = new Interaction();

        this.singleAttackWf.terminate(true, interaction);
        verify(this.criticalDamageWorkflow).runWorkflow(interaction);
    }

    @Test
    public void terminateNormalWorkflowTest() {
        final Interaction interaction = new Interaction();

        this.singleAttackWf.terminate(false, interaction);
        verify(this.damageWorkflow).runWorkflow(interaction);
    }

    @Test
    public void terminateCriticalWorkflowNoNormalTest() {
        final Interaction interaction = new Interaction();

        this.singleAttackWf.terminate(true, interaction);
        verify(this.damageWorkflow, times(0)).runWorkflow(interaction);
    }

    @Test
    public void terminateNormalWorkflowNoCriticalTest() {
        final Interaction interaction = new Interaction();

        this.singleAttackWf.terminate(false, interaction);
        verify(this.criticalDamageWorkflow, times(0)).runWorkflow(interaction);
    }

}
