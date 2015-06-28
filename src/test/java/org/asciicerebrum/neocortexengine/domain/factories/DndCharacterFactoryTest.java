package org.asciicerebrum.neocortexengine.domain.factories;

import com.google.common.collect.Iterators;
import java.util.ArrayList;
import org.asciicerebrum.neocortexengine.domain.core.particles.AttackAbility;
import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueId;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.game.StateRegistry;
import org.asciicerebrum.neocortexengine.domain.ruleentities.BodySlot;
import org.asciicerebrum.neocortexengine.domain.ruleentities.BodySlotType;
import org.asciicerebrum.neocortexengine.domain.ruleentities.BodySlots;
import org.asciicerebrum.neocortexengine.domain.ruleentities.Race;
import org.asciicerebrum.neocortexengine.domain.ruleentities.composition.BaseAbilityEntry;
import org.asciicerebrum.neocortexengine.domain.ruleentities.composition.Condition;
import org.asciicerebrum.neocortexengine.domain.ruleentities.composition.LevelAdvancement;
import org.asciicerebrum.neocortexengine.domain.setup.BaseAbilityEntrySetup;
import org.asciicerebrum.neocortexengine.domain.setup.CharacterSetup;
import org.asciicerebrum.neocortexengine.domain.setup.ConditionSetup;
import org.asciicerebrum.neocortexengine.domain.setup.EntitySetup;
import org.asciicerebrum.neocortexengine.domain.setup.LevelAdvancementSetup;
import org.asciicerebrum.neocortexengine.domain.setup.PersonalizedBodySlotSetup;
import org.asciicerebrum.neocortexengine.domain.setup.SetupIncompleteException;
import org.asciicerebrum.neocortexengine.domain.setup.WorldDateSetup;
import org.asciicerebrum.neocortexengine.infrastructure.ApplicationContextProvider;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
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
        this.stateRegistryFactory = mock(EntityFactory.class);
        this.baseAbilityEntryFactory = mock(EntityFactory.class);
        this.conditionFactory = mock(EntityFactory.class);
        this.resultCharacter = new DndCharacter();

        ApplicationContextProvider ctxProvider
                = new ApplicationContextProvider();
        ctxProvider.setApplicationContext(this.applicationContext);
        this.factory.setLevelAdvancementFactory(this.levelAdvancementFactory);
        this.factory.setStateRegistryFactory(this.stateRegistryFactory);
        this.factory.setBaseAbilityEntryFactory(this.baseAbilityEntryFactory);
        this.factory.setConditionFactory(this.conditionFactory);

        when(this.applicationContext.getBean(DndCharacter.class))
                .thenReturn(this.resultCharacter);
        when(this.baseAbilityEntryFactory.newEntity((EntitySetup) anyObject()))
                .thenReturn(new BaseAbilityEntry());
    }

    @After
    public void tearDown() {
    }

    @Test(expected = SetupIncompleteException.class)
    public void newEntityIncompleteTest() {
        final CharacterSetup setup = new CharacterSetup();

        this.factory.newEntity(setup);
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
        bodySlotSetup.setIsPrimaryAttackSlot("true");

        lvlAdvSetup.setAdvancementNumber("1");
        lvlAdvSetup.setHpAdvancement("0");
        lvlAdvSetup.setClassLevel("1");

        baseAbilityEntrySetup.setAbility("abilityId");
        baseAbilityEntrySetup.setAbilityValue("12");

        final Race race = new Race();
        final BodySlots raceBodySlots = new BodySlots();
        final BodySlot raceBodySlot = new BodySlot();
        final BodySlotType raceBodySlotType = new BodySlotType();
        raceBodySlotType.setId(new UniqueId("slotTypeId"));
        raceBodySlot.setBodySlotType(raceBodySlotType);
        raceBodySlot.setIsPrimaryAttackSlot(new AttackAbility(true));
        raceBodySlots.add(raceBodySlot);
        race.setBodySlotBluePrint(raceBodySlots);
        when(this.applicationContext.getBean("raceId", Race.class))
                .thenReturn(race);
        when(this.levelAdvancementFactory.newEntity(eq(lvlAdvSetup)))
                .thenReturn(new LevelAdvancement());
        when(this.baseAbilityEntryFactory.newEntity(eq(baseAbilityEntrySetup)))
                .thenReturn(new BaseAbilityEntry());
    }

    @Test
    public void newEntityCompleteUniqueIdTest() {
        final CharacterSetup setup = new CharacterSetup();

        this.makeComplete(setup);

        final DndCharacter result
                = this.factory.newEntity(setup);

        assertEquals("uniqueId", result.getUniqueId().getValue());
    }

    @Test
    public void newEntityCompleteHpTest() {
        final CharacterSetup setup = new CharacterSetup();

        this.makeComplete(setup);

        final DndCharacter result
                = this.factory.newEntity(setup);

        assertEquals(25L, result.getCurrentStaticHp().getValue());
    }

    @Test
    public void newEntityBodySlotItemTest() {
        final CharacterSetup setup = new CharacterSetup();

        this.makeComplete(setup);

        final DndCharacter result
                = this.factory.newEntity(setup);

        assertEquals("slotTypeId", result.getPersonalizedBodySlots()
                .iterator().next().getBodySlotType().getId().getValue());
    }

    @Test
    public void newEntityBodySlotItemCountTest() {
        final CharacterSetup setup = new CharacterSetup();

        this.makeComplete(setup);

        final DndCharacter result
                = this.factory.newEntity(setup);

        assertEquals(1L, Iterators.size(result.getPersonalizedBodySlots()
                .iterator()));
    }

    @Test
    public void newEntityLevelAdvancementCountTest() {
        final CharacterSetup setup = new CharacterSetup();
        this.makeComplete(setup);

        final DndCharacter result
                = this.factory.newEntity(setup);

        assertEquals(1L, Iterators.size(result.getLevelAdvancements()
                .iterator()));
    }

    @Test
    public void newEntityBaseAbilitiesCountTest() {
        final CharacterSetup setup = new CharacterSetup();

        this.makeComplete(setup);

        final DndCharacter result
                = this.factory.newEntity(setup);

        assertEquals(1L, Iterators.size(result.getBaseAbilities()
                .abilityIterator()));
    }

    @Test
    public void newEntityZeroNonlethalHitPointsTest() {
        final CharacterSetup setup = new CharacterSetup();

        this.makeComplete(setup);

        final DndCharacter result
                = this.factory.newEntity(setup);

        assertEquals(0L, result.getCurrentStaticHpNonLethal().getValue());
    }

    @Test
    public void newEntityWithConditionsTest() {
        final CharacterSetup setup = new CharacterSetup();

        this.makeComplete(setup);
        final ConditionSetup conditionSetup = new ConditionSetup();
        setup.setConditionSetups(new ArrayList<EntitySetup>() {
            {
                this.add(conditionSetup);
            }
        });
        final WorldDateSetup dateSetup = new WorldDateSetup();
        dateSetup.setCombatRoundNumber("1");
        dateSetup.setCombatRoundPosition("1");
        conditionSetup.setConditionType("conditionTypeId");
        conditionSetup.setStartingDate(dateSetup);
        conditionSetup.setExpiryDate(dateSetup);

        final DndCharacter result
                = this.factory.newEntity(setup);

        assertEquals(1L, Iterators.size(result.getConditions().iterator()));
    }

}
