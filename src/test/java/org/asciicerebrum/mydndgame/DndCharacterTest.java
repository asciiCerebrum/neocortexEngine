package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.interfaces.services.BonusCalculationService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.asciicerebrum.mydndgame.interfaces.entities.IAbility;
import org.asciicerebrum.mydndgame.interfaces.entities.IArmor;
import org.asciicerebrum.mydndgame.interfaces.entities.IBodySlotType;
import org.asciicerebrum.mydndgame.interfaces.entities.IBonus;
import org.asciicerebrum.mydndgame.interfaces.entities.IClass;
import org.asciicerebrum.mydndgame.interfaces.entities.IConditionType;
import org.asciicerebrum.mydndgame.interfaces.entities.IDiceAction;
import org.asciicerebrum.mydndgame.interfaces.entities.IObserver;
import org.asciicerebrum.mydndgame.interfaces.entities.ISituationContext;
import org.asciicerebrum.mydndgame.interfaces.entities.IWeapon;
import org.asciicerebrum.mydndgame.interfaces.entities.ObserverHook;
import org.asciicerebrum.mydndgame.interfaces.entities.Slotable;
import org.asciicerebrum.mydndgame.interfaces.observing.ObservableDelegate;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author species8472
 */
public class DndCharacterTest {

    @Mock
    private BonusCalculationService bcService;

    @Mock
    private ObservableDelegate observableDelegate;

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
                new LevelAdvancement()
                .setClassName("characterClass"));
        setup.getLevelAdvancementStack().add(
                new LevelAdvancement()
                .setClassName("characterClass")
                .setHpAddition(ADDITIONAL_HP));
        setup.getLevelAdvancementStack().add(
                new LevelAdvancement()
                .setClassName("otherCharacterClass"));

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

        when(this.bcService.retrieveEffectiveBonusValueByTarget(
                (ISituationContext) anyObject(), eq(this.testChar), eq(hp)))
                .thenReturn(ADDITIONAL_HP);

        this.testChar.setHp(hp);
        this.testChar.setBaseAttackBonus(baseAtkBonusType);
        this.testChar.getHpAdditionList().add(Long.valueOf(HIT_DICE));
        this.testChar.getHpAdditionList().add(ADDITIONAL_HP);
        this.testChar.setBonusService(this.bcService);
        this.testChar.setObservableDelegate(this.observableDelegate);
        this.testChar.getBaseAtkBoni().add(baseAtkBonus2_1);
        this.testChar.getBaseAtkBoni().add(baseAtkBonus2_2);
        this.testChar.getClassLevels().add(cLevel1);
        this.testChar.getClassLevels().add(cLevel2);
        this.testChar.setMeleeAttackAction(new DiceAction());
        this.testChar.setRangedAttackAction(new DiceAction());

        IDiceAction touchAttackAction = mock(IDiceAction.class);
        IObserver touchObserver = mock(IObserver.class);
        List<IObserver> touchObservers = new ArrayList<IObserver>();
        touchObservers.add(touchObserver);
        when(touchAttackAction.getTargetObservers()).thenReturn(touchObservers);
        this.testChar.setTouchAttackAction(touchAttackAction);

        IConditionType flatFootedCondition = mock(IConditionType.class);
        IObserver flatFootedObserver = mock(IObserver.class);
        List<IObserver> flatFootedObservers = new ArrayList<IObserver>();
        flatFootedObservers.add(flatFootedObserver);
        when(flatFootedCondition.getObservers())
                .thenReturn(flatFootedObservers);
        this.testChar.setFlatFooted(flatFootedCondition);

        DiceAction acBaseAction = new DiceAction();
        acBaseAction.setConstValue(10L);

        this.testChar.setAcBaseAction(acBaseAction);
        this.testChar.setAcAction(new DiceAction());

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

    @Test
    public void testGetAbilityMod() {
        IAbility ability = mock(IAbility.class);
        when(ability.getAssociatedHook()).thenReturn(ObserverHook.ABILITY_STR);
        when(this.observableDelegate.triggerObservers(
                eq(ObserverHook.ABILITY_STR),
                eq(1L), eq(this.testChar.getObserverMap()),
                (ISituationContext) anyObject())).thenReturn(1L);

        this.testChar.getBaseAbilityMap().put(ability, 12L);
        this.testChar.setAbilityBonusOffset(10);

        Long abilityMod = this.testChar.getAbilityMod(ability);

        assertEquals(Long.valueOf(1L), abilityMod);
    }

    @Test
    public void testGetAbilityModNegative() {
        IAbility ability = mock(IAbility.class);
        when(ability.getAssociatedHook()).thenReturn(ObserverHook.ABILITY_STR);
        when(this.observableDelegate.triggerObservers(
                eq(ObserverHook.ABILITY_STR),
                eq(-2L), eq(this.testChar.getObserverMap()),
                (ISituationContext) anyObject())).thenReturn(-2L);

        this.testChar.getBaseAbilityMap().put(ability, 6L);
        this.testChar.setAbilityBonusOffset(10);

        Long abilityMod = this.testChar.getAbilityMod(ability);

        assertEquals(Long.valueOf(-2L), abilityMod);
    }

    @Test
    public void testGetWieldedArmorSize() {
        Slotable armorSlot = mock(Slotable.class);
        IArmor armor = mock(IArmor.class);
        when(armor.isCorrectlyWielded((IBodySlotType) anyObject()))
                .thenReturn(Boolean.TRUE);
        when(armorSlot.getItem()).thenReturn(armor);

        Slotable weaponSlot = mock(Slotable.class);
        IWeapon weapon = mock(IWeapon.class);
        when(weapon.isCorrectlyWielded((IBodySlotType) anyObject()))
                .thenReturn(Boolean.TRUE);
        when(weaponSlot.getItem()).thenReturn(weapon);

        this.testChar.getBodySlots().add(armorSlot);
        this.testChar.getBodySlots().add(weaponSlot);

        List<IArmor> wieldedArmor = this.testChar.getWieldedArmor();

        assertEquals(1, wieldedArmor.size());
    }

    @Test
    public void testGetWieldedArmorCorrectArmor() {
        Slotable armorSlot = mock(Slotable.class);
        IArmor armor = mock(IArmor.class);
        when(armor.isCorrectlyWielded((IBodySlotType) anyObject()))
                .thenReturn(Boolean.TRUE);
        when(armorSlot.getItem()).thenReturn(armor);

        Slotable weaponSlot = mock(Slotable.class);
        IWeapon weapon = mock(IWeapon.class);
        when(weapon.isCorrectlyWielded((IBodySlotType) anyObject()))
                .thenReturn(Boolean.TRUE);
        when(weaponSlot.getItem()).thenReturn(weapon);

        this.testChar.getBodySlots().add(armorSlot);
        this.testChar.getBodySlots().add(weaponSlot);

        List<IArmor> wieldedArmor = this.testChar.getWieldedArmor();

        assertEquals(armor, wieldedArmor.get(0));
    }

    @Test
    public void testGetWieldedArmorEmptySlot() {
        Slotable armorSlot = mock(Slotable.class);
        IArmor armor = mock(IArmor.class);
        when(armor.isCorrectlyWielded((IBodySlotType) anyObject()))
                .thenReturn(Boolean.TRUE);
        when(armorSlot.getItem()).thenReturn(armor);

        Slotable weaponSlot = mock(Slotable.class);
        when(weaponSlot.getItem()).thenReturn(null);

        this.testChar.getBodySlots().add(armorSlot);
        this.testChar.getBodySlots().add(weaponSlot);

        List<IArmor> wieldedArmor = this.testChar.getWieldedArmor();

        assertEquals(armor, wieldedArmor.get(0));
    }

    @Test
    public void testGetWieldedArmorArmorInBothSlots() {
        Slotable armorSlot = mock(Slotable.class);
        IArmor armor = mock(IArmor.class);
        when(armor.isCorrectlyWielded((IBodySlotType) anyObject()))
                .thenReturn(Boolean.TRUE);
        when(armorSlot.getItem()).thenReturn(armor);

        Slotable weaponSlot = mock(Slotable.class);
        when(weaponSlot.getItem()).thenReturn(armor);

        this.testChar.getBodySlots().add(armorSlot);
        this.testChar.getBodySlots().add(weaponSlot);

        List<IArmor> wieldedArmor = this.testChar.getWieldedArmor();

        assertEquals(1, wieldedArmor.size());
    }

    @Test
    public void testGetWieldedArmorNotWieldedCorrectly() {
        Slotable armorSlot = mock(Slotable.class);
        IArmor armor = mock(IArmor.class);
        when(armor.isCorrectlyWielded((IBodySlotType) anyObject()))
                .thenReturn(Boolean.FALSE);
        when(armorSlot.getItem()).thenReturn(armor);

        Slotable weaponSlot = mock(Slotable.class);
        IWeapon weapon = mock(IWeapon.class);
        when(weapon.isCorrectlyWielded((IBodySlotType) anyObject()))
                .thenReturn(Boolean.TRUE);
        when(weaponSlot.getItem()).thenReturn(weapon);

        this.testChar.getBodySlots().add(armorSlot);
        this.testChar.getBodySlots().add(weaponSlot);

        List<IArmor> wieldedArmor = this.testChar.getWieldedArmor();

        assertEquals(0, wieldedArmor.size());
    }

    @Test
    public void testGetMeleeDamageBonus() {
        IWeapon weapon = mock(IWeapon.class);
        ObservableDelegate obsDel = mock(ObservableDelegate.class);
        when(weapon.getObservableDelegate()).thenReturn(obsDel);
        this.testChar.getBodySlotByType(this.bodySlot)
                .setItem(weapon);
        Long dmgBon = this.testChar.getMeleeDamageBonus(this.bodySlot);

        assertEquals(Long.valueOf(0L), dmgBon);
    }

    @Test
    public void testGetRangedDamageBonus() {
        IWeapon weapon = mock(IWeapon.class);
        ObservableDelegate obsDel = mock(ObservableDelegate.class);
        when(weapon.getObservableDelegate()).thenReturn(obsDel);
        this.testChar.getBodySlotByType(this.bodySlot)
                .setItem(weapon);
        Long dmgBon = this.testChar.getRangedDamageBonus(this.bodySlot);

        assertEquals(Long.valueOf(0L), dmgBon);
    }

    @Test
    public void testGetAc() {
        Long ac = this.testChar.getAc();

        assertEquals(Long.valueOf(10L), ac);
    }

    @Test
    public void testGetAcMethodCallsWithoutArmor() {
        this.testChar.getAc();

        verify(this.bcService, times(2)).traverseBoniByTarget(eq(this.testChar),
                (DiceAction) anyObject());
    }

    @Test
    public void testGetAcTriggerMethodCallsWithoutArmor() {
        this.testChar.getAc();

        verify(this.observableDelegate, times(2)).triggerObservers(
                (ObserverHook) anyObject(), anyObject(),
                (Map<ObserverHook, List<IObserver>>) anyObject(),
                (ISituationContext) anyObject());
    }

    @Test
    public void testGetAcMethodCallsWithArmor() {

        IArmor armor = mock(IArmor.class);
        ObservableDelegate obsDel = mock(ObservableDelegate.class);
        when(armor.getObservableDelegate()).thenReturn(obsDel);
        when(armor.isCorrectlyWielded(this.bodySlot)).thenReturn(Boolean.TRUE);
        this.testChar.getBodySlotByType(this.bodySlot)
                .setItem(armor);

        this.testChar.getAc();

        verify(this.bcService, times(2)).traverseBoniByTarget(eq(armor),
                (DiceAction) anyObject());
    }

    @Test
    public void testGetAcTriggerMethodCallsWithArmor() {

        IArmor armor = mock(IArmor.class);
        ObservableDelegate obsDel = mock(ObservableDelegate.class);
        when(armor.getObservableDelegate()).thenReturn(obsDel);
        when(armor.isCorrectlyWielded(this.bodySlot)).thenReturn(Boolean.TRUE);
        this.testChar.getBodySlotByType(this.bodySlot)
                .setItem(armor);

        this.testChar.getAc();

        verify(obsDel, times(2)).triggerObservers(
                (ObserverHook) anyObject(), anyObject(),
                (Map<ObserverHook, List<IObserver>>) anyObject(),
                (ISituationContext) anyObject());
    }

    @Test
    public void testGetAcTouch() {
        Long acTouch = this.testChar.getAcTouch();

        assertEquals(Long.valueOf(10L), acTouch);
    }

    @Test
    public void testGetAcTouchObserverRegistration() {
        this.testChar.getAcTouch();

        verify(this.observableDelegate, times(1)).registerListener(
                eq(ObserverHook.AC_BASE),
                (IObserver) anyObject(),
                (Map<ObserverHook, List<IObserver>>) anyObject());
    }

    @Test
    public void testGetAcTouchObserverUnregistration() {
        this.testChar.getAcTouch();

        verify(this.observableDelegate, times(1)).unregisterListener(
                eq(ObserverHook.AC_BASE),
                (IObserver) anyObject(),
                (Map<ObserverHook, List<IObserver>>) anyObject());
    }

    @Test
    public void testGetAcFlatFooted() {
        Long acTouch = this.testChar.getAcFlatFooted();

        assertEquals(Long.valueOf(10L), acTouch);
    }

    @Test
    public void testGetAcFlatFootedObserverRegistration() {
        this.testChar.getAcFlatFooted();

        verify(this.observableDelegate, times(1)).registerListener(
                eq(ObserverHook.AC_BASE),
                (IObserver) anyObject(),
                (Map<ObserverHook, List<IObserver>>) anyObject());
    }

    @Test
    public void testGetAcFlatFootedObserverUnregistration() {
        this.testChar.getAcFlatFooted();

        verify(this.observableDelegate, times(1)).unregisterListener(
                eq(ObserverHook.AC_BASE),
                (IObserver) anyObject(),
                (Map<ObserverHook, List<IObserver>>) anyObject());
    }

    @Test
    public void testGetAcStandardWithArmor() {
        IArmor armor = mock(IArmor.class);
        ObservableDelegate obsDel = mock(ObservableDelegate.class);
        when(armor.getObservableDelegate()).thenReturn(obsDel);
        when(armor.isCorrectlyWielded(this.bodySlot)).thenReturn(Boolean.TRUE);
        this.testChar.getBodySlotByType(this.bodySlot)
                .setItem(armor);

        Long acStandard = this.testChar.getAcStandard();

        assertEquals(Long.valueOf(10L), acStandard);
    }

    @Test
    public void testGetAcStandardWithArmorCallOfAcBaseTraversal() {
        IArmor armor = mock(IArmor.class);
        ObservableDelegate obsDel = mock(ObservableDelegate.class);
        when(armor.getObservableDelegate()).thenReturn(obsDel);
        when(armor.isCorrectlyWielded(this.bodySlot)).thenReturn(Boolean.TRUE);
        this.testChar.getBodySlotByType(this.bodySlot)
                .setItem(armor);

        this.testChar.getAcStandard();

        verify(this.bcService).traverseBoniByTarget(eq(armor),
                eq(this.testChar.getAcBaseAction()));
    }

    @Test
    public void testGetAcStandardWithArmorNonCallOfAcHook() {
        IArmor armor = mock(IArmor.class);
        ObservableDelegate obsDel = mock(ObservableDelegate.class);
        when(armor.getObservableDelegate()).thenReturn(obsDel);
        when(armor.isCorrectlyWielded(this.bodySlot)).thenReturn(Boolean.TRUE);
        this.testChar.getBodySlotByType(this.bodySlot)
                .setItem(armor);

        this.testChar.getAcStandard();

        verify(obsDel, times(0)).triggerObservers(
                eq(ObserverHook.AC),
                anyObject(),
                (Map<ObserverHook, List<IObserver>>) anyObject(),
                (ISituationContext) anyObject());
    }

}
