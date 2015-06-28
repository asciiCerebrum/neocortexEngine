package org.asciicerebrum.neocortexengine.integrationtests.dndcharacter;

import com.google.common.collect.Iterators;
import org.asciicerebrum.neocortexengine.domain.core.particles.ArmorClass;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusRank;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusValue;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusValueTuple;
import org.asciicerebrum.neocortexengine.domain.core.particles.HitPoints;
import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueId;
import org.asciicerebrum.neocortexengine.domain.factories.ArmorFactory;
import org.asciicerebrum.neocortexengine.domain.factories.DndCharacterFactory;
import org.asciicerebrum.neocortexengine.domain.factories.WeaponFactory;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.game.Weapon;
import org.asciicerebrum.neocortexengine.domain.ruleentities.BodySlot;
import org.asciicerebrum.neocortexengine.domain.ruleentities.BodySlotType;
import org.asciicerebrum.neocortexengine.domain.ruleentities.composition.PersonalizedBodySlot;
import org.asciicerebrum.neocortexengine.domain.ruleentities.composition.PersonalizedBodySlot.Facet;
import org.asciicerebrum.neocortexengine.domain.setup.CharacterSetup;
import org.asciicerebrum.neocortexengine.domain.setup.PersonalizedBodySlotSetup;
import org.asciicerebrum.neocortexengine.domain.setup.SetupProperty;
import org.asciicerebrum.neocortexengine.integrationtests.pool.dndCharacters.ValerosHumanFighter1;
import org.asciicerebrum.neocortexengine.integrationtests.pool.inventoryItems.armors.MwkChainmail;
import org.asciicerebrum.neocortexengine.integrationtests.pool.inventoryItems.armors.MwkHeavySteelShield;
import org.asciicerebrum.neocortexengine.integrationtests.pool.inventoryItems.armors.StandardLightWoodenShield;
import org.asciicerebrum.neocortexengine.integrationtests.pool.inventoryItems.weapons.MwkBastardsword;
import org.asciicerebrum.neocortexengine.integrationtests.pool.inventoryItems.weapons.MwkRapier;
import org.asciicerebrum.neocortexengine.integrationtests.pool.inventoryItems.weapons.StandardLongsword;
import org.asciicerebrum.neocortexengine.services.context.EntityPoolService;
import org.asciicerebrum.neocortexengine.services.statistics.AcCalculationService;
import org.asciicerebrum.neocortexengine.services.statistics.AtkCalculationService;
import org.asciicerebrum.neocortexengine.services.statistics.DamageCalculationService;
import org.asciicerebrum.neocortexengine.services.statistics.HpCalculationService;
import org.asciicerebrum.neocortexengine.services.statistics.InitiativeCalculationService;
import org.asciicerebrum.neocortexengine.testcategories.IntegrationTest;
import org.junit.After;
import static org.junit.Assert.assertEquals;
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
public class CharacterPropertiesValerosIntegrationTest {

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
    private HpCalculationService hpCalculationService;

    @Autowired
    private AcCalculationService acCalculationService;

    @Autowired
    private AtkCalculationService atkCalculationService;

    @Autowired
    private DamageCalculationService damageCalculationService;

    @Autowired
    private InitiativeCalculationService initiativeCalculationService;

    private UniqueId valerosId;

    @Before
    public void setUp() {

        this.entityPoolService.registerUniqueEntity(this.dndCharacterFactory
                .newEntity(ValerosHumanFighter1.getSetup()));

        this.entityPoolService.registerUniqueEntity(this.armorFactory
                .newEntity(MwkChainmail.getSetup()));
        this.entityPoolService.registerUniqueEntity(this.armorFactory
                .newEntity(StandardLightWoodenShield.getSetup()));
        this.entityPoolService.registerUniqueEntity(this.armorFactory
                .newEntity(MwkHeavySteelShield.getSetup()));
        this.entityPoolService.registerUniqueEntity(this.weaponFactory
                .newEntity(StandardLongsword.getSetup()));
        this.entityPoolService.registerUniqueEntity(this.weaponFactory
                .newEntity(MwkBastardsword.getSetup()));
        this.entityPoolService.registerUniqueEntity(this.weaponFactory
                .newEntity(MwkRapier.getSetup()));

        this.valerosId = new UniqueId("valeros");
    }

    @After
    public void tearDown() {
        this.entityPoolService.empty();
    }

    @Test
    public void valerosNameTest() {
        final String id = ((DndCharacter) this.entityPoolService.getEntityById(
                this.valerosId)).getUniqueId().getValue();

        assertEquals("valeros", id);
    }

    @Test
    public void valerosMaxHpTest() {
        final HitPoints result = this.hpCalculationService
                .calcMaxHp((DndCharacter) this.entityPoolService
                        .getEntityById(this.valerosId));

        // lvl 1 fighter: 10 + con mod 1 (con 12)
        assertEquals(11L, result.getValue());
    }

    @Test
    public void valerosBaseAtk1Test() {
        final BonusValueTuple result
                = ((DndCharacter) this.entityPoolService
                .getEntityById(this.valerosId)).getBaseAtkBoni();

        // lvl 1 fighter: 1 (no other boni apply here!)
        assertEquals(1L,
                result.getBonusValueByRank(BonusRank.RANK_0).getValue());
    }

    @Test
    public void valerosBaseAtkLengthTest() {
        final BonusValueTuple result
                = ((DndCharacter) this.entityPoolService
                .getEntityById(this.valerosId)).getBaseAtkBoni();

        // lvl 1 fighter: 1 attack
        assertEquals(1L, Iterators.size(result.iterator()));
    }

    @Test
    public void valerosAcTest() {
        final ArmorClass ac = this.acCalculationService.calcAcStandard(
                ((DndCharacter) this.entityPoolService
                .getEntityById(this.valerosId)));

        // dex 15: +2
        // mwk chainmail: +5 (and no extra armor bonus for mwk!)
        assertEquals(17L, ac.getValue());
    }

    @Test
    public void valerosAcFlatFootedTest() {
        final ArmorClass ac = this.acCalculationService.calcAcFlatFooted(
                ((DndCharacter) this.entityPoolService
                .getEntityById(this.valerosId)));

        // dex 15: +2 NOT GRANTED HERE
        // mwk chainmail: +5 (and no extra armor bonus for mwk!)
        assertEquals(15L, ac.getValue());
    }

    @Test
    public void valerosAcTouchTest() {
        final ArmorClass ac = this.acCalculationService.calcAcTouch(
                ((DndCharacter) this.entityPoolService
                .getEntityById(this.valerosId)));

        // dex 15: +2
        // mwk chainmail: +5 (and no extra armor bonus for mwk!) NOT GRANTED!!!
        assertEquals(12L, ac.getValue());
    }

    @Test
    public void valerosAcWithShieldTest() {
        final CharacterSetup setup = ValerosHumanFighter1.getSetup();

        final PersonalizedBodySlotSetup hand2Setup
                = new PersonalizedBodySlotSetup();
        hand2Setup.setBodySlotType("secondaryHand");
        hand2Setup.setItem("standardLightWoodenShield");
        hand2Setup.setIsPrimaryAttackSlot("false");

        setup.getPropertySetups(SetupProperty.BODY_SLOTS).add(hand2Setup);
        this.entityPoolService.registerUniqueEntity(this.dndCharacterFactory
                .newEntity(setup));

        final ArmorClass ac = this.acCalculationService.calcAcStandard(
                ((DndCharacter) this.entityPoolService
                .getEntityById(this.valerosId)));

        // dex 15: +2
        // chainmail: +5
        // light wooden shield +1
        assertEquals(18L, ac.getValue());
    }

    @Test
    public void valerosAcWithTwoShieldsTest() {
        final PersonalizedBodySlotSetup hand1Setup
                = new PersonalizedBodySlotSetup();
        hand1Setup.setBodySlotType("primaryHand");
        hand1Setup.setItem("standardLightWoodenShield");
        hand1Setup.setIsPrimaryAttackSlot("true");

        final PersonalizedBodySlotSetup hand2Setup
                = new PersonalizedBodySlotSetup();
        hand2Setup.setBodySlotType("secondaryHand");
        hand2Setup.setItem("mwkHeavySteelShield");
        hand2Setup.setIsPrimaryAttackSlot("false");

        final CharacterSetup setup = ValerosHumanFighter1.getSetup();
        // removal of battleaxe
        setup.getPropertySetups(SetupProperty.BODY_SLOTS).remove(0);
        // adding the shields
        setup.getPropertySetups(SetupProperty.BODY_SLOTS).add(hand1Setup);
        setup.getPropertySetups(SetupProperty.BODY_SLOTS).add(hand2Setup);

        this.entityPoolService.registerUniqueEntity(this.dndCharacterFactory
                .newEntity(setup));

        final ArmorClass ac = this.acCalculationService.calcAcStandard(
                ((DndCharacter) this.entityPoolService
                .getEntityById(this.valerosId)));

        // dex 15: +2
        // chainmail: +5
        // light wooden shield: +1 // NOT GRANTED, LOWER SHIELD-BONUS!
        // mwk Heavy Steel Shield: +2
        assertEquals(19L, ac.getValue());
    }

    @Test
    public void valerosPrimaryHandItemNameTest() {
        final BodySlotType primaryType
                = this.context.getBean("primaryHand", BodySlotType.class);
        final BodySlot bSlot = new BodySlot();
        bSlot.setBodySlotType(primaryType);
        final PersonalizedBodySlot blueprint = new PersonalizedBodySlot();
        blueprint.setBodySlot(bSlot);

        final PersonalizedBodySlot result
                = ((DndCharacter) this.entityPoolService
                .getEntityById(this.valerosId))
                .getPersonalizedBodySlots()
                .findFirstSimilar(blueprint, Facet.BODY_SLOT_TYPE);

        assertEquals("standardLongsword", result.getItemId().getValue());
    }

    @Test
    public void valerosMeleeAtkBonusFirstValueTest() {
        final BonusValueTuple atkResult
                = this.atkCalculationService.calcAtkBoni(
                        (Weapon) this.entityPoolService
                        .getEntityById(new UniqueId("standardLongsword")),
                        (DndCharacter) this.entityPoolService
                        .getEntityById(this.valerosId));

        // base atk fighter lvl 1: +1
        // str 14: +2
        assertEquals(3L, atkResult.getBonusValueByRank(BonusRank.RANK_0)
                .getValue());
    }

    @Test
    public void valerosMeleeAtkBonusFirstValueSecHandTest() {
        final PersonalizedBodySlotSetup hand2Setup
                = new PersonalizedBodySlotSetup();
        hand2Setup.setBodySlotType("secondaryHand");
        hand2Setup.setItem("mwkBastardsword");
        hand2Setup.setIsPrimaryAttackSlot("false");

        final CharacterSetup setup = ValerosHumanFighter1.getSetup();
        setup.getPropertySetups(SetupProperty.BODY_SLOTS).add(hand2Setup);

        this.entityPoolService.registerUniqueEntity(this.dndCharacterFactory
                .newEntity(setup));

        final BonusValueTuple atkResult
                = this.atkCalculationService.calcAtkBoni(
                        (Weapon) this.entityPoolService
                        .getEntityById(new UniqueId("mwkBastardsword")),
                        (DndCharacter) this.entityPoolService
                        .getEntityById(this.valerosId));

        // base atk of fighter lvl 1: +1
        // str 14 -> +2
        // exotic weapon -> nonproficiencyPenalty: -4
        // mwk weapon: +1
        assertEquals(0L, atkResult.getBonusValueByRank(BonusRank.RANK_0)
                .getValue());
    }

    @Test
    public void valerosMeleeAtkBonusFirstValueTwoMwkWeaponsTest() {
        final PersonalizedBodySlotSetup hand1Setup
                = new PersonalizedBodySlotSetup();
        hand1Setup.setBodySlotType("primaryHand");
        hand1Setup.setItem("mwkRapier");
        hand1Setup.setIsPrimaryAttackSlot("true");

        final PersonalizedBodySlotSetup hand2Setup
                = new PersonalizedBodySlotSetup();
        hand2Setup.setBodySlotType("secondaryHand");
        hand2Setup.setItem("mwkBastardsword");
        hand2Setup.setIsPrimaryAttackSlot("false");

        final CharacterSetup setup = ValerosHumanFighter1.getSetup();

        // removal of battleaxe
        setup.getPropertySetups(SetupProperty.BODY_SLOTS).remove(0);
        // adding the rapier
        setup.getPropertySetups(SetupProperty.BODY_SLOTS).add(hand1Setup);
        setup.getPropertySetups(SetupProperty.BODY_SLOTS).add(hand2Setup);

        this.entityPoolService.registerUniqueEntity(this.dndCharacterFactory
                .newEntity(setup));

        final BonusValueTuple atkResult
                = this.atkCalculationService.calcAtkBoni(
                        (Weapon) this.entityPoolService
                        .getEntityById(new UniqueId("mwkRapier")),
                        (DndCharacter) this.entityPoolService
                        .getEntityById(this.valerosId));

        // base atk of fighter lvl 1: +1
        // str 14 -> +2
        // mwk weapon: +1
        assertEquals(4L, atkResult.getBonusValueByRank(BonusRank.RANK_0)
                .getValue());
    }

    @Test
    public void valerosMeleeDamageBonusPrimHandTest() {
        final BonusValue damage = this.damageCalculationService.calcDamageBonus(
                (Weapon) this.entityPoolService
                .getEntityById(new UniqueId("standardLongsword")),
                (DndCharacter) this.entityPoolService
                .getEntityById(this.valerosId));

        // str-bonus -> +2
        assertEquals(2L, damage.getValue());
    }

    @Test
    public void valerosMeleeDamageBonusSecHandTest() {
        final PersonalizedBodySlotSetup hand2Setup
                = new PersonalizedBodySlotSetup();
        hand2Setup.setBodySlotType("secondaryHand");
        hand2Setup.setItem("standardLongsword");
        hand2Setup.setIsPrimaryAttackSlot("false");

        final CharacterSetup setup = ValerosHumanFighter1.getSetup();
        // removal of all items
        setup.getPropertySetups(SetupProperty.BODY_SLOTS).clear();
        // adding the axe on sec hand
        setup.getPropertySetups(SetupProperty.BODY_SLOTS).add(hand2Setup);

        this.entityPoolService.registerUniqueEntity(this.dndCharacterFactory
                .newEntity(setup));

        final BonusValue damage = this.damageCalculationService.calcDamageBonus(
                (Weapon) this.entityPoolService
                .getEntityById(new UniqueId("standardLongsword")),
                (DndCharacter) this.entityPoolService
                .getEntityById(this.valerosId));

        // str-bonus -> +2 --> off-hand (*0.5) --> +1
        assertEquals(1L, damage.getValue());
    }

    @Test
    public void valerosMeleeDamageBonusBothHandsTest() {
        final PersonalizedBodySlotSetup hand1Setup
                = new PersonalizedBodySlotSetup();
        hand1Setup.setBodySlotType("primaryHand");
        hand1Setup.setItem("standardLongsword");
        hand1Setup.setIsPrimaryAttackSlot("true");
        final PersonalizedBodySlotSetup hand2Setup
                = new PersonalizedBodySlotSetup();
        hand2Setup.setBodySlotType("secondaryHand");
        hand2Setup.setItem("standardLongsword");
        hand2Setup.setIsPrimaryAttackSlot("false");

        final CharacterSetup setup = ValerosHumanFighter1.getSetup();
        // removal of all items
        setup.getPropertySetups(SetupProperty.BODY_SLOTS).clear();
        // adding the sword on prim and sec hand
        setup.getPropertySetups(SetupProperty.BODY_SLOTS).add(hand1Setup);
        setup.getPropertySetups(SetupProperty.BODY_SLOTS).add(hand2Setup);

        this.entityPoolService.registerUniqueEntity(this.dndCharacterFactory
                .newEntity(setup));

        final BonusValue damage = this.damageCalculationService.calcDamageBonus(
                (Weapon) this.entityPoolService
                .getEntityById(new UniqueId("standardLongsword")),
                (DndCharacter) this.entityPoolService
                .getEntityById(this.valerosId));

        // str-bonus -> +2 --> two-hands (*1.5) --> +3
        assertEquals(3L, damage.getValue());
    }

    @Test
    public void valerosGetInitBonusTest() {
        final BonusValue initBonusResult
                = this.initiativeCalculationService.calcInitBonus(
                        (DndCharacter) this.entityPoolService
                        .getEntityById(this.valerosId));
        // dex mod: +2
        // improved init: +4
        assertEquals(6L, initBonusResult.getValue());
    }

}
