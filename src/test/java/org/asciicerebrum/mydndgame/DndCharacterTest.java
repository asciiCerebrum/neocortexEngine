package org.asciicerebrum.mydndgame;

import java.util.ArrayList;
import java.util.List;
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
public class DndCharacterTest {

    @Mock
    private ApplicationContext context;

    @Mock
    private DefaultBonusCalculationServiceImpl bcService;

    private DndCharacter testChar;
    private BodySlotType bodySlot;

    private static final Integer HIT_DICE = 10;
    private static final Long ADDITIONAL_HP = 5L;
    private static final Long BASE_ATK_BONUS = 2L;

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
        setup.setRace("characterRace");
        setup.getBaseAbilityMap().put("ability", 0L);
        setup.getLevelAdvancementStack().add(
                new LevelAdvancement("characterClass", null, null, null));
        setup.getLevelAdvancementStack().add(
                new LevelAdvancement("characterClass", ADDITIONAL_HP, null,
                        null));
        setup.getLevelAdvancementStack().add(
                new LevelAdvancement("otherCharacterClass", null, null, null));

        BonusType baseAtkBonusType = new BonusType();
        Dice hitDice = new Dice();
        hitDice.setSides(HIT_DICE);
        CharacterClass chClass = new CharacterClass();
        chClass.setHitDice(hitDice);
        chClass.setId("characterClass");
        ClassLevel cLevel1 = new ClassLevel();
        cLevel1.setLevel(1);
        Bonus baseAtkBonus = new Bonus();
        baseAtkBonus.setRank(0L);
        baseAtkBonus.setValue(BASE_ATK_BONUS);
        baseAtkBonus.setTarget(new DiceAction());
        baseAtkBonus.setBonusType(baseAtkBonusType);
        cLevel1.getBaseAtkBoni().add(baseAtkBonus);
        cLevel1.setCharacterClass(chClass);
        chClass.getClassLevels().add(cLevel1);

        Bonus baseAtkBonus2_1 = new Bonus();
        baseAtkBonus2_1.setRank(0L);
        baseAtkBonus2_1.setValue(BASE_ATK_BONUS);
        baseAtkBonus2_1.setTarget(new DiceAction());
        baseAtkBonus2_1.setBonusType(baseAtkBonusType);

        Bonus baseAtkBonus2_2 = new Bonus();
        baseAtkBonus2_2.setRank(1L);
        baseAtkBonus2_2.setValue(BASE_ATK_BONUS);
        baseAtkBonus2_2.setTarget(new DiceAction());
        baseAtkBonus2_2.setBonusType(baseAtkBonusType);

        this.bodySlot = new BodySlotType();
        this.bodySlot.setId("bodySlotType");
        List<BodySlotType> bodySlots = new ArrayList<BodySlotType>();
        bodySlots.add(this.bodySlot);
        
        ClassLevel cLevel2 = new ClassLevel();
        cLevel2.setLevel(2);
        cLevel2.setCharacterClass(chClass);
        cLevel2.getBaseAtkBoni().add(baseAtkBonus2_1);
        cLevel2.getBaseAtkBoni().add(baseAtkBonus2_2);
        chClass.getClassLevels().add(cLevel2);
        Race race = new Race();
        race.setSize(new SizeCategory());
        race.setProvidedBodySlotTypes(bodySlots);

        CharacterClass otherChClass = new CharacterClass();
        otherChClass.setHitDice(hitDice);
        ClassLevel otherCLevel1 = new ClassLevel();
        otherCLevel1.setLevel(1);
        otherCLevel1.setCharacterClass(otherChClass);
        otherChClass.getClassLevels().add(otherCLevel1);

        when(this.context.getBean("hp", DiceAction.class))
                .thenReturn(new DiceAction());
        when(this.context.getBean("characterRace", Race.class))
                .thenReturn(race);
        when(this.context.getBean("ability", Ability.class))
                .thenReturn(new Ability());
        when(this.context.getBean("characterClass", CharacterClass.class))
                .thenReturn(chClass);
        when(this.context.getBean("otherCharacterClass", CharacterClass.class))
                .thenReturn(otherChClass);
        when(this.context.getBean("baseAttackBonus", BonusType.class))
                .thenReturn(baseAtkBonusType);
        when(this.context.getBean("bonusCalculationService",
                BonusCalculationService.class))
                .thenReturn(this.bcService);

        this.testChar = new DndCharacter.Builder(setup, this.context).build();
    }

    @After
    public void tearDown() {
    }

    /**
     * Calculate max hp directly from the hitdice and an additionally given
     * bonus. The boni come from two different character classes.
     */
    @Test
    public void testGetMaxHp() {
        Long maxHp = this.testChar.getMaxHp();

        assertEquals(Long.valueOf(HIT_DICE + ADDITIONAL_HP + HIT_DICE), maxHp);
    }

    /**
     * Testing the size of the baseAtk boni.
     */
    @Test
    public void testGetBaseAtkBoni() {
        List<Bonus> baseAtkBoni = this.testChar.getBaseAtkBoni();

        assertEquals(2, baseAtkBoni.size());
    }

    /**
     * Testing the value of the first base atk bonus.
     */
    @Test
    public void testGetBaseAtkBoniValue() {
        List<Bonus> baseAtkBoni = this.testChar.getBaseAtkBoni();

        assertEquals(BASE_ATK_BONUS, baseAtkBoni.get(0).getValue());
    }

    /**
     * Testing the value of the one base atk bonus. Adding a false bonus to test
     * its exclusion.
     */
    @Test
    public void testGetBaseAtkBoniValueWithFalseBonus() {
        Bonus nonAtkBonus = new Bonus();
        BonusType nonAtkBonusType = new BonusType();
        nonAtkBonusType.setId("nonAtkBonus");
        nonAtkBonus.setBonusType(nonAtkBonusType);
        this.testChar.getBoni().add(nonAtkBonus);

        List<Bonus> baseAtkBoni = this.testChar.getBaseAtkBoni();

        assertEquals(BASE_ATK_BONUS, baseAtkBoni.get(0).getValue());
    }

    /**
     * Testing the value of the melee atk bonus.
     */
    @Test
    public void testGetMeleeAtkBonusFirstValue() {

        List<Long> boniList = this.testChar.getMeleeAtkBonus(this.bodySlot);

        assertEquals(Long.valueOf(2), boniList.get(0));
    }

}
