package org.asciicerebrum.mydndgame;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
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

        CharacterSetup setup = new CharacterSetup();
        DndCharacter character = new DndCharacter();

        setup.setRace("someRace");
        setup.getBaseAbilityMap().put("someAbility", 10L);
        setup.getLevelAdvancementStack().add(new LevelAdvancement("someClass",
                5L, null, "someFeat"));

        Race race = new Race();
        race.setId(setup.getRace());
        BodySlotType bsType = new BodySlotType();
        bsType.setId("someBodySlotType");
        race.getProvidedBodySlotTypes().add(bsType);
        CharacterClass chClass = new CharacterClass();
        chClass.setId("someClass");
        ClassLevel cLevel = new ClassLevel();
        cLevel.setCharacterClass(chClass);
        cLevel.setLevel(1);
        chClass.getClassLevels().add(cLevel);
        Ability ability = new Ability();
        ability.setId("someAbility");
        Feat feat = new Feat();
        feat.setId("someFeat");

        when(this.context.getBean("dndCharacter", DndCharacter.class))
                .thenReturn(character);
        when(this.context.getBean(race.getId(), Race.class)).thenReturn(race);
        when(this.context.getBean(chClass.getId(), CharacterClass.class)).thenReturn(chClass);
        when(this.context.getBean(ability.getId(), Ability.class)).thenReturn(ability);
        when(this.context.getBean(feat.getId(), Feat.class)).thenReturn(feat);

        this.dndCharacterBuilder = new DndCharacterBuilder(setup, this.context);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testBuildBodySlotsCount() {

        DndCharacter character = this.dndCharacterBuilder.build();

        assertEquals(1L, character.getBodySlots().size());
    }

    @Test
    public void testBuildBodySlotsFirstType() {

        DndCharacter character = this.dndCharacterBuilder.build();

        assertEquals("someBodySlotType", character.getBodySlots().get(0).getBodySlotType().getId());
    }

    @Test
    public void testBuildAbilityMapSize() {
        DndCharacter character = this.dndCharacterBuilder.build();

        assertEquals(1L, character.getAbilityMap().size());
    }

    @Test
    public void testBuildAbilityMapFirstElementId() {
        DndCharacter character = this.dndCharacterBuilder.build();

        assertEquals("someAbility", character.getAbilityMap().keySet().iterator().next().getId());
    }

    @Test
    public void testBuildAbilityMapFirstElementValue() {
        DndCharacter character = this.dndCharacterBuilder.build();

        assertEquals(Long.valueOf(10), character.getAbilityMap().values().iterator().next());
    }

    @Test
    public void testBuildFeatsSize() {
        DndCharacter character = this.dndCharacterBuilder.build();

        assertEquals(1L, character.getFeats().size());
    }

    @Test
    public void testBuildClassListSize() {
        DndCharacter character = this.dndCharacterBuilder.build();

        assertEquals(1L, character.getClassList().size());
    }

    @Test
    public void testBuildClassLevelsSize() {
        DndCharacter character = this.dndCharacterBuilder.build();

        assertEquals(1L, character.getClassLevels().size());
    }

    @Test
    public void testBuildClassListFirstValue() {
        DndCharacter character = this.dndCharacterBuilder.build();

        assertEquals("someClass", character.getClassList().get(0).getId());
    }

    @Test
    public void testBuildHpAdditionsSize() {
        DndCharacter character = this.dndCharacterBuilder.build();

        assertEquals(1L, character.getHpAdditionList().size());
    }

    @Test
    public void testBuildHpAdditionsFirstValue() {
        DndCharacter character = this.dndCharacterBuilder.build();

        assertEquals(Long.valueOf(5), character.getHpAdditionList().get(0));
    }

    //TODO test possessions
}
