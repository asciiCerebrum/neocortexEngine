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
import org.asciicerebrum.mydndgame.interfaces.entities.InteractionResponseKey;
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
public class DamageWorkflowTest {

    private DamageWorkflow damageWf;

    private IInteraction interaction;

    private IInteractionResponse response;

    private ICharacter targetCharacter;

    private ICharacter triggerCharacter;

    private IDiceAction damageAction;

    private IDiceRollManager drManager;

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
        this.damageWf = new DamageWorkflow();
        this.interaction = new Interaction();
        this.response = mock(IInteractionResponse.class);
        this.targetCharacter = mock(ICharacter.class);
        this.triggerCharacter = mock(ICharacter.class);

        List<ICharacter> targetCharacters = new ArrayList<ICharacter>();
        this.targetCharacter = mock(ICharacter.class);
        targetCharacters.add(this.targetCharacter);

        this.interaction.setTriggeringCharacter(this.triggerCharacter);
        this.interaction.setTargetCharacters(targetCharacters);

        ISituationContext triggerContext = mock(ISituationContext.class);
        IBodySlotType bsType = mock(IBodySlotType.class);
        Slotable bs = mock(Slotable.class);
        IWeapon triggerWeapon = mock(IWeapon.class);

        when(triggerWeapon.getCriticalMinimumLevel()).thenReturn(20);
        when(triggerWeapon.getCriticalFactor()).thenReturn(2);
        when(triggerWeapon.getDamage()).thenReturn(this.damageAction);
        when(bs.getItem()).thenReturn(triggerWeapon);
        when(triggerContext.getBodySlotType()).thenReturn(bsType);
        when(this.triggerCharacter.getAtkBoni()).thenReturn(
                new ArrayList<Long>(Arrays.asList(2L)));
        when(this.triggerCharacter.getSituationContext()).thenReturn(triggerContext);
        when(this.triggerCharacter.getBodySlotByType(bsType)).thenReturn(bs);

        this.drManager = mock(IDiceRollManager.class);
        when(this.drManager.rollDice(this.damageAction)).thenReturn(8L);
        this.damageWf.setDiceRollManager(this.drManager);
        this.damageWf.setMinimumDamage(1L);
        this.damageWf.setAttackCriticalKey(InteractionResponseKey.ATTACK_CRITICAL);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testRunWorkflowDamage() {
        this.damageWf.runWorkflow(this.interaction, this.response);

        verify(this.drManager, times(1)).rollDice(this.damageAction);
    }

    /**
     * Test of runWorkflow method, of class AttackWorkflow. This is a hit with
     * attack 12 (roll 10 + bonus 2) vs ac 12.
     */
    @Test
    public void testRunWorkflowHit() {
        this.damageWf.runWorkflow(this.interaction, this.response);

        verify(this.targetCharacter).applyDamage((IDamage) anyObject());
    }

    @Test
    public void testRunWorkflowCriticalHitDamage() {
        when(this.response.getValue(InteractionResponseKey.ATTACK_CRITICAL,
                Boolean.class)).thenReturn(Boolean.TRUE);

        this.damageWf.runWorkflow(this.interaction, this.response);

        verify(this.drManager, times(2))
                .rollDice(this.damageAction);
    }

    /**
     * First attack roll is critical, but second is not.
     */
    @Test
    public void testRunWorkflowCriticalMissDamage() {
        this.damageWf.runWorkflow(this.interaction, this.response);

        verify(this.drManager, times(1))
                .rollDice(this.damageAction);
    }

}
