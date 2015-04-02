package org.asciicerebrum.mydndgame.integrationtests.dndcharacter;

import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.factories.DndCharacterFactory;
import org.asciicerebrum.mydndgame.domain.factories.InventoryItemFactory;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.setup.ArmorSetup;
import org.asciicerebrum.mydndgame.integrationtests.pool.dndCharacters.HarskDwarfFighter2;
import org.asciicerebrum.mydndgame.integrationtests.pool.dndCharacters.ValerosHumanFighter1;
import org.asciicerebrum.mydndgame.integrationtests.pool.inventoryItems.armors.StandardHalfplate;
import org.asciicerebrum.mydndgame.integrationtests.pool.inventoryItems.armors.StandardLightWoodenShield;
import org.asciicerebrum.mydndgame.integrationtests.pool.inventoryItems.armors.StandardStuddedLeather;
import org.asciicerebrum.mydndgame.integrationtests.pool.inventoryItems.weapons.StandardBattleaxe;
import org.asciicerebrum.mydndgame.integrationtests.pool.inventoryItems.weapons.StandardLongsword;
import org.asciicerebrum.mydndgame.services.context.EntityPoolService;
import org.asciicerebrum.mydndgame.testcategories.IntegrationTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
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
public class CharacterCreationIntegrationTest {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private EntityPoolService entityPoolService;

    @Autowired
    private DndCharacterFactory dndCharacterFactory;

    @Autowired
    private InventoryItemFactory inventoryItemFactory;

    @Before
    public void setUp() {

        this.entityPoolService.registerUniqueEntity(this.dndCharacterFactory
                .newEntity(ValerosHumanFighter1.getSetup()));
        this.entityPoolService.registerUniqueEntity(this.dndCharacterFactory
                .newEntity(HarskDwarfFighter2.getSetup()));

        this.entityPoolService.registerUniqueEntity(this.inventoryItemFactory
                .newEntity(StandardHalfplate.getSetup()));
        this.entityPoolService.registerUniqueEntity(this.inventoryItemFactory
                .newEntity(StandardStuddedLeather.getSetup()));
        this.entityPoolService.registerUniqueEntity(this.inventoryItemFactory
                .newEntity(StandardLongsword.getSetup()));
        this.entityPoolService.registerUniqueEntity(this.inventoryItemFactory
                .newEntity(StandardBattleaxe.getSetup()));
        this.entityPoolService.registerUniqueEntity(this.inventoryItemFactory
                .newEntity(StandardLightWoodenShield.getSetup()));
    }

    @Test
    public void harskName() {
        final String id = ((DndCharacter) this.entityPoolService.getEntityById(
                new UniqueId("harsk"))).getUniqueId().getValue();

        assertEquals("harsk", id);
    }

    @Test
    public void harskMaxHp() {
//        assertEquals(Long.valueOf(19), this.harsk.getMaxHp());
        fail();
    }

    @Test
    public void harskBaseAtk1() {
//        assertEquals(Long.valueOf(2),
//                this.harsk.getBaseAtkBoni().get(0).getDynamicValueProvider()
//                .getDynamicValue(this.harsk));
        fail();
    }

    @Test
    public void harskBaseAtkLength() {
//        assertEquals(1, this.harsk.getBaseAtkBoni().size());
        fail();
    }

    //TODO test rank ordered bonus list of a size of at least 2.
    @Test
    public void harskAC() {
        // dex: +0
        // studded leather: +3
//        assertEquals(Long.valueOf(13), this.harsk.getAcStandard());
        fail();
    }

    @Test
    public void harskACFlatFooted() {
        // studded leather: +3
//        assertEquals(Long.valueOf(13), this.harsk.getAcFlatFooted());
        fail();
    }

    @Test
    public void harskACTouch() {
        // studded leather not applied
//        assertEquals(Long.valueOf(10), this.harsk.getAcTouch());
        fail();
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
//        IArmor shield4Harsk = new ArmorFactory(
//                shield4HarskSetup, this.context).build();
//
//        this.harsk.getBodySlotByType(this.secondaryHand).setItem(shield4Harsk);
//
//        assertEquals(Long.valueOf(15), this.harsk.getAcStandard());
        fail();
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
//        IArmor shield4Harsk = new ArmorFactory(
//                shield4HarskSetup, this.context).build();
//
//        this.harsk.getBodySlotByType(this.primaryHand).setItem(shield4Harsk);
//        this.harsk.getBodySlotByType(this.secondaryHand).setItem(shield4Harsk);
//
//        assertEquals(Long.valueOf(15), this.harsk.getAcStandard());
        fail();
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
//        IArmor shield4Harsk = new ArmorFactory(
//                shield4HarskSetup, this.context).build();
//
//        this.harsk.getBodySlotByType(this.torso).setItem(shield4Harsk);
//
//        assertEquals(Long.valueOf(10), this.harsk.getAcStandard());
        fail();
    }

    @Test
    public void harskACFlatFootedWithShield() {
        // studded leather: +3
        // heavy steel shield: +2
        ArmorSetup shield4HarskSetup = new ArmorSetup();
        shield4HarskSetup.setId("shield4Harsk");
        shield4HarskSetup.setName("heavySteelShield");
        shield4HarskSetup.setSizeCategory("medium");
//        IArmor shield4Harsk = new ArmorFactory(
//                shield4HarskSetup, this.context).build();
//
//        this.harsk.getBodySlotByType(this.secondaryHand).setItem(shield4Harsk);
//
//        assertEquals(Long.valueOf(15), this.harsk.getAcFlatFooted());
        fail();
    }

    @Test
    public void harskACTouchWithShield() {
        // studded leather not applied
        // heavy steel shield not applied
        ArmorSetup shield4HarskSetup = new ArmorSetup();
        shield4HarskSetup.setId("shield4Harsk");
        shield4HarskSetup.setName("heavySteelShield");
        shield4HarskSetup.setSizeCategory("medium");
//        IArmor shield4Harsk = new ArmorFactory(
//                shield4HarskSetup, this.context).build();
//
//        this.harsk.getBodySlotByType(this.secondaryHand).setItem(shield4Harsk);
//
//        assertEquals(Long.valueOf(10), this.harsk.getAcTouch());
        fail();
    }

    @Test
    public void valerosMaxHp() {
//        assertEquals(Long.valueOf(12), this.valeros.getMaxHp());
        fail();
    }

    @Test
    public void valerosMaxAttackNumber() {
//        assertEquals(Long.valueOf(1), this.valeros.getMaxAttackNumber());
        fail();
    }

    @Test
    public void valerosBaseAtk1() {
//        assertEquals(Long.valueOf(1),
//                this.valeros.getBaseAtkBoni().get(0).getDynamicValueProvider()
//                .getDynamicValue(this.valeros));
        fail();
    }

    @Test
    public void valerosBaseAtkLength() {
//        assertEquals(1, this.valeros.getBaseAtkBoni().size());
        fail();
    }

    @Test
    public void valerosAC() {
        // dex: -1
        // chainmail: +5
//        assertEquals(Long.valueOf(14), this.valeros.getAcStandard());
        fail();
    }

    @Test
    public void valerosACFlatFooted() {
        // dex: -1 -> stays when flat footed!
        // chainmail: +5
//        assertEquals(Long.valueOf(14), this.valeros.getAcFlatFooted());
        fail();
    }

    @Test
    public void valerosACTouch() {
        // dex: -1
        // chainmail: +5 -> not active when touched
//        assertEquals(Long.valueOf(9), this.valeros.getAcTouch());
        fail();
    }

    @Test
    public void valerosChainmailCost() {
        // mwk chainmail!

//        Long cost = this.valeros.getBodySlotByType(this.torso)
//                .getItem().getCost();
//        assertEquals(Long.valueOf(300), cost);
        fail();
    }

    @Test
    public void valerosFeatNumber() {
        // 8 from class: simpleWeaponProficiency, martialWeaponProficiency,
        // lightArmorProficiency, mediumArmorProficiency, heavyArmorProficiency,
        // lightShieldProficiency, heavyShieldProficiency,
        // towerShieldProficiency
        // 1 from character: weaponFinesse
//        assertEquals(9, this.valeros.getFeats().size());
        fail();
    }

    @Test
    public void valeros3rdFeatName() {
        // feat 0 till 7 come from the class, feat 8 from the character.
//        assertEquals(FEAT_WEAPON_FINESSE,
//                this.valeros.getFeats().get(8).getId());
        fail();
    }

    @Test
    public void valerosPrimaryHandItemName() {
//        assertEquals(RAPIER4VALEROS_ID,
//                this.valeros.getBodySlots().get(0).getItem().getId());
        fail();
    }

    @Test
    public void valerosMeleeAtkBonusFirstValue() {
//        List<Long> meleeAtkBoni
//                = this.valeros.getAtkBoni(this.primaryHand,
//                        this.meleeAttackMode);

        // base atk of fighter lvl 1: 1
        // dex 9 instead of str 12 due to weapon finesse: -1
//        assertEquals(Long.valueOf(0), meleeAtkBoni.get(0));
        fail();
    }

    @Test
    public void valerosMeleeAtkBonusFirstValueSecHand() {
//        List<Long> meleeAtkBoni
//                = this.valeros.getAtkBoni(this.secondaryHand,
//                        this.meleeAttackMode);

        // base atk of fighter lvl 1: 1
        // dex 9 instead of str 12 due to weapon finesse: -1
        // mwk dagger: 1
//        assertEquals(Long.valueOf(1), meleeAtkBoni.get(0));
        fail();
    }

    @Test
    public void valerosMwkDaggerCost() {
//        IInventoryItem mwkDagger
//                = this.valeros.getBodySlotByType(this.secondaryHand).getItem();
//        assertEquals(Long.valueOf(302), mwkDagger.getCost());
        fail();
    }

    @Test
    public void harskMeleeAtkBonusFirstValue() {
//        List<Long> meleeAtkBoni
//                = this.harsk.getAtkBoni(this.primaryHand, this.meleeAttackMode);
//
//        // base atk of fighter lvl 2
//        // str 14 -> +2
//        // power attack 1 -> -1
//        assertEquals(Long.valueOf(3), meleeAtkBoni.get(0));
        fail();
    }

    @Test
    public void valerosRangedAtkBonusFirstValue() {
//        List<Long> meleeAtkBoni
//                = this.valeros.getAtkBoni(this.primaryHand,
//                        this.rangedAttackMode);
//
//        // base atk of fighter lvl 1: 1
//        // dex 9 instead of str 12 due to weapon finesse: -1
//        // improvised weapon: rapier is not for throwing,
//        // so you gain a nonproficiency penalty: -4
//        assertEquals(Long.valueOf(-4), meleeAtkBoni.get(0));
        fail();
    }

    @Test
    public void valerosRangedAtkBonusFirstValueSecHand() {
//        List<Long> meleeAtkBoni
//                = this.valeros.getAtkBoni(this.secondaryHand,
//                        this.rangedAttackMode);
//
//        // base atk of fighter lvl 1: 1
//        // dex 9: -1
//        // mwk dagger: 1
//        // dagger is also good for ranged so no changes here.
//        assertEquals(Long.valueOf(1), meleeAtkBoni.get(0));
        fail();
    }

    @Test
    public void harskMeleeAtkBonusFirstValueSecHand() {
//        List<Long> meleeAtkBoni
//                = this.harsk.getAtkBoni(this.secondaryHand,
//                        this.meleeAttackMode);
//
//        // base atk of fighter lvl 2
//        // str 14 -> +2
//        // power attack 1 -> -1
//        // exotic weapon -> nonproficiencyPenalty: -4
//        assertEquals(Long.valueOf(-1), meleeAtkBoni.get(0));
        fail();
    }

    @Test
    public void valerosMeleeDamageBonusPrimHand() {
//        Long damageBonus = this.valeros.getDamageBonus(this.primaryHand,
//                this.meleeAttackMode);
//
//        // str-bonus +2
//        assertEquals(Long.valueOf(2), damageBonus);
        fail();
    }

    @Test
    public void valerosMeleeDamageBonusSecHand() {
//        Long damageBonus = this.valeros.getDamageBonus(this.secondaryHand,
//                this.meleeAttackMode);
//
//        // str-bonus +2 but off-hand: +1 (1/2!)
//        assertEquals(Long.valueOf(1), damageBonus);
        fail();
    }

    @Test
    public void harskMeleeDamageBonusPrimHand() {
//        Long damageBonus = this.harsk.getDamageBonus(this.primaryHand,
//                this.meleeAttackMode);
//
//        // str-bonus -> +2
//        // power attack 1 -> +1
//        assertEquals(Long.valueOf(3), damageBonus);
        fail();
    }

    @Test
    public void harskMeleeDamageBonusSecHand() {
//        Long damageBonus = this.harsk.getDamageBonus(this.secondaryHand,
//                this.meleeAttackMode);
//
//        // str-bonus +2 but off-hand: +1
//        // power attack 1 -> +1
//        assertEquals(Long.valueOf(2), damageBonus);
        fail();
    }

    /**
     * test damage bonus of rapier in two hands (1.5 Str bonus does NOT apply).
     */
    @Test
    public void valerosMeleeDamageBonusBothHandsRapier() {
//        //TODO create API to easily sitch position of items.
//        this.valeros.getBodySlotByType(this.secondaryHand).setItem(
//                this.valeros.getBodySlotByType(this.primaryHand).getItem()
//        );
//        Long damageBonus = this.valeros.getDamageBonus(this.primaryHand,
//                this.meleeAttackMode);
//
//        // str-bonus +2
//        assertEquals(Long.valueOf(2), damageBonus);
        fail();
    }

    /**
     * test damage bonus of battle axe in two hands (1.5 Str bonus applies).
     */
    @Test
    public void harskMeleeDamageBonusBothHandsBattleAxe() {
//        this.harsk.getBodySlotByType(this.secondaryHand).setItem(
//                this.harsk.getBodySlotByType(this.primaryHand).getItem()
//        );
//
//        Long damageBonus = this.harsk.getDamageBonus(this.primaryHand,
//                this.meleeAttackMode);
//
//        // str-bonus -> +2 (*1.5 both hands) -> +3
//        // power attack 1 -> +1 (*2 both hands) -> +2
//        assertEquals(Long.valueOf(5), damageBonus);
        fail();
    }

    @Test
    public void valerosRangedDamageBonusBow() {
//
//        WeaponSetup bowSetup = new WeaponSetup();
//        bowSetup.setId("bowValeros");
//        bowSetup.setName("longbow");
//        bowSetup.setSizeCategory("medium");
//        IWeapon bowValeros
//                = new WeaponFactory(bowSetup, this.context).build();
//
//        this.valeros.getBodySlotByType(this.primaryHand).setItem(bowValeros);
//        this.valeros.getBodySlotByType(this.secondaryHand).setItem(bowValeros);
//
//        Long damageBonus = this.valeros.getDamageBonus(this.primaryHand,
//                this.rangedAttackMode);
//
//        // str-bonus not applied!
//        assertEquals(Long.valueOf(0), damageBonus);
        fail();
    }

    /**
     * test attack with bow in ranged mode: str penalty applies (use weak
     * character with negative str bonus).
     */
    @Test
    public void valerosWeakRangedDamageBonusBow() {

//        WeaponSetup bowSetup = new WeaponSetup();
//        bowSetup.setId("bowValeros");
//        bowSetup.setName("longbow");
//        bowSetup.setSizeCategory("medium");
//        IWeapon bowValeros
//                = new WeaponFactory(bowSetup, this.context).build();
//
//        this.valeros.getBodySlotByType(this.primaryHand).setItem(bowValeros);
//        this.valeros.getBodySlotByType(this.secondaryHand).setItem(bowValeros);
//
//        IAbility str = this.context.getBean("str", Ability.class);
//        this.valeros.getBaseAbilityMap().put(str, 8L); // --> bonus -1
//
//        Long damageBonus = this.valeros.getDamageBonus(this.primaryHand,
//                this.rangedAttackMode);
//
//        // str-bonus: -1 is applied!
//        assertEquals(Long.valueOf(-1), damageBonus);
        fail();
    }

    /**
     * test attack with bow in melee mode: str bonus applies normal (use strong
     * character with positive str bonus).
     */
    @Test
    public void valerosWeakMeleeDamageBonusBow() {

//        WeaponSetup bowSetup = new WeaponSetup();
//        bowSetup.setId("bowValeros");
//        bowSetup.setName("longbow");
//        bowSetup.setSizeCategory("medium");
//        IWeapon bowValeros
//                = new WeaponFactory(bowSetup, this.context).build();
//
//        this.valeros.getBodySlotByType(this.primaryHand).setItem(bowValeros);
//        this.valeros.getBodySlotByType(this.secondaryHand).setItem(bowValeros);
//
//        Long damageBonus = this.valeros.getDamageBonus(this.primaryHand,
//                this.meleeAttackMode);
//
//        // str-bonus: +2 is applied! (*1.5 both hands)
//        assertEquals(Long.valueOf(3), damageBonus);
        fail();
    }

    @Test
    public void valerosGetInitBonus() {
//        Long initBonus = this.valeros.getInitBonus();
//        // dex 9 --> -1
//        assertEquals(Long.valueOf(-1), initBonus);
        fail();
    }

    @Test
    public void harskGetInitBonus() {
//        Long initBonus = this.harsk.getInitBonus();
//        // dex 11 --> 0
//        // improved initiative --> 4
//        assertEquals(Long.valueOf(4), initBonus);
        fail();
    }

    //TODO test what happens when wearing two shields!!! the penalties on attack
    // should not stack!
    //TODO test if situation context invalidation works: get damage bonus for
    // ranged directly before damage bonus for melee. There should be no
    // difference to when you only get damage bonus for melee!!!
}
