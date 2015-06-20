package org.asciicerebrum.mydndgame.integrationtests.interactions;

import javax.naming.OperationNotSupportedException;
import org.asciicerebrum.mydndgame.domain.core.particles.DiceRoll;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.factories.ArmorFactory;
import org.asciicerebrum.mydndgame.domain.factories.CampaignFactory;
import org.asciicerebrum.mydndgame.domain.factories.DndCharacterFactory;
import org.asciicerebrum.mydndgame.domain.factories.WeaponFactory;
import org.asciicerebrum.mydndgame.domain.game.Campaign;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.DndCharacters;
import org.asciicerebrum.mydndgame.domain.mechanics.workflow.Interaction;
import org.asciicerebrum.mydndgame.domain.mechanics.workflow.InteractionType;
import org.asciicerebrum.mydndgame.domain.ruleentities.DiceAction;
import org.asciicerebrum.mydndgame.integrationtests.pool.dndCharacters.HarskDwarfFighter2;
import org.asciicerebrum.mydndgame.integrationtests.pool.dndCharacters.MerisielElfRogue1;
import org.asciicerebrum.mydndgame.integrationtests.pool.dndCharacters.ValerosHumanFighter1;
import org.asciicerebrum.mydndgame.integrationtests.pool.inventoryItems.armors.MwkChainmail;
import org.asciicerebrum.mydndgame.integrationtests.pool.inventoryItems.armors.MwkHeavySteelShield;
import org.asciicerebrum.mydndgame.integrationtests.pool.inventoryItems.armors.StandardLightWoodenShield;
import org.asciicerebrum.mydndgame.integrationtests.pool.inventoryItems.armors.StandardStuddedLeather;
import org.asciicerebrum.mydndgame.integrationtests.pool.inventoryItems.weapons.MwkBastardsword;
import org.asciicerebrum.mydndgame.integrationtests.pool.inventoryItems.weapons.MwkRapier;
import org.asciicerebrum.mydndgame.integrationtests.pool.inventoryItems.weapons.StandardBattleaxe;
import org.asciicerebrum.mydndgame.integrationtests.pool.inventoryItems.weapons.StandardLongsword;
import org.asciicerebrum.mydndgame.mechanics.managers.CombatRoundManager;
import org.asciicerebrum.mydndgame.mechanics.managers.DefaultRollResultManager;
import org.asciicerebrum.mydndgame.mechanics.managers.DiceRollManager;
import org.asciicerebrum.mydndgame.mechanics.managers.RollResultManager;
import org.asciicerebrum.mydndgame.services.context.EntityPoolService;
import org.asciicerebrum.mydndgame.testcategories.IntegrationTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
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

    //TODO test attack misses
    //TODO test attack hits normally
    //TODO test attack hits critically
    
    @Test
    public void merisielMeleeAttacksValerosTest()
            throws OperationNotSupportedException {
        final Campaign campaign = this.campaignFactory.newEntity();
        final DndCharacters participants = new DndCharacters();

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

        // put new roll results into the mocked dice roll manager for attack
        // and damage!
        when(this.mockDiceRollManager.rollDice((DiceAction) anyObject()))
                .thenReturn(new DiceRoll(16L), new DiceRoll(4L));
        
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

}
