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
import org.asciicerebrum.mydndgame.domain.ruleentities.DiceAction;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.Condition;
import org.asciicerebrum.mydndgame.domain.setup.BaseAbilityEntrySetup;
import org.asciicerebrum.mydndgame.domain.setup.CharacterSetup;
import org.asciicerebrum.mydndgame.domain.setup.SetupProperty;
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
import static org.junit.Assert.assertEquals;
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
public class InitializeCombatRoundWorkflowIntegrationTest {

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

    @Test
    public void initiateCombatRoundSimpleTest()
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

        /*TODO
         develop a dice roll history instance that saves a list of the following:
         - who? (harsk)
         - which dice? (3W6)
         - result? (11)
         - added total bonus? (+3)
         - for what? (damage)
         - further details? (against valeros)
         - when? (the current worlddate of the roll)
         a console log is done while this list is filled
        
         then the roll history can be reconstructed and the dice roll manager
         random generator can be replaced by a given list of dice roll results
         to enforce desired outcomes for better testing!
        
         develop a replacement of SecureRandom with the same interface!
        
         Then manipulate the dice rolls in such a way this tests has the desired
         outcome of merisiel being the first participant.
        
         Then make another test that requires reroll after a tie in initiative!
        
         it must be saved and persisted at the campaign! with its on factory
         and setup classes and domain model!
        
         service class with event listener: whenever a roll is recorded, all
         registered events are triggered
         - one such event listener is the logging event listener which writes
         those information to the console and/or log file.
         */
        assertEquals("merisiel", campaign.getCombatRound()
                .getCurrentParticipantId().getValue());
    }

    @Test
    public void initiateCombatRoundSameResultsTest()
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
                .thenReturn(new DiceRoll(5L), new DiceRoll(17L),
                        new DiceRoll(14L));

        this.combatRoundManager.initiateCombatRound(campaign, participants);

        assertEquals("valeros", campaign.getCombatRound()
                .getCurrentParticipantId().getValue());
    }

    @Test
    public void initiateCombatRoundSameResultsSameBoniTest()
            throws OperationNotSupportedException {
        final Campaign campaign = this.campaignFactory.newEntity();
        final DndCharacters participants = new DndCharacters();

        // giving harsk a little more dexterity to be equal with merisiel
        final BaseAbilityEntrySetup dexSetup = new BaseAbilityEntrySetup();
        dexSetup.setAbility("dex");
        dexSetup.setAbilityValue("16");

        final CharacterSetup setup = HarskDwarfFighter2.getSetup();
        setup.getPropertySetups(SetupProperty.BASE_ABILITY_ENTRIES)
                .add(dexSetup);

        this.entityPoolService.registerUniqueEntity(this.dndCharacterFactory
                .newEntity(setup));

        participants.addDndCharacter((DndCharacter) this.entityPoolService
                .getEntityById(new UniqueId("harsk")));
        participants.addDndCharacter((DndCharacter) this.entityPoolService
                .getEntityById(new UniqueId("merisiel")));
        participants.addDndCharacter((DndCharacter) this.entityPoolService
                .getEntityById(new UniqueId("valeros")));

        // giving additional rolls to resolve the tie between harsk and merisiel
        when(this.mockDiceRollManager.rollDice((DiceAction) anyObject()))
                .thenReturn(new DiceRoll(12L), new DiceRoll(12L),
                        new DiceRoll(1L), new DiceRoll(1L), new DiceRoll(2L));

        this.combatRoundManager.initiateCombatRound(campaign, participants);

        assertEquals("merisiel", campaign.getCombatRound()
                .getCurrentParticipantId().getValue());
    }

    @Test
    public void initiateCombatRoundFlatFootedTest()
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

        final DndCharacter merisiel = (DndCharacter) this.entityPoolService
                .getEntityById(campaign.getCombatRound()
                        .getCurrentParticipantId());

        assertEquals("flatFooted", merisiel.getConditions().iterator().next()
                .getConditionType().getUniqueId().getValue());
    }

    @Test
    public void initiateCombatRoundFlatFootedExpiryPositionTest()
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

        final DndCharacter merisiel = (DndCharacter) this.entityPoolService
                .getEntityById(campaign.getCombatRound()
                        .getCurrentParticipantId());

        final Condition flatFooted = merisiel.getConditions().iterator().next();

        assertEquals("023003", flatFooted.getExpiryDate()
                .getCombatRoundPosition().getValue());
    }

    @Test
    public void initiateCombatRoundFlatFootedExpiryRoundTest()
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

        final DndCharacter merisiel = (DndCharacter) this.entityPoolService
                .getEntityById(campaign.getCombatRound()
                        .getCurrentParticipantId());

        final Condition flatFooted = merisiel.getConditions().iterator().next();

        assertEquals(0L, flatFooted.getExpiryDate()
                .getCombatRoundNumber().getValue());
    }

}
