package org.asciicerebrum.neocortexengine.integrationtests.interactions;

import com.google.common.collect.Iterators;
import javax.naming.OperationNotSupportedException;
import org.asciicerebrum.neocortexengine.domain.core.particles.DiceRoll;
import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueId;
import org.asciicerebrum.neocortexengine.domain.factories.ArmorFactory;
import org.asciicerebrum.neocortexengine.domain.factories.CampaignFactory;
import org.asciicerebrum.neocortexengine.domain.factories.DndCharacterFactory;
import org.asciicerebrum.neocortexengine.domain.factories.WeaponFactory;
import org.asciicerebrum.neocortexengine.domain.game.Campaign;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacters;
import org.asciicerebrum.neocortexengine.domain.mechanics.workflow.Interaction;
import org.asciicerebrum.neocortexengine.domain.mechanics.workflow.InteractionType;
import org.asciicerebrum.neocortexengine.domain.ruleentities.DiceAction;
import org.asciicerebrum.neocortexengine.integrationtests.pool.dndCharacters.HarskDwarfFighter2;
import org.asciicerebrum.neocortexengine.integrationtests.pool.dndCharacters.MerisielElfRogue1;
import org.asciicerebrum.neocortexengine.integrationtests.pool.dndCharacters.ValerosHumanFighter1;
import org.asciicerebrum.neocortexengine.integrationtests.pool.inventoryItems.armors.MwkChainmail;
import org.asciicerebrum.neocortexengine.integrationtests.pool.inventoryItems.armors.MwkHeavySteelShield;
import org.asciicerebrum.neocortexengine.integrationtests.pool.inventoryItems.armors.StandardLightWoodenShield;
import org.asciicerebrum.neocortexengine.integrationtests.pool.inventoryItems.armors.StandardStuddedLeather;
import org.asciicerebrum.neocortexengine.integrationtests.pool.inventoryItems.weapons.MwkBastardsword;
import org.asciicerebrum.neocortexengine.integrationtests.pool.inventoryItems.weapons.MwkRapier;
import org.asciicerebrum.neocortexengine.integrationtests.pool.inventoryItems.weapons.StandardBattleaxe;
import org.asciicerebrum.neocortexengine.integrationtests.pool.inventoryItems.weapons.StandardLongsword;
import org.asciicerebrum.neocortexengine.mechanics.managers.CombatRoundManager;
import org.asciicerebrum.neocortexengine.mechanics.managers.DefaultRollResultManager;
import org.asciicerebrum.neocortexengine.mechanics.managers.DiceRollManager;
import org.asciicerebrum.neocortexengine.mechanics.managers.RollResultManager;
import org.asciicerebrum.neocortexengine.services.context.EntityPoolService;
import org.asciicerebrum.neocortexengine.testcategories.IntegrationTest;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author species8472
 */
@Category(IntegrationTest.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class AttackAndDamageWorkflowIntegrationTest {

    private static final Logger LOG
            = LoggerFactory.getLogger(
                    AttackAndDamageWorkflowIntegrationTest.class);

    @Autowired
    private ApplicationContext context;

    @Autowired
    private EntityPoolService entityPoolService;

    @Autowired
    private DndCharacterFactory dndCharacterFactory;

    @Autowired
    private ArmorFactory armorFactory;

    @Autowired
    private WeaponFactory weaponFactory;

    @Autowired
    private CampaignFactory campaignFactory;

    @Autowired
    private CombatRoundManager combatRoundManager;

    private DiceRollManager mockDiceRollManager;

    @Before
    public void setUp() {

        this.entityPoolService.registerUniqueEntity(this.dndCharacterFactory
                .newEntity(HarskDwarfFighter2.getSetup()));

        this.entityPoolService.registerUniqueEntity(this.armorFactory
                .newEntity(StandardStuddedLeather.getSetup()));
        this.entityPoolService.registerUniqueEntity(this.weaponFactory
                .newEntity(StandardBattleaxe.getSetup()));
        this.entityPoolService.registerUniqueEntity(this.armorFactory
                .newEntity(StandardLightWoodenShield.getSetup()));

        this.entityPoolService.registerUniqueEntity(this.dndCharacterFactory
                .newEntity(MerisielElfRogue1.getSetup()));

        this.entityPoolService.registerUniqueEntity(this.weaponFactory
                .newEntity(MwkRapier.getSetup()));

        this.entityPoolService.registerUniqueEntity(this.dndCharacterFactory
                .newEntity(ValerosHumanFighter1.getSetup()));

        this.entityPoolService.registerUniqueEntity(this.armorFactory
                .newEntity(MwkChainmail.getSetup()));
        this.entityPoolService.registerUniqueEntity(this.armorFactory
                .newEntity(MwkHeavySteelShield.getSetup()));
        this.entityPoolService.registerUniqueEntity(this.weaponFactory
                .newEntity(StandardLongsword.getSetup()));
        this.entityPoolService.registerUniqueEntity(this.weaponFactory
                .newEntity(MwkBastardsword.getSetup()));

        this.mockDiceRollManager = mock(DiceRollManager.class);
        ((DefaultRollResultManager) this.context
                .getBean(RollResultManager.class))
                .setDiceRollManager(this.mockDiceRollManager);

    }

    private void initiateCombatRound(final Campaign campaign,
            final DndCharacters participants)
            throws OperationNotSupportedException {
        participants.addDndCharacter((DndCharacter) this.entityPoolService
                .getEntityById(new UniqueId("harsk")));
        participants.addDndCharacter((DndCharacter) this.entityPoolService
                .getEntityById(new UniqueId("merisiel")));
        participants.addDndCharacter((DndCharacter) this.entityPoolService
                .getEntityById(new UniqueId("valeros")));

        when(this.mockDiceRollManager.rollDice((DiceAction) anyObject()))
                .thenReturn(new DiceRoll(5L), new DiceRoll(20L),
                        new DiceRoll(8L));

        this.combatRoundManager.initiateCombatRound(campaign, participants);
    }

    private void executeMeleeSingleAttack(final Campaign campaign) {
        final Interaction meleeSingleAttack = new Interaction();
        meleeSingleAttack.setTriggeringCharacter(
                (DndCharacter) this.entityPoolService
                .getEntityById(new UniqueId("merisiel")));
        final DndCharacters targetCharacters = new DndCharacters();
        targetCharacters.addDndCharacter((DndCharacter) this.entityPoolService
                .getEntityById(new UniqueId("valeros")));
        meleeSingleAttack.setTargetCharacters(targetCharacters);
        final InteractionType interactionType
                = this.context.getBean("meleeSingleAttack",
                        InteractionType.class);
        meleeSingleAttack.setInteractionType(interactionType);

        this.combatRoundManager.executeInteraction(campaign, meleeSingleAttack);
    }

    private void executeEndTurn(final Campaign campaign) {
        final Interaction endTurn = new Interaction();
        endTurn.setTriggeringCharacter(
                (DndCharacter) this.entityPoolService
                .getEntityById(new UniqueId("merisiel")));
        final InteractionType interactionType
                = this.context.getBean("endTurn", InteractionType.class);
        endTurn.setInteractionType(interactionType);

        this.combatRoundManager.executeInteraction(campaign, endTurn);
    }

    @Test
    public void merisielMeleeAttacksValerosHitTest()
            throws OperationNotSupportedException {
        final Campaign campaign = this.campaignFactory.newEntity();
        final DndCharacters participants = new DndCharacters();

        this.initiateCombatRound(campaign, participants);

        // put new roll results into the mocked dice roll manager for attack
        // and damage!
        when(this.mockDiceRollManager.rollDice((DiceAction) anyObject()))
                .thenReturn(new DiceRoll(16L), new DiceRoll(4L));

        this.executeMeleeSingleAttack(campaign);

        final DndCharacter valeros = (DndCharacter) this.entityPoolService
                .getEntityById(new UniqueId("valeros"));

        assertEquals(6L, valeros.getCurrentStaticHp().getValue());
    }

    @Test
    public void merisielMeleeAttacksValerosMissTest()
            throws OperationNotSupportedException {
        final Campaign campaign = this.campaignFactory.newEntity();
        final DndCharacters participants = new DndCharacters();

        this.initiateCombatRound(campaign, participants);

        // put new roll results into the mocked dice roll manager for attack
        // and damage!
        when(this.mockDiceRollManager.rollDice((DiceAction) anyObject()))
                .thenReturn(new DiceRoll(10L));

        this.executeMeleeSingleAttack(campaign);

        final DndCharacter valeros = (DndCharacter) this.entityPoolService
                .getEntityById(new UniqueId("valeros"));

        assertEquals(11L, valeros.getCurrentStaticHp().getValue());
    }

    @Test
    public void merisielMeleeAttacksValerosCriticalHitTest()
            throws OperationNotSupportedException {
        final Campaign campaign = this.campaignFactory.newEntity();
        final DndCharacters participants = new DndCharacters();

        this.initiateCombatRound(campaign, participants);

        // put new roll results into the mocked dice roll manager for attack
        // and damage!
        when(this.mockDiceRollManager.rollDice((DiceAction) anyObject()))
                .thenReturn(new DiceRoll(19L), new DiceRoll(16L),
                        new DiceRoll(4L), new DiceRoll(3L));

        this.executeMeleeSingleAttack(campaign);

        final DndCharacter valeros = (DndCharacter) this.entityPoolService
                .getEntityById(new UniqueId("valeros"));

        assertEquals(2L, valeros.getCurrentStaticHp().getValue());
    }

    @Test
    public void merisielMeleeAttacksValerosNearlyCriticalHitTest()
            throws OperationNotSupportedException {
        final Campaign campaign = this.campaignFactory.newEntity();
        final DndCharacters participants = new DndCharacters();

        this.initiateCombatRound(campaign, participants);

        // put new roll results into the mocked dice roll manager for attack
        // and damage!
        when(this.mockDiceRollManager.rollDice((DiceAction) anyObject()))
                .thenReturn(new DiceRoll(19L), new DiceRoll(2L),
                        new DiceRoll(3L));

        this.executeMeleeSingleAttack(campaign);

        final DndCharacter valeros = (DndCharacter) this.entityPoolService
                .getEntityById(new UniqueId("valeros"));

        assertEquals(7L, valeros.getCurrentStaticHp().getValue());
    }

    @Test
    public void merisielMeleeAttacksValerosEndTurnTest()
            throws OperationNotSupportedException {
        final Campaign campaign = this.campaignFactory.newEntity();
        final DndCharacters participants = new DndCharacters();

        this.initiateCombatRound(campaign, participants);

        // put new roll results into the mocked dice roll manager for attack
        // and damage!
        when(this.mockDiceRollManager.rollDice((DiceAction) anyObject()))
                .thenReturn(new DiceRoll(16L), new DiceRoll(4L));

        this.executeMeleeSingleAttack(campaign);
        this.executeEndTurn(campaign);

        assertEquals("valeros", campaign.getCombatRound()
                .getCurrentParticipantId().getValue());
    }

    @Test
    public void merisielMeleeAttacksValerosEndTurnLoseFlatfootedTest()
            throws OperationNotSupportedException {
        final Campaign campaign = this.campaignFactory.newEntity();
        final DndCharacters participants = new DndCharacters();

        this.initiateCombatRound(campaign, participants);

        // put new roll results into the mocked dice roll manager for attack
        // and damage!
        when(this.mockDiceRollManager.rollDice((DiceAction) anyObject()))
                .thenReturn(new DiceRoll(16L), new DiceRoll(4L));

        this.executeMeleeSingleAttack(campaign);
        this.executeEndTurn(campaign);

        final DndCharacter valeros = (DndCharacter) this.entityPoolService
                .getEntityById(new UniqueId("valeros"));

        assertEquals(0L, Iterators.size(valeros.getConditions().iterator()));
    }

}
