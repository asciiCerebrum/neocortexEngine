package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.interfaces.services.BonusCalculationService;
import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.mydndgame.interfaces.entities.IBodySlotType;
import org.asciicerebrum.mydndgame.interfaces.entities.IBonus;
import org.asciicerebrum.mydndgame.interfaces.entities.IClass;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author species8472
 */
public class DndCharacterTest {
    
    @Mock
    private BonusCalculationService bcService;
    
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
        final CharacterClass chClass = new CharacterClass();
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
        List<IBodySlotType> bodySlots = new ArrayList<IBodySlotType>();
        bodySlots.add(this.bodySlot);
        
        ClassLevel cLevel2 = new ClassLevel();
        cLevel2.setLevel(2);
        cLevel2.getBaseAtkBoni().add(baseAtkBonus2_1);
        cLevel2.getBaseAtkBoni().add(baseAtkBonus2_2);
        chClass.getClassLevels().add(cLevel2);
        Race race = new Race();
        race.setSize(new SizeCategory());
        race.setProvidedBodySlotTypes(bodySlots);
        
        final CharacterClass otherChClass = new CharacterClass();
        otherChClass.setHitDice(hitDice);
        ClassLevel otherCLevel1 = new ClassLevel();
        otherCLevel1.setLevel(1);
        otherChClass.getClassLevels().add(otherCLevel1);
        
        this.testChar = new DndCharacter();
        this.testChar.setClassList(new ArrayList<IClass>() {
            {
                this.add(chClass);
                this.add(otherChClass);
            }
        });
        
        DiceAction hp = new DiceAction();
        
        when(this.bcService.retrieveEffectiveBonusValueByTarget(this.testChar,
                this.testChar, hp)).thenReturn(ADDITIONAL_HP);
        
        this.testChar.setHp(hp);
        this.testChar.setBaseAttackBonus(baseAtkBonusType);
        this.testChar.getHpAdditionList().add(Long.valueOf(HIT_DICE));
        this.testChar.getHpAdditionList().add(ADDITIONAL_HP);
        this.testChar.setBonusService(this.bcService);
        this.testChar.getBoni().add(baseAtkBonus2_1);
        this.testChar.getBoni().add(baseAtkBonus2_2);
        this.testChar.getClassLevels().add(cLevel1);
        this.testChar.getClassLevels().add(cLevel2);
        
        BodySlot rawBodySlot = new BodySlot();
        rawBodySlot.setBodySlotType(this.bodySlot);
        this.testChar.getBodySlots().add(rawBodySlot);
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

    @Test
    public void testGetMaxHpFromClassHitDice() {
        this.testChar.getHpAdditionList().set(0, null);
        Long maxHp = this.testChar.getMaxHp();
        
        assertEquals(Long.valueOf(HIT_DICE + ADDITIONAL_HP + HIT_DICE), maxHp);
    }
    
    /**
     * Testing the size of the baseAtk boni.
     */
    @Test
    public void testGetBaseAtkBoni() {
        List<IBonus> baseAtkBoni = this.testChar.getBaseAtkBoni();
        
        assertEquals(2, baseAtkBoni.size());
    }

    /**
     * Testing the value of the first base atk bonus.
     */
    @Test
    public void testGetBaseAtkBoniValue() {
        List<IBonus> baseAtkBoni = this.testChar.getBaseAtkBoni();
        
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
        
        List<IBonus> baseAtkBoni = this.testChar.getBaseAtkBoni();
        
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
    
    @Test
    public void testGetMaxAttackNumber() {
        this.testChar.getClassLevels().get(0).getBaseAtkBoni().clear();
        Long maxAttackNumber = this.testChar.getMaxAttackNumber();
        
        assertEquals(Long.valueOf(2), maxAttackNumber);
    }
    
}
