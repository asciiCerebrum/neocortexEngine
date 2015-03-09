package org.asciicerebrum.mydndgame.domain.factories;

import java.util.ArrayList;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.StateRegistry;
import org.asciicerebrum.mydndgame.domain.ruleentities.BodySlots;
import org.asciicerebrum.mydndgame.domain.ruleentities.Race;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.BaseAbilityEntry;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.Condition;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.LevelAdvancement;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.PersonalizedBodySlot;
import org.asciicerebrum.mydndgame.domain.setup.BaseAbilityEntrySetup;
import org.asciicerebrum.mydndgame.domain.setup.CharacterSetup;
import org.asciicerebrum.mydndgame.domain.setup.EntitySetup;
import org.asciicerebrum.mydndgame.domain.setup.LevelAdvancementSetup;
import org.asciicerebrum.mydndgame.domain.setup.PersonalizedBodySlotSetup;
import org.asciicerebrum.mydndgame.domain.setup.SetupIncompleteException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *
 * @author species8472
 */
public class DndCharacterFactoryTest {

    private DndCharacterFactory factory;

    private ApplicationContext applicationContext;

    private EntityFactory<LevelAdvancement> levelAdvancementFactory;

    private EntityFactory<PersonalizedBodySlot> bodySlotFactory;

    private EntityFactory<StateRegistry> stateRegistryFactory;

    private EntityFactory<BaseAbilityEntry> baseAbilityEntryFactory;

    private EntityFactory<Condition> conditionFactory;

    private DndCharacter resultCharacter;

    public DndCharacterFactoryTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.factory = new DndCharacterFactory();
        this.applicationContext = mock(ApplicationContext.class,
                withSettings()
                .extraInterfaces(ConfigurableApplicationContext.class));
        this.levelAdvancementFactory = mock(EntityFactory.class);
        this.bodySlotFactory = mock(EntityFactory.class);
        this.stateRegistryFactory = mock(EntityFactory.class);
        this.baseAbilityEntryFactory = mock(EntityFactory.class);
        this.conditionFactory = mock(EntityFactory.class);
        this.resultCharacter = new DndCharacter();

        this.factory.setContext(this.applicationContext);
        this.factory.setLevelAdvancementFactory(this.levelAdvancementFactory);
        this.factory.setBodySlotFactory(this.bodySlotFactory);
        this.factory.setStateRegistryFactory(this.stateRegistryFactory);
        this.factory.setBaseAbilityEntryFactory(this.baseAbilityEntryFactory);
        this.factory.setConditionFactory(this.conditionFactory);

        when(this.applicationContext.getBean(DndCharacter.class))
                .thenReturn(this.resultCharacter);
        when(this.baseAbilityEntryFactory.newEntity((EntitySetup) anyObject(),
                (Reassignments) anyObject()))
                .thenReturn(new BaseAbilityEntry());
    }

    @After
    public void tearDown() {
    }

    @Test(expected = SetupIncompleteException.class)
    public void newEntityIncompleteTest() {
        final CharacterSetup setup = new CharacterSetup();
        final Reassignments reassignments = new Reassignments();

        this.factory.newEntity(setup, reassignments);
    }

    private void makeComplete(CharacterSetup setup) {
        final PersonalizedBodySlotSetup bodySlotSetup
                = new PersonalizedBodySlotSetup();
        final LevelAdvancementSetup lvlAdvSetup
                = new LevelAdvancementSetup();
        final BaseAbilityEntrySetup baseAbilityEntrySetup
                = new BaseAbilityEntrySetup();

        setup.setRace("raceId");
        setup.setUniqueId("uniqueId");
        setup.setCurrentHp("25");
        setup.setCurrentXp("605");
        setup.setBodySlotSetups(new ArrayList<EntitySetup>() {
            {
                this.add(bodySlotSetup);
            }
        });
        setup.setLevelAdvancementSetups(new ArrayList<EntitySetup>() {
            {
                this.add(lvlAdvSetup);
            }
        });
        setup.setBaseAbilitySetups(new ArrayList<EntitySetup>() {
            {
                this.add(baseAbilityEntrySetup);
            }
        });

        bodySlotSetup.setBodySlotType("slotTypeId");
        bodySlotSetup.setItem("slotItemId");

        lvlAdvSetup.setAdvancementNumber("1");
        lvlAdvSetup.setHpAdvancement("0");
        lvlAdvSetup.setClassLevel("1");

        baseAbilityEntrySetup.setAbility("abilityId");
        baseAbilityEntrySetup.setAbilityValue("12");

        final Race race = new Race();
        race.setBodySlotBluePrint(new BodySlots());
        when(this.applicationContext.getBean("raceId", Race.class))
                .thenReturn(race);
    }

    @Test
    public void newEntityCompleteUniqueIdTest() {
        final CharacterSetup setup = new CharacterSetup();
        final Reassignments reassignments = new Reassignments();

        this.makeComplete(setup);

        final DndCharacter result
                = this.factory.newEntity(setup, reassignments);

        assertEquals("uniqueId", result.getUniqueId().getValue());
    }

    @Test
    public void newEntityCompleteHpTest() {
        final CharacterSetup setup = new CharacterSetup();
        final Reassignments reassignments = new Reassignments();

        this.makeComplete(setup);

        final DndCharacter result
                = this.factory.newEntity(setup, reassignments);

        assertEquals(25L, result.getCurrentStaticHp().getValue());
    }

}
