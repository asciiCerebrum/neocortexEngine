package org.asciicerebrum.mydndgame;

import java.util.List;
import org.asciicerebrum.mydndgame.interfaces.entities.IAbility;
import org.asciicerebrum.mydndgame.interfaces.entities.IArmor;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.IInventoryItem;
import org.asciicerebrum.mydndgame.interfaces.entities.IWeapon;
import org.asciicerebrum.mydndgame.interfaces.entities.IWeaponCategory;
import org.asciicerebrum.mydndgame.testcategories.IntegrationTest;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author species8472
 */
@Category(IntegrationTest.class)
public class CharacterCreationIntegrationTest {

    private static final String CLASS_FIGHTER = "fighter";
    private static final String FEAT_WEAPON_FINESSE = "weaponFinesse";
    private static final String AXE4HARSK_ID = "battleAxeHarsk";
    private static final String BASTARD4HARSK_ID = "bastardSwordHarsk";
    private static final String RAPIER4VALEROS_ID = "rapierValeros";
    private static final String DAGGER4VALEROS_ID = "mwkRapierValeros";
    private static final String PRIMARY_HAND_TYPE = "primaryHand";
    private static final String TORSO_TYPE = "torso";
    private static final String SECONDARY_HAND_TYPE = "secondaryHand";
    private static final String CHAINMAIL4VALEROS_ID = "chainmailValeros";
    private static final String STUDDEDLEATHER4HARSK_ID = "studdedLeatherHarsk";

    private ICharacter harsk;
    private ICharacter valeros;

    private BodySlotType primaryHand;
    private BodySlotType secondaryHand;
    private BodySlotType torso;

    private IWeaponCategory meleeAttackMode;
    private IWeaponCategory rangedAttackMode;

    private ApplicationContext context;

    @Before
    public void setUp() {
        this.context = new ClassPathXmlApplicationContext(
                new String[]{"applicationContext.xml"});

        // instance of bean factory to add new beans programmatically, e.g.
        // all the weapons!
        ConfigurableListableBeanFactory beanFactory
                = ((ConfigurableApplicationContext) this.context)
                .getBeanFactory();

        WeaponSetup axe4HarskSetup = new WeaponSetup();
        axe4HarskSetup.setId(AXE4HARSK_ID);
        axe4HarskSetup.setName("battleaxe");
        axe4HarskSetup.setSizeCategory("medium");
        IWeapon axe4Harsk
                = new WeaponBuilder(axe4HarskSetup, this.context).build();

        WeaponSetup bastard4HarskSetup = new WeaponSetup();
        bastard4HarskSetup.setId(BASTARD4HARSK_ID);
        bastard4HarskSetup.setName("bastardsword");
        bastard4HarskSetup.setSizeCategory("medium");
        IWeapon bastard4Harsk
                = new WeaponBuilder(bastard4HarskSetup, this.context).build();

        ArmorSetup studdedLeather4HarskSetup = new ArmorSetup();
        studdedLeather4HarskSetup.setId(STUDDEDLEATHER4HARSK_ID);
        studdedLeather4HarskSetup.setName("studdedLeather");
        studdedLeather4HarskSetup.setSizeCategory("medium");
        IArmor studdedLeather4Harsk = new ArmorBuilder(
                studdedLeather4HarskSetup, this.context).build();

        WeaponSetup rapier4ValerosSetup = new WeaponSetup();
        rapier4ValerosSetup.setId(RAPIER4VALEROS_ID);
        rapier4ValerosSetup.setName("rapier");
        rapier4ValerosSetup.setSizeCategory("medium");
        IWeapon rapier4Valeros
                = new WeaponBuilder(rapier4ValerosSetup, this.context).build();

        WeaponSetup dagger4ValerosSetup = new WeaponSetup();
        dagger4ValerosSetup.setId(DAGGER4VALEROS_ID);
        dagger4ValerosSetup.setName("dagger");
        dagger4ValerosSetup.setSizeCategory("medium");
        dagger4ValerosSetup.getSpecialAbilities().add("masterworkWeapon");
        IWeapon dagger4Valeros
                = new WeaponBuilder(dagger4ValerosSetup, this.context).build();

        ArmorSetup chainmail4ValerosSetup = new ArmorSetup();
        chainmail4ValerosSetup.setId(CHAINMAIL4VALEROS_ID);
        chainmail4ValerosSetup.setName("chainmail");
        chainmail4ValerosSetup.setSizeCategory("medium");
        chainmail4ValerosSetup.getSpecialAbilities().add("masterworkArmor");
        IArmor chainmail4Valeros = new ArmorBuilder(chainmail4ValerosSetup,
                this.context).build();

        beanFactory.registerSingleton(AXE4HARSK_ID, axe4Harsk);
        beanFactory.registerSingleton(BASTARD4HARSK_ID, bastard4Harsk);
        beanFactory.registerSingleton(RAPIER4VALEROS_ID, rapier4Valeros);
        beanFactory.registerSingleton(DAGGER4VALEROS_ID, dagger4Valeros);
        beanFactory.registerSingleton(CHAINMAIL4VALEROS_ID, chainmail4Valeros);
        beanFactory.registerSingleton(STUDDEDLEATHER4HARSK_ID,
                studdedLeather4Harsk);

        CharacterSetup setupHarsk = new CharacterSetup(this.context);
        setupHarsk.setName("Harsk");
        setupHarsk.setRace("dwarf");
        setupHarsk.getBaseAbilityMap().put("str", 14L);
        setupHarsk.getBaseAbilityMap().put("dex", 11L);
        setupHarsk.getBaseAbilityMap().put("con", 13L);
        setupHarsk.getBaseAbilityMap().put("int", 10L);
        setupHarsk.getBaseAbilityMap().put("wis", 10L);
        setupHarsk.getBaseAbilityMap().put("cha", 8L);
        setupHarsk.getLevelAdvancementStack().add(
                new LevelAdvancement()
                .setClassName(CLASS_FIGHTER)
                .setFeatName("powerAttack"));
        setupHarsk.getLevelAdvancementStack().add(
                new LevelAdvancement()
                .setClassName(CLASS_FIGHTER)
                .setHpAddition(7L));
        setupHarsk.getPossessionContainer().put(AXE4HARSK_ID, PRIMARY_HAND_TYPE);
        setupHarsk.getPossessionContainer().put(BASTARD4HARSK_ID, "secondaryHand");
        setupHarsk.getPossessionContainer().put(STUDDEDLEATHER4HARSK_ID, "torso");
        setupHarsk.getStateRegistry().put("powerAttackValue", 1L); // first parameter is the stateKey, which is defined in the feat bean, as well as the type of the value - here Long

        CharacterSetup setupValeros = new CharacterSetup(this.context);
        setupValeros.setName("Valeros");
        setupValeros.setRace("human");
        setupValeros.getBaseAbilityMap().put("str", 14L);
        setupValeros.getBaseAbilityMap().put("dex", 9L);
        setupValeros.getBaseAbilityMap().put("con", 14L);
        setupValeros.getBaseAbilityMap().put("int", 9L);
        setupValeros.getBaseAbilityMap().put("wis", 11L);
        setupValeros.getBaseAbilityMap().put("cha", 10L);
        setupValeros.getLevelAdvancementStack().add(
                new LevelAdvancement()
                .setClassName(CLASS_FIGHTER)
                .setFeatName(FEAT_WEAPON_FINESSE));
        setupValeros.getPossessionContainer().put(RAPIER4VALEROS_ID, PRIMARY_HAND_TYPE);
        setupValeros.getPossessionContainer().put(DAGGER4VALEROS_ID, "secondaryHand");
        setupValeros.getPossessionContainer().put(CHAINMAIL4VALEROS_ID, "torso");
        setupValeros.getStateRegistry().put("weaponFinesseMode." + RAPIER4VALEROS_ID, "true"); // this must not be directly saved at the weapon; stateKey and value type Weapon (or String?)
        setupValeros.getStateRegistry().put("weaponFinesseMode." + DAGGER4VALEROS_ID, "true");
        setupValeros.getStateRegistry().put("weaponDamageType." + DAGGER4VALEROS_ID, "slashing");

        this.primaryHand = this.context.getBean(PRIMARY_HAND_TYPE,
                BodySlotType.class);
        this.secondaryHand = this.context.getBean(SECONDARY_HAND_TYPE,
                BodySlotType.class);
        this.torso = this.context.getBean(TORSO_TYPE, BodySlotType.class);

        this.meleeAttackMode = this.context.getBean("meleeWeapon", WeaponCategory.class);
        this.rangedAttackMode = this.context.getBean("rangedWeapon", WeaponCategory.class);

        this.harsk
                = new DndCharacterBuilder(setupHarsk, this.context).build();
        this.valeros
                = new DndCharacterBuilder(setupValeros, this.context).build();
    }

    @Test
    public void harskMaxHp() {
        assertEquals(Long.valueOf(19), this.harsk.getMaxHp());
    }

    @Test
    public void harskBaseAtk1() {
        assertEquals(Long.valueOf(2),
                this.harsk.getBaseAtkBoni().get(0).getDynamicValueProvider()
                .getDynamicValue(this.harsk));
    }

    @Test
    public void harskBaseAtkLength() {
        assertEquals(1, this.harsk.getBaseAtkBoni().size());
    }

    //TODO test rank ordered bonus list of a size of at least 2.
    @Test
    public void harskAC() {
        // dex: +0
        // studded leather: +3
        assertEquals(Long.valueOf(13), this.harsk.getAcStandard());
    }

    @Test
    public void harskACFlatFooted() {
        // studded leather: +3
        assertEquals(Long.valueOf(13), this.harsk.getAcFlatFooted());
    }

    @Test
    public void harskACTouch() {
        // studded leather not applied
        assertEquals(Long.valueOf(10), this.harsk.getAcTouch());
    }

    @Test
    public void harskACWithShield() {
        // dex: +0
        // studded leather: +3
        // heavy steel shield: +2
        ArmorSetup shield4HarskSetup = new ArmorSetup();
        shield4HarskSetup.setId("shield4Harsk");
        shield4HarskSetup.setName("heavySteelShield");
        shield4HarskSetup.setSizeCategory("medium");
        IArmor shield4Harsk = new ArmorBuilder(
                shield4HarskSetup, this.context).build();

        this.harsk.getBodySlotByType(this.secondaryHand).setItem(shield4Harsk);

        assertEquals(Long.valueOf(15), this.harsk.getAcStandard());
    }

    @Test
    public void harskACWithShieldInBothHands() {
        // dex: +0
        // studded leather: +3
        // heavy steel shield: +2
        ArmorSetup shield4HarskSetup = new ArmorSetup();
        shield4HarskSetup.setId("shield4Harsk");
        shield4HarskSetup.setName("heavySteelShield");
        shield4HarskSetup.setSizeCategory("medium");
        IArmor shield4Harsk = new ArmorBuilder(
                shield4HarskSetup, this.context).build();

        this.harsk.getBodySlotByType(this.primaryHand).setItem(shield4Harsk);
        this.harsk.getBodySlotByType(this.secondaryHand).setItem(shield4Harsk);

        assertEquals(Long.valueOf(15), this.harsk.getAcStandard());
    }

    @Test
    public void harskACWithShieldInWrongSlot() {
        // dex: +0
        // studded leather: +3 --> replaced by shield, so not applied
        // heavy steel shield: +2 --> is on torso, so not applied
        ArmorSetup shield4HarskSetup = new ArmorSetup();
        shield4HarskSetup.setId("shield4Harsk");
        shield4HarskSetup.setName("heavySteelShield");
        shield4HarskSetup.setSizeCategory("medium");
        IArmor shield4Harsk = new ArmorBuilder(
                shield4HarskSetup, this.context).build();

        this.harsk.getBodySlotByType(this.torso).setItem(shield4Harsk);

        assertEquals(Long.valueOf(10), this.harsk.getAcStandard());
    }

    @Test
    public void harskACFlatFootedWithShield() {
        // studded leather: +3
        // heavy steel shield: +2
        ArmorSetup shield4HarskSetup = new ArmorSetup();
        shield4HarskSetup.setId("shield4Harsk");
        shield4HarskSetup.setName("heavySteelShield");
        shield4HarskSetup.setSizeCategory("medium");
        IArmor shield4Harsk = new ArmorBuilder(
                shield4HarskSetup, this.context).build();

        this.harsk.getBodySlotByType(this.secondaryHand).setItem(shield4Harsk);

        assertEquals(Long.valueOf(15), this.harsk.getAcFlatFooted());
    }

    @Test
    public void harskACTouchWithShield() {
        // studded leather not applied
        // heavy steel shield not applied
        ArmorSetup shield4HarskSetup = new ArmorSetup();
        shield4HarskSetup.setId("shield4Harsk");
        shield4HarskSetup.setName("heavySteelShield");
        shield4HarskSetup.setSizeCategory("medium");
        IArmor shield4Harsk = new ArmorBuilder(
                shield4HarskSetup, this.context).build();

        this.harsk.getBodySlotByType(this.secondaryHand).setItem(shield4Harsk);

        assertEquals(Long.valueOf(10), this.harsk.getAcTouch());
    }

    @Test
    public void valerosMaxHp() {
        assertEquals(Long.valueOf(12), this.valeros.getMaxHp());
    }

    @Test
    public void valerosMaxAttackNumber() {
        assertEquals(Long.valueOf(1), this.valeros.getMaxAttackNumber());
    }

    @Test
    public void valerosBaseAtk1() {
        assertEquals(Long.valueOf(1),
                this.valeros.getBaseAtkBoni().get(0).getDynamicValueProvider()
                .getDynamicValue(this.valeros));
    }

    @Test
    public void valerosBaseAtkLength() {
        assertEquals(1, this.valeros.getBaseAtkBoni().size());
    }

    @Test
    public void valerosAC() {
        // dex: -1
        // chainmail: +5
        assertEquals(Long.valueOf(14), this.valeros.getAcStandard());
    }

    @Test
    public void valerosACFlatFooted() {
        // dex: -1 -> stays when flat footed!
        // chainmail: +5
        assertEquals(Long.valueOf(14), this.valeros.getAcFlatFooted());
    }

    @Test
    public void valerosACTouch() {
        // dex: -1
        // chainmail: +5 -> not active when touched
        assertEquals(Long.valueOf(9), this.valeros.getAcTouch());
    }

    @Test
    public void valerosChainmailCost() {
        // mwk chainmail!

        Long cost = this.valeros.getBodySlotByType(this.torso)
                .getItem().getCost();

        assertEquals(Long.valueOf(300), cost);
    }

    @Test
    public void valerosFeatNumber() {
        // 8 from class: simpleWeaponProficiency, martialWeaponProficiency,
        // lightArmorProficiency, mediumArmorProficiency, heavyArmorProficiency,
        // lightShieldProficiency, heavyShieldProficiency,
        // towerShieldProficiency
        // 1 from character: weaponFinesse
        assertEquals(9, this.valeros.getFeats().size());
    }

    @Test
    public void valeros3rdFeatName() {
        // feat 0 till 7 come from the class, feat 8 from the character.
        assertEquals(FEAT_WEAPON_FINESSE,
                this.valeros.getFeats().get(8).getId());
    }

    @Test
    public void valerosPrimaryHandItemName() {
        assertEquals(RAPIER4VALEROS_ID,
                this.valeros.getBodySlots().get(0).getItem().getId());
    }

    @Test
    public void valerosMeleeAtkBonusFirstValue() {
        List<Long> meleeAtkBoni
                = this.valeros.getAtkBoni(this.primaryHand,
                        this.meleeAttackMode);

        // base atk of fighter lvl 1: 1
        // dex 9 instead of str 12 due to weapon finesse: -1
        assertEquals(Long.valueOf(0), meleeAtkBoni.get(0));
    }

    @Test
    public void valerosMeleeAtkBonusFirstValueSecHand() {
        List<Long> meleeAtkBoni
                = this.valeros.getAtkBoni(this.secondaryHand,
                        this.meleeAttackMode);

        // base atk of fighter lvl 1: 1
        // dex 9 instead of str 12 due to weapon finesse: -1
        // mwk dagger: 1
        assertEquals(Long.valueOf(1), meleeAtkBoni.get(0));
    }

    @Test
    public void valerosMwkDaggerCost() {
        IInventoryItem mwkDagger
                = this.valeros.getBodySlotByType(this.secondaryHand).getItem();
        assertEquals(Long.valueOf(302), mwkDagger.getCost());
    }

    @Test
    public void harskMeleeAtkBonusFirstValue() {
        List<Long> meleeAtkBoni
                = this.harsk.getAtkBoni(this.primaryHand, this.meleeAttackMode);

        // base atk of fighter lvl 2
        // str 14 -> +2
        // power attack 1 -> -1
        assertEquals(Long.valueOf(3), meleeAtkBoni.get(0));
    }

    @Test
    public void valerosRangedAtkBonusFirstValue() {
        List<Long> meleeAtkBoni
                = this.valeros.getAtkBoni(this.primaryHand,
                        this.rangedAttackMode);

        // base atk of fighter lvl 1: 1
        // dex 9 instead of str 12 due to weapon finesse: -1
        // improvised weapon: rapier is not for throwing,
        // so you gain a nonproficiency penalty: -4
        assertEquals(Long.valueOf(-4), meleeAtkBoni.get(0));
    }

    @Test
    public void valerosRangedAtkBonusFirstValueSecHand() {
        List<Long> meleeAtkBoni
                = this.valeros.getAtkBoni(this.secondaryHand,
                        this.rangedAttackMode);

        // base atk of fighter lvl 1: 1
        // dex 9: -1
        // mwk dagger: 1
        // dagger is also good for ranged so no changes here.
        assertEquals(Long.valueOf(1), meleeAtkBoni.get(0));
    }

    @Test
    public void harskMeleeAtkBonusFirstValueSecHand() {
        List<Long> meleeAtkBoni
                = this.harsk.getAtkBoni(this.secondaryHand,
                        this.meleeAttackMode);

        // base atk of fighter lvl 2
        // str 14 -> +2
        // power attack 1 -> -1
        // exotic weapon -> nonproficiencyPenalty: -4
        assertEquals(Long.valueOf(-1), meleeAtkBoni.get(0));
    }

    @Test
    public void valerosMeleeDamageBonusPrimHand() {
        Long damageBonus = this.valeros.getDamageBonus(this.primaryHand,
                this.meleeAttackMode);

        // str-bonus +2
        assertEquals(Long.valueOf(2), damageBonus);
    }

    @Test
    public void valerosMeleeDamageBonusSecHand() {
        Long damageBonus = this.valeros.getDamageBonus(this.secondaryHand,
                this.meleeAttackMode);

        // str-bonus +2 but off-hand: +1 (1/2!)
        assertEquals(Long.valueOf(1), damageBonus);
    }

    @Test
    public void harskMeleeDamageBonusPrimHand() {
        Long damageBonus = this.harsk.getDamageBonus(this.primaryHand,
                this.meleeAttackMode);

        // str-bonus -> +2
        // power attack 1 -> +1
        assertEquals(Long.valueOf(3), damageBonus);
    }

    @Test
    public void harskMeleeDamageBonusSecHand() {
        Long damageBonus = this.harsk.getDamageBonus(this.secondaryHand,
                this.meleeAttackMode);

        // str-bonus +2 but off-hand: +1
        // power attack 1 -> +1
        assertEquals(Long.valueOf(2), damageBonus);
    }

    /**
     * test damage bonus of rapier in two hands (1.5 Str bonus does NOT apply).
     */
    @Test
    public void valerosMeleeDamageBonusBothHandsRapier() {
        //TODO create API to easily sitch position of items.
        this.valeros.getBodySlotByType(this.secondaryHand).setItem(
                this.valeros.getBodySlotByType(this.primaryHand).getItem()
        );
        Long damageBonus = this.valeros.getDamageBonus(this.primaryHand,
                this.meleeAttackMode);

        // str-bonus +2
        assertEquals(Long.valueOf(2), damageBonus);
    }

    /**
     * test damage bonus of battle axe in two hands (1.5 Str bonus applies).
     */
    @Test
    public void harskMeleeDamageBonusBothHandsBattleAxe() {
        this.harsk.getBodySlotByType(this.secondaryHand).setItem(
                this.harsk.getBodySlotByType(this.primaryHand).getItem()
        );

        Long damageBonus = this.harsk.getDamageBonus(this.primaryHand,
                this.meleeAttackMode);

        // str-bonus -> +2 (*1.5 both hands) -> +3
        // power attack 1 -> +1 (*2 both hands) -> +2
        assertEquals(Long.valueOf(5), damageBonus);
    }

    @Test
    public void valerosRangedDamageBonusBow() {

        WeaponSetup bowSetup = new WeaponSetup();
        bowSetup.setId("bowValeros");
        bowSetup.setName("longbow");
        bowSetup.setSizeCategory("medium");
        IWeapon bowValeros
                = new WeaponBuilder(bowSetup, this.context).build();

        this.valeros.getBodySlotByType(this.primaryHand).setItem(bowValeros);
        this.valeros.getBodySlotByType(this.secondaryHand).setItem(bowValeros);

        Long damageBonus = this.valeros.getDamageBonus(this.primaryHand,
                this.rangedAttackMode);

        // str-bonus not applied!
        assertEquals(Long.valueOf(0), damageBonus);
    }

    /**
     * test attack with bow in ranged mode: str penalty applies (use weak
     * character with negative str bonus).
     */
    @Test
    public void valerosWeakRangedDamageBonusBow() {

        WeaponSetup bowSetup = new WeaponSetup();
        bowSetup.setId("bowValeros");
        bowSetup.setName("longbow");
        bowSetup.setSizeCategory("medium");
        IWeapon bowValeros
                = new WeaponBuilder(bowSetup, this.context).build();

        this.valeros.getBodySlotByType(this.primaryHand).setItem(bowValeros);
        this.valeros.getBodySlotByType(this.secondaryHand).setItem(bowValeros);

        IAbility str = this.context.getBean("str", Ability.class);
        this.valeros.getBaseAbilityMap().put(str, 8L); // --> bonus -1

        Long damageBonus = this.valeros.getDamageBonus(this.primaryHand,
                this.rangedAttackMode);

        // str-bonus: -1 is applied!
        assertEquals(Long.valueOf(-1), damageBonus);
    }

    /**
     * test attack with bow in melee mode: str bonus applies normal (use strong
     * character with positive str bonus).
     */
    @Test
    public void valerosWeakMeleeDamageBonusBow() {

        WeaponSetup bowSetup = new WeaponSetup();
        bowSetup.setId("bowValeros");
        bowSetup.setName("longbow");
        bowSetup.setSizeCategory("medium");
        IWeapon bowValeros
                = new WeaponBuilder(bowSetup, this.context).build();

        this.valeros.getBodySlotByType(this.primaryHand).setItem(bowValeros);
        this.valeros.getBodySlotByType(this.secondaryHand).setItem(bowValeros);

        Long damageBonus = this.valeros.getDamageBonus(this.primaryHand,
                this.meleeAttackMode);

        // str-bonus: +2 is applied! (*1.5 both hands)
        assertEquals(Long.valueOf(3), damageBonus);
    }

    //TODO test what happens when wearing two shields!!! the penalties on attack
    // should not stack!
    
    //TODO test if situation context invalidation works: get damage bonus for
    // ranged directly before damage bonus for melee. There should be no
    // difference to when you only get damage bonus for melee!!!
}
