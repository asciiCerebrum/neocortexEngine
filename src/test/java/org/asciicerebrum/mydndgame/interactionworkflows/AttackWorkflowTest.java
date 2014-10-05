package org.asciicerebrum.mydndgame.interactionworkflows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.asciicerebrum.mydndgame.Interaction;
import org.asciicerebrum.mydndgame.interfaces.entities.IBodySlotType;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.IDamage;
import org.asciicerebrum.mydndgame.interfaces.entities.IDiceAction;
import org.asciicerebrum.mydndgame.interfaces.entities.IInteraction;
import org.asciicerebrum.mydndgame.interfaces.entities.IInteractionResponse;
import org.asciicerebrum.mydndgame.interfaces.entities.ISituationContext;
import org.asciicerebrum.mydndgame.interfaces.entities.IWeapon;
import org.asciicerebrum.mydndgame.interfaces.entities.IWorkflow;
import org.asciicerebrum.mydndgame.interfaces.entities.Slotable;
import org.asciicerebrum.mydndgame.interfaces.managers.IDiceRollManager;
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
public class AttackWorkflowTest {

    private AttackWorkflow attackWf;

    private IInteraction interaction;

    private IInteractionResponse response;

    private IDiceAction attackAction;

    private IDiceAction damageAction;

    private IDiceRollManager drManager;

    private ICharacter targetCharacter;

    private ICharacter triggerCharacter;

    private IWorkflow damageWorkflow;

    public AttackWorkflowTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.attackWf = new AttackWorkflow();
        this.interaction = new Interaction();
        this.response = mock(IInteractionResponse.class);
        this.damageWorkflow = mock(IWorkflow.class);

        this.attackAction = mock(IDiceAction.class);
        this.drManager = mock(IDiceRollManager.class);
        when(this.drManager.rollDice(this.attackAction)).thenReturn(10L);

        this.attackWf.setAttackAction(this.attackAction);
        this.attackWf.setAutoFailureRoll(1L);
        this.attackWf.setAutoSuccessRoll(20L);
        this.attackWf.setDiceRollManager(this.drManager);
        this.attackWf.setDamageWorkflow(this.damageWorkflow);

        this.triggerCharacter = mock(ICharacter.class);
        ISituationContext triggerContext = mock(ISituationContext.class);
        IBodySlotType bsType = mock(IBodySlotType.class);
        Slotable bs = mock(Slotable.class);
        IWeapon triggerWeapon = mock(IWeapon.class);
        this.damageAction = mock(IDiceAction.class);

        when(triggerWeapon.getCriticalMinimumLevel()).thenReturn(20);
        when(triggerWeapon.getCriticalFactor()).thenReturn(2);
        when(triggerWeapon.getDamage()).thenReturn(this.damageAction);
        when(bs.getItem()).thenReturn(triggerWeapon);
        when(triggerContext.getBodySlotType()).thenReturn(bsType);
        when(this.triggerCharacter.getAtkBoni()).thenReturn(
                new ArrayList<Long>(Arrays.asList(2L)));
        when(this.triggerCharacter.getSituationContext()).thenReturn(triggerContext);
        when(this.triggerCharacter.getBodySlotByType(bsType)).thenReturn(bs);

        List<ICharacter> targetCharacters = new ArrayList<ICharacter>();
        this.targetCharacter = mock(ICharacter.class);
        when(this.targetCharacter.getAc()).thenReturn(12L);
        targetCharacters.add(this.targetCharacter);

        this.interaction.setTriggeringCharacter(this.triggerCharacter);
        this.interaction.setTargetCharacters(targetCharacters);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of runWorkflow method, of class AttackWorkflow. This is a hit with
     * attack 12 (roll 10 + bonus 2) vs ac 12.
     */
    @Test
    public void testRunWorkflow() {
        this.attackWf.runWorkflow(this.interaction, this.response);

        verify(this.damageWorkflow).runWorkflow(
                this.interaction, this.response);
    }

    @Test
    public void testRunWorkflowDamage() {
        this.attackWf.runWorkflow(this.interaction, this.response);

        verify(this.damageWorkflow, times(1)).runWorkflow(this.interaction,
                this.response);
    }

    /**
     * This is a miss with ac 13.
     */
    @Test
    public void testRunWorkflowMiss() {
        when(this.targetCharacter.getAc()).thenReturn(13L);

        this.attackWf.runWorkflow(this.interaction, this.response);

        verify(this.targetCharacter, times(0))
                .applyDamage((IDamage) anyObject());
    }

    /**
     * This is a critical hit.
     */
    @Test
    public void testRunWorkflowCriticalHit() {
        when(this.drManager.rollDice(this.attackAction)).thenReturn(20L);

        this.attackWf.runWorkflow(this.interaction, this.response);

        verify(this.drManager, times(2))
                .rollDice(this.attackAction);
    }

    /**
     * First attack roll is critical, but second is not.
     */
    @Test
    public void testRunWorkflowCriticalMiss() {
        when(this.drManager.rollDice(this.attackAction)).thenReturn(20L, 10L);

        this.attackWf.runWorkflow(this.interaction, this.response);

        verify(this.drManager, times(2))
                .rollDice(this.attackAction);
    }

    /**
     * First attack roll is critical, but second is not.
     */
    @Test
    public void testRunWorkflowCriticalMissDamage() {
        when(this.drManager.rollDice(this.attackAction)).thenReturn(20L, 10L);

        this.attackWf.runWorkflow(this.interaction, this.response);

        verify(this.damageWorkflow).runWorkflow(
                this.interaction, this.response);
    }

    @Test
    public void testRunWorkflowAutoFailure() {
        when(this.drManager.rollDice(this.attackAction)).thenReturn(1L);

        this.attackWf.runWorkflow(this.interaction, this.response);

        // this line should not be reached - getting the atk boni of the
        // trigger character.
        verify(this.triggerCharacter, times(0)).getAtkBoni();
    }

    /**
     * Attack roll below AC but is an automatic success though!
     */
    @Test
    public void testRunWorkflowAutoSuccess() {
        this.attackWf.setAutoSuccessRoll(8L);
        when(this.drManager.rollDice(this.attackAction)).thenReturn(8L);

        this.attackWf.runWorkflow(this.interaction, this.response);

        verify(this.damageWorkflow, times(1))
                .runWorkflow(this.interaction, this.response);
    }

    //TODO Important!!! Test for secondCritical - it is enough to succeed AC!
    // The second critical has nothing to do with the weapons critical minimum!
    //TODO Important!!! Test all invocations of damageWorkflow, if the correct
    // parameters were transmitted (critical, etc.)
}
