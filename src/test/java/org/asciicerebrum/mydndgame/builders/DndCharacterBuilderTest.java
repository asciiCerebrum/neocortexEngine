package org.asciicerebrum.mydndgame.builders;

import org.asciicerebrum.mydndgame.Ability;
import org.asciicerebrum.mydndgame.BodySlotType;
import org.asciicerebrum.mydndgame.CharacterClass;
import org.asciicerebrum.mydndgame.CharacterSetup;
import org.asciicerebrum.mydndgame.ClassLevel;
import org.asciicerebrum.mydndgame.DndCharacter;
import org.asciicerebrum.mydndgame.Feat;
import org.asciicerebrum.mydndgame.InventoryItem;
import org.asciicerebrum.mydndgame.LevelAdvancement;
import org.asciicerebrum.mydndgame.Race;
import org.asciicerebrum.mydndgame.Weapon;
import org.asciicerebrum.mydndgame.builders.DndCharacterBuilder;
import org.asciicerebrum.mydndgame.interfaces.entities.BonusTarget;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.IFeat;
import org.asciicerebrum.mydndgame.interfaces.entities.IObserver;
import org.asciicerebrum.mydndgame.interfaces.entities.ObserverHook;
import org.asciicerebrum.mydndgame.interfaces.observing.ObservableDelegate;
import org.asciicerebrum.mydndgame.interfaces.services.BonusCalculationService;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author species8472
 */
public class DndCharacterBuilderTest {

    @Mock
    private ApplicationContext context;

    private DndCharacterBuilder dndCharacterBuilder;

    private BodySlotType bsType;

    private CharacterSetup setup;

    private ObservableDelegate observableDelegate;

    private IObserver characterObserver;

    private IObserver featObserver;

    private DndCharacter character;

    private BonusCalculationService bcService;

    public DndCharacterBuilderTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        this.setup = new CharacterSetup(this.context);
        this.character = new DndCharacter();

        this.observableDelegate = mock(ObservableDelegate.class);
        this.character.setObservableDelegate(this.observableDelegate);

        this.characterObserver = mock(IObserver.class);
        when(this.characterObserver.getHook()).thenReturn(ObserverHook.AC);
        this.character.getObservers().add(this.characterObserver);

        this.bcService = mock(BonusCalculationService.class);
        when(this.bcService.retrieveEffectiveBonusValueByTarget(
                eq(this.character),
                eq(this.character),
                (BonusTarget) anyObject())).thenReturn(0L);
        this.character.setBonusService(this.bcService);

        this.setup.setRace("someRace");
        this.setup.getBaseAbilityMap().put("someAbility", 10L);
        this.setup.getLevelAdvancementStack().add(
                new LevelAdvancement()
                .setClassName("someClass")
                .setHpAddition(5L)
                .setFeatName("someFeat")
                .setAbilityName("someAbility"));
        this.setup.getPossessionContainer().put("someItem", "someBodySlotType");

        Race race = new Race();
        race.setId(this.setup.getRace());
        this.bsType = new BodySlotType();
        this.bsType.setId("someBodySlotType");
        race.getProvidedBodySlotTypes().add(this.bsType);
        CharacterClass chClass = new CharacterClass();
        chClass.setId("someClass");
        ClassLevel cLevel = new ClassLevel();
        cLevel.setLevel(1);
        chClass.getClassLevels().add(cLevel);
        Ability ability = new Ability();
        ability.setId("someAbility");
        Feat feat = new Feat();
        feat.setId("someFeat");

        this.featObserver = mock(IObserver.class);
        when(this.featObserver.getHook()).thenReturn(ObserverHook.DAMAGE);
        feat.getObservers().add(this.featObserver);

        Weapon weapon = new Weapon();
        weapon.setId("someItem");

        IFeat classFeat = mock(IFeat.class);
        chClass.getClassFeats().add(classFeat);

        when(this.context.getBean("dndCharacter", DndCharacter.class))
                .thenReturn(this.character);
        when(this.context.getBean(race.getId(), Race.class)).thenReturn(race);
        when(this.context.getBean(chClass.getId(), CharacterClass.class))
                .thenReturn(chClass);
        when(this.context.getBean(ability.getId(), Ability.class))
                .thenReturn(ability);
        when(this.context.getBean(feat.getId(), Feat.class)).thenReturn(feat);
        when(this.context.getBean(this.bsType.getId(), BodySlotType.class))
                .thenReturn(this.bsType);
        when(this.context.getBean(weapon.getId(), InventoryItem.class))
                .thenReturn(weapon);

        this.dndCharacterBuilder = new DndCharacterBuilder(this.setup, this.context);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testBuildBodySlotsCount() {

        ICharacter newCharacter = this.dndCharacterBuilder.build();

        assertEquals(1L, newCharacter.getBodySlots().size());
    }

    @Test
    public void testBuildBodySlotsFirstType() {

        ICharacter newCharacter = this.dndCharacterBuilder.build();

        assertEquals("someBodySlotType", newCharacter.getBodySlots().get(0).getBodySlotType().getId());
    }

    @Test
    public void testBuildAbilityMapSize() {
        ICharacter newCharacter = this.dndCharacterBuilder.build();

        assertEquals(1L, newCharacter.getBaseAbilityMap().size());
    }

    @Test
    public void testBuildAbilityMapFirstElementId() {
        ICharacter newCharacter = this.dndCharacterBuilder.build();

        assertEquals("someAbility", newCharacter.getBaseAbilityMap().keySet().iterator().next().getId());
    }

    @Test
    public void testBuildAbilityMapFirstElementValue() {
        ICharacter newCharacter = this.dndCharacterBuilder.build();

        assertEquals(Long.valueOf(10), newCharacter.getBaseAbilityMap().values().iterator().next());
    }

    @Test
    public void testBuildFeatsSize() {
        ICharacter newCharacter = this.dndCharacterBuilder.build();

        // feat of character plus feat of class
        assertEquals(2L, newCharacter.getFeats().size());
    }

    @Test
    public void testBlankFeatName() {
        this.setup.getLevelAdvancementStack().clear();
        this.setup.getLevelAdvancementStack().add(
                new LevelAdvancement()
                .setClassName("someClass")
                .setHpAddition(5L));

        ICharacter newCharacter = this.dndCharacterBuilder.build();

        // only feat of class remains
        assertEquals(1L, newCharacter.getFeats().size());
    }

    @Test
    public void testBuildClassListSize() {
        ICharacter newCharacter = this.dndCharacterBuilder.build();

        assertEquals(1L, newCharacter.getClassList().size());
    }

    @Test
    public void testBuildClassLevelsSize() {
        ICharacter newCharacter = this.dndCharacterBuilder.build();

        assertEquals(1L, newCharacter.getClassLevels().size());
    }

    @Test
    public void testBuildClassListFirstValue() {
        ICharacter newCharacter = this.dndCharacterBuilder.build();

        assertEquals("someClass", newCharacter.getClassList().get(0).getId());
    }

    @Test
    public void testBuildHpAdditionsSize() {
        ICharacter newCharacter = this.dndCharacterBuilder.build();

        assertEquals(1L, newCharacter.getHpAdditionList().size());
    }

    @Test
    public void testBuildHpAdditionsFirstValue() {
        ICharacter newCharacter = this.dndCharacterBuilder.build();

        assertEquals(Long.valueOf(5), newCharacter.getHpAdditionList().get(0));
    }

    @Test
    public void testPossessionsCorrectSlotAndId() {
        ICharacter newCharacter = this.dndCharacterBuilder.build();

        assertEquals("someItem", newCharacter.getBodySlotByType(this.bsType).getItem().getId());
    }

    @Test
    public void testPossessionsUnavailableSlotType() {
        this.setup.getPossessionContainer().clear();
        this.setup.getPossessionContainer().put("somitItem", "someUnavailableBodySlotType");

        ICharacter newCharacter = this.dndCharacterBuilder.build();
        assertNull(newCharacter.getBodySlotByType(this.bsType).getItem());
    }

    @Test
    public void testObserverRegister() {
        this.dndCharacterBuilder.build();

        verify(this.observableDelegate).registerListener(ObserverHook.AC,
                this.characterObserver, this.character.getObserverMap());
    }

    @Test
    public void testFeatObserverRegister() {
        this.dndCharacterBuilder.build();

        verify(this.observableDelegate).registerListener(ObserverHook.DAMAGE,
                this.featObserver, this.character.getObserverMap());
    }

}
