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
import org.asciicerebrum.neocortexengine.domain.setup.CharacterSetup;
import org.asciicerebrum.neocortexengine.domain.setup.PersonalizedBodySlotSetup;
import org.asciicerebrum.neocortexengine.domain.setup.SetupProperty;
import org.asciicerebrum.neocortexengine.integrationtests.pool.dndCharacters.HarskDwarfFighter2;
import org.asciicerebrum.neocortexengine.integrationtests.pool.inventoryItems.armors.StandardLightWoodenShield;
import org.asciicerebrum.neocortexengine.integrationtests.pool.inventoryItems.armors.StandardStuddedLeather;
import org.asciicerebrum.neocortexengine.integrationtests.pool.inventoryItems.weapons.StandardBattleaxe;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class CharacterPropertiesHarskIntegrationTest {

    private static final Logger LOG
            = LoggerFactory.getLogger(
                    CharacterPropertiesHarskIntegrationTest.class);

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

    private UniqueId harskId;

    @Before
    public void setUp() {

        this.entityPoolService.registerUniqueEntity(this.dndCharacterFactory
                .newEntity(HarskDwarfFighter2.getSetup()));

        this.entityPoolService.registerUniqueEntity(this.armorFactory
                .newEntity(StandardStuddedLeather.getSetup()));
        this.entityPoolService.registerUniqueEntity(this.weaponFactory
                .newEntity(StandardBattleaxe.getSetup()));
        this.entityPoolService.registerUniqueEntity(this.armorFactory
                .newEntity(StandardLightWoodenShield.getSetup()));

        this.harskId = new UniqueId("harsk");
    }

    @After
    public void tearDown() {
        this.entityPoolService.empty();
    }

    @Test
    public void harskNameTest() {
        final String id = ((DndCharacter) this.entityPoolService
                .getEntityById(this.harskId)).getUniqueId().getValue();

        assertEquals("harsk", id);
    }

    @Test
    public void harskMaxHpTest() {
        final HitPoints result = this.hpCalculationService
                .calcMaxHp((DndCharacter) this.entityPoolService
                        .getEntityById(this.harskId));

        // lvl 1 fighter: 10 + con mod 2 (con 15)
        // lvl 2: 6 + con mod 2
        assertEquals(20L, result.getValue());
    }

    @Test
    public void harskBaseAtk1Test() {
        final BonusValueTuple result
                = ((DndCharacter) this.entityPoolService
                .getEntityById(this.harskId)).getBaseAtkBoni();

        // lvl 2 fighter: 2 (no other boni apply here!)
        assertEquals(2L,
                result.getBonusValueByRank(BonusRank.RANK_0).getValue());
    }

    @Test
    public void harskBaseAtkLengthTest() {
        final BonusValueTuple result
                = ((DndCharacter) this.entityPoolService
                .getEntityById(this.harskId)).getBaseAtkBoni();

        // lvl 2 fighter: 1 attack
        assertEquals(1L, Iterators.size(result.iterator()));
    }

    //TODO test rank ordered bonus list of a size of at least 2.
    @Test
    public void harskAcTest() {
        final ArmorClass ac = this.acCalculationService.calcAcStandard(
                ((DndCharacter) this.entityPoolService
                .getEntityById(this.harskId)));

        // dex 15: +2
        // studded leather: +3
        // light wooden shield: +1
        assertEquals(16L, ac.getValue());
    }

    @Test
    public void harskACFlatFootedTest() {
        final ArmorClass ac = this.acCalculationService.calcAcFlatFooted(
                ((DndCharacter) this.entityPoolService
                .getEntityById(this.harskId)));

        // dex 15: +2 NOT GRANTED HERE
        // studded leather: +3
        // light wooden shield: +1
        assertEquals(14L, ac.getValue());
    }

    @Test
    public void harskACTouchTest() {
        final ArmorClass ac = this.acCalculationService.calcAcTouch(
                ((DndCharacter) this.entityPoolService
                .getEntityById(this.harskId)));

        // dex 15: +2
        // studded leather: +3 NOT GRANTED HERE
        // light wooden shield: +1 NOT GRANTED HERE
        assertEquals(12L, ac.getValue());
    }

    @Test
    public void harskAcWithoutShieldTest() {
        final CharacterSetup setup = HarskDwarfFighter2.getSetup();
        setup.getPropertySetups(SetupProperty.BODY_SLOTS).remove(1);
        this.entityPoolService.registerUniqueEntity(this.dndCharacterFactory
                .newEntity(setup));

        final ArmorClass ac = this.acCalculationService.calcAcStandard(
                ((DndCharacter) this.entityPoolService
                .getEntityById(this.harskId)));

        // dex 15: +2
        // studded leather: +3
        assertEquals(15L, ac.getValue());
    }

    @Test
    public void harskAcWithShieldInBothHandsTest() {
        final PersonalizedBodySlotSetup hand1Setup
                = new PersonalizedBodySlotSetup();
        hand1Setup.setBodySlotType("primaryHand");
        hand1Setup.setItem("standardLightWoodenShield");
        hand1Setup.setIsPrimaryAttackSlot("true");

        final CharacterSetup setup = HarskDwarfFighter2.getSetup();
        // removal of battleaxe
        setup.getPropertySetups(SetupProperty.BODY_SLOTS).remove(0);
        // adding the same shield also on primary hand
        setup.getPropertySetups(SetupProperty.BODY_SLOTS).add(hand1Setup);

        this.entityPoolService.registerUniqueEntity(this.dndCharacterFactory
                .newEntity(setup));

        final ArmorClass ac = this.acCalculationService.calcAcStandard(
                ((DndCharacter) this.entityPoolService
                .getEntityById(this.harskId)));

        // dex 15: +2
        // studded leather: +3
        // light wooden shield: +1
        // holding a shield in two hands does not bring you anything!
        assertEquals(16L, ac.getValue());
    }

    @Test
    public void harskAcWithShieldInWrongSlotTest() {
        final PersonalizedBodySlotSetup torsoSetup
                = new PersonalizedBodySlotSetup();
        torsoSetup.setBodySlotType("torso");
        torsoSetup.setItem("standardLightWoodenShield");
        torsoSetup.setIsPrimaryAttackSlot("false");

        final CharacterSetup setup = HarskDwarfFighter2.getSetup();
        // removal of studded leather
        setup.getPropertySetups(SetupProperty.BODY_SLOTS).remove(2);
        // removal of light wooden shield
        setup.getPropertySetups(SetupProperty.BODY_SLOTS).remove(1);
        // adding the shield on in inappropriate body slot type torso
        setup.getPropertySetups(SetupProperty.BODY_SLOTS).add(torsoSetup);

        this.entityPoolService.registerUniqueEntity(this.dndCharacterFactory
                .newEntity(setup));

        final ArmorClass ac = this.acCalculationService.calcAcStandard(
                ((DndCharacter) this.entityPoolService
                .getEntityById(this.harskId)));

        // dex 15: +2
        // studded leather: +3 CANNOT BE APPLIED! (removed)
        // light wooden shield: +1 CANNOT BE APPLIED! (wrong slot)
        assertEquals(12L, ac.getValue());
    }

    @Test
    public void harskACFlatFootedWithoutShieldTest() {
        final CharacterSetup setup = HarskDwarfFighter2.getSetup();
        setup.getPropertySetups(SetupProperty.BODY_SLOTS).remove(1);
        this.entityPoolService.registerUniqueEntity(this.dndCharacterFactory
                .newEntity(setup));

        final ArmorClass ac = this.acCalculationService.calcAcFlatFooted(
                ((DndCharacter) this.entityPoolService
                .getEntityById(this.harskId)));

        // dex 15: +2 NOT GRANTED HERE
        // studded leather: +3
        assertEquals(13L, ac.getValue());

    }

    @Test
    public void harskACTouchWithShieldTest() {
        final CharacterSetup setup = HarskDwarfFighter2.getSetup();
        setup.getPropertySetups(SetupProperty.BODY_SLOTS).remove(1);
        this.entityPoolService.registerUniqueEntity(this.dndCharacterFactory
                .newEntity(setup));

        final ArmorClass ac = this.acCalculationService.calcAcTouch(
                ((DndCharacter) this.entityPoolService
                .getEntityById(this.harskId)));

        // dex 15: +2
        // studded leather: +3 NOT GRANTED HERE
        assertEquals(12L, ac.getValue());
    }

    @Test
    public void harskMeleeAtkBonusFirstValueTest() {
        final BonusValueTuple atkResult
                = this.atkCalculationService.calcAtkBoni(
                        (Weapon) this.entityPoolService
                        .getEntityById(new UniqueId("standardBattleaxe")),
                        (DndCharacter) this.entityPoolService
                        .getEntityById(this.harskId));

        // base atk fighter lvl 2: 2
        // str 14: +2
        // power attack 1: -1
        assertEquals(3L, atkResult.getBonusValueByRank(BonusRank.RANK_0)
                .getValue());
    }

    @Test
    public void harskMeleeAtkBonusFirstValueWithoutPowerAttackSetupTest() {
        final CharacterSetup setup = HarskDwarfFighter2.getSetup();
        setup.setStateRegistrySetup(null);
        this.entityPoolService.registerUniqueEntity(this.dndCharacterFactory
                .newEntity(setup));

        final BonusValueTuple atkResult
                = this.atkCalculationService.calcAtkBoni(
                        (Weapon) this.entityPoolService
                        .getEntityById(new UniqueId("standardBattleaxe")),
                        (DndCharacter) this.entityPoolService
                        .getEntityById(this.harskId));

        // base atk fighter lvl 2: 2
        // str 14: +2
        assertEquals(4L, atkResult.getBonusValueByRank(BonusRank.RANK_0)
                .getValue());
    }

    @Test
    public void harskMeleeDamageBonusPrimHandTest() {
        final BonusValue damage = this.damageCalculationService.calcDamageBonus(
                (Weapon) this.entityPoolService
                .getEntityById(new UniqueId("standardBattleaxe")),
                (DndCharacter) this.entityPoolService
                .getEntityById(this.harskId));

        // str-bonus -> +2
        // power attack 1 -> +1
        assertEquals(3L, damage.getValue());
    }

    @Test
    public void harskMeleeDamageBonusPrimHandWithoutPowerAttackSetupTest() {
        final CharacterSetup setup = HarskDwarfFighter2.getSetup();
        setup.setStateRegistrySetup(null);
        this.entityPoolService.registerUniqueEntity(this.dndCharacterFactory
                .newEntity(setup));

        final BonusValue damage = this.damageCalculationService.calcDamageBonus(
                (Weapon) this.entityPoolService
                .getEntityById(new UniqueId("standardBattleaxe")),
                (DndCharacter) this.entityPoolService
                .getEntityById(this.harskId));

        // str-bonus -> +2
        assertEquals(2L, damage.getValue());
    }

    @Test
    public void harskMeleeDamageBonusSecHandTest() {
        final PersonalizedBodySlotSetup hand2Setup
                = new PersonalizedBodySlotSetup();
        hand2Setup.setBodySlotType("secondaryHand");
        hand2Setup.setItem("standardBattleaxe");
        hand2Setup.setIsPrimaryAttackSlot("false");

        final CharacterSetup setup = HarskDwarfFighter2.getSetup();
        // removal of all items
        setup.getPropertySetups(SetupProperty.BODY_SLOTS).clear();
        // adding the axe on sec hand
        setup.getPropertySetups(SetupProperty.BODY_SLOTS).add(hand2Setup);

        this.entityPoolService.registerUniqueEntity(this.dndCharacterFactory
                .newEntity(setup));

        final BonusValue damage = this.damageCalculationService.calcDamageBonus(
                (Weapon) this.entityPoolService
                .getEntityById(new UniqueId("standardBattleaxe")),
                (DndCharacter) this.entityPoolService
                .getEntityById(this.harskId));

        // str-bonus -> +2 --> off-hand (*0.5) --> +1
        // power attack 1 -> +1
        assertEquals(2L, damage.getValue());
    }

    /**
     * test damage bonus of battle axe in two hands (1.5 Str bonus applies).
     */
    @Test
    public void harskMeleeDamageBonusBothHandsTest() {
        final PersonalizedBodySlotSetup hand1Setup
                = new PersonalizedBodySlotSetup();
        hand1Setup.setBodySlotType("primaryHand");
        hand1Setup.setItem("standardBattleaxe");
        hand1Setup.setIsPrimaryAttackSlot("true");
        final PersonalizedBodySlotSetup hand2Setup
                = new PersonalizedBodySlotSetup();
        hand2Setup.setBodySlotType("secondaryHand");
        hand2Setup.setItem("standardBattleaxe");
        hand2Setup.setIsPrimaryAttackSlot("false");

        final CharacterSetup setup = HarskDwarfFighter2.getSetup();
        // removal of all items
        setup.getPropertySetups(SetupProperty.BODY_SLOTS).clear();
        // adding the axe on prim and sec hand
        setup.getPropertySetups(SetupProperty.BODY_SLOTS).add(hand1Setup);
        setup.getPropertySetups(SetupProperty.BODY_SLOTS).add(hand2Setup);

        this.entityPoolService.registerUniqueEntity(this.dndCharacterFactory
                .newEntity(setup));

        final BonusValue damage = this.damageCalculationService.calcDamageBonus(
                (Weapon) this.entityPoolService
                .getEntityById(new UniqueId("standardBattleaxe")),
                (DndCharacter) this.entityPoolService
                .getEntityById(this.harskId));

        // str-bonus -> +2 --> two-hands (*1.5) --> +3
        // power attack 1 -> +1 --> two-hands (*2) --> +2
        assertEquals(5L, damage.getValue());
    }

    @Test
    public void harskGetInitBonusTest() {
        final BonusValue initBonusResult
                = this.initiativeCalculationService.calcInitBonus(
                        (DndCharacter) this.entityPoolService
                        .getEntityById(this.harskId));
        // dex mod: +2
        assertEquals(2L, initBonusResult.getValue());
    }

}
