package org.asciicerebrum.mydndgame.integrationtests.dndcharacter;

import org.asciicerebrum.mydndgame.domain.core.particles.BonusRank;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValueTuple;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.factories.ArmorFactory;
import org.asciicerebrum.mydndgame.domain.factories.DndCharacterFactory;
import org.asciicerebrum.mydndgame.domain.factories.WeaponFactory;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.domain.setup.CharacterSetup;
import org.asciicerebrum.mydndgame.domain.setup.PersonalizedBodySlotSetup;
import org.asciicerebrum.mydndgame.domain.setup.SetupProperty;
import org.asciicerebrum.mydndgame.domain.setup.StateRegistrySetup;
import org.asciicerebrum.mydndgame.integrationtests.pool.dndCharacters.MerisielElfRogue1;
import org.asciicerebrum.mydndgame.integrationtests.pool.inventoryItems.armors.StandardStuddedLeather;
import org.asciicerebrum.mydndgame.integrationtests.pool.inventoryItems.weapons.MwkRapier;
import org.asciicerebrum.mydndgame.integrationtests.pool.inventoryItems.weapons.StandardBattleaxe;
import org.asciicerebrum.mydndgame.services.context.EntityPoolService;
import org.asciicerebrum.mydndgame.services.statistics.AtkCalculationService;
import org.asciicerebrum.mydndgame.services.statistics.DamageCalculationService;
import org.asciicerebrum.mydndgame.services.statistics.InitiativeCalculationService;
import org.asciicerebrum.mydndgame.testcategories.IntegrationTest;
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
public class CharacterPropertiesMerisielIntegrationTest {

    private static final Logger LOG
            = LoggerFactory.getLogger(
                    CharacterPropertiesMerisielIntegrationTest.class);

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
    private AtkCalculationService atkCalculationService;

    @Autowired
    private DamageCalculationService damageCalculationService;

    @Autowired
    private InitiativeCalculationService initiativeCalculationService;

    private UniqueId merisielId;

    @Before
    public void setUp() {

        this.entityPoolService.registerUniqueEntity(this.dndCharacterFactory
                .newEntity(MerisielElfRogue1.getSetup()));

        this.entityPoolService.registerUniqueEntity(this.armorFactory
                .newEntity(StandardStuddedLeather.getSetup()));
        this.entityPoolService.registerUniqueEntity(this.weaponFactory
                .newEntity(MwkRapier.getSetup()));
        this.entityPoolService.registerUniqueEntity(this.weaponFactory
                .newEntity(StandardBattleaxe.getSetup()));

        this.merisielId = new UniqueId("merisiel");
    }

    @After
    public void tearDown() {
        this.entityPoolService.empty();
    }

    @Test
    public void merisielMeleeAtkBonusFirstValueNoWeaponFinesseSetupTest() {
        LOG.info("Doing merisielMeleeAtkBonusFirstValueTest.");

        final CharacterSetup setup = MerisielElfRogue1.getSetup();

        // deleting weapon finesse mode from registry
        setup.setStateRegistrySetup(null);

        this.entityPoolService.registerUniqueEntity(this.dndCharacterFactory
                .newEntity(setup));

        final BonusValueTuple atkResult
                = this.atkCalculationService.calcAtkBoni(
                        (Weapon) this.entityPoolService
                        .getEntityById(new UniqueId("mwkRapier")),
                        (DndCharacter) this.entityPoolService
                        .getEntityById(this.merisielId));

        LOG.info("Done doing merisielMeleeAtkBonusFirstValueTest.");

        // base atk: 0
        // STR mod +1 (weapon finesse: DEX mod: +3 NOT GRANTED!)
        // mwk rapier: +1
        assertEquals(2L, atkResult.getBonusValueByRank(BonusRank.RANK_0)
                .getValue());
    }

    @Test
    public void merisielMeleeAtkBonusFirstValueWeaponFinesseSetupTest() {
        final BonusValueTuple atkResult
                = this.atkCalculationService.calcAtkBoni(
                        (Weapon) this.entityPoolService
                        .getEntityById(new UniqueId("mwkRapier")),
                        (DndCharacter) this.entityPoolService
                        .getEntityById(this.merisielId));

        // base atk: 0
        // weapon finesse: DEX mod: +3 (STR mod +1 NOT GRANTED!)
        // mwk rapier: +1
        assertEquals(4L, atkResult.getBonusValueByRank(BonusRank.RANK_0)
                .getValue());
    }

    @Test
    public void merisielMeleeAtkBonusFirstValueBattleaxeSetupTest() {
        final CharacterSetup setup = MerisielElfRogue1.getSetup();

        final StateRegistrySetup regSetup
                = (StateRegistrySetup) setup.getPropertySetup(
                        SetupProperty.STATE_REGISTRY);

        final StateRegistrySetup.StateRegistryEntrySetup entrySetup
                = new StateRegistrySetup.StateRegistryEntrySetup();

        entrySetup.setRegistryParticle("WEAPON_FINESSE_MODE");
        entrySetup.setContextObjectId("standardBattleaxe");
        entrySetup.setRegistryValue("true");
        entrySetup.setRegistryValueType("BOOLEAN");

        regSetup.addStateRegistryEntry(entrySetup);

        final PersonalizedBodySlotSetup hand1Setup
                = new PersonalizedBodySlotSetup();
        hand1Setup.setBodySlotType("primaryHand");
        hand1Setup.setItem("standardBattleaxe");
        hand1Setup.setIsPrimaryAttackSlot("true");

        // removal of rapier
        setup.getPropertySetups(SetupProperty.BODY_SLOTS).remove(0);
        // adding the battleaxe on primary hand
        setup.getPropertySetups(SetupProperty.BODY_SLOTS).add(hand1Setup);

        this.entityPoolService.registerUniqueEntity(this.dndCharacterFactory
                .newEntity(setup));

        final BonusValueTuple atkResult
                = this.atkCalculationService.calcAtkBoni(
                        (Weapon) this.entityPoolService
                        .getEntityById(new UniqueId("standardBattleaxe")),
                        (DndCharacter) this.entityPoolService
                        .getEntityById(this.merisielId));

        // base atk: 0
        // STR mod: +1 (weapon finesse DEX mod: +3 NOT GRANTED!)
        // unproficient with martial weapn battleaxe: -4
        assertEquals(-3L, atkResult.getBonusValueByRank(BonusRank.RANK_0)
                .getValue());
    }

    @Test
    public void merisielMeleeDamageBonusPrimHandTest() {
        final BonusValue damage = this.damageCalculationService.calcDamageBonus(
                (Weapon) this.entityPoolService
                .getEntityById(new UniqueId("mwkRapier")),
                (DndCharacter) this.entityPoolService
                .getEntityById(this.merisielId));

        // str-bonus -> +1
        assertEquals(1L, damage.getValue());
    }

    @Test
    public void merisielMeleeDamageBonusSecHandTest() {
        final PersonalizedBodySlotSetup hand2Setup
                = new PersonalizedBodySlotSetup();
        hand2Setup.setBodySlotType("secondaryHand");
        hand2Setup.setItem("mwkRapier");
        hand2Setup.setIsPrimaryAttackSlot("false");

        final CharacterSetup setup = MerisielElfRogue1.getSetup();
        // removal of all items
        setup.getPropertySetups(SetupProperty.BODY_SLOTS).clear();
        // adding the axe on sec hand
        setup.getPropertySetups(SetupProperty.BODY_SLOTS).add(hand2Setup);

        this.entityPoolService.registerUniqueEntity(this.dndCharacterFactory
                .newEntity(setup));

        final BonusValue damage = this.damageCalculationService.calcDamageBonus(
                (Weapon) this.entityPoolService
                .getEntityById(new UniqueId("mwkRapier")),
                (DndCharacter) this.entityPoolService
                .getEntityById(this.merisielId));

        // str-bonus -> +1 --> off-hand (*0.5) --> +0
        assertEquals(0L, damage.getValue());
    }

    @Test
    public void merisielMeleeDamageBonusBothHandsTest() {
        final PersonalizedBodySlotSetup hand1Setup
                = new PersonalizedBodySlotSetup();
        hand1Setup.setBodySlotType("primaryHand");
        hand1Setup.setItem("mwkRapier");
        hand1Setup.setIsPrimaryAttackSlot("true");
        final PersonalizedBodySlotSetup hand2Setup
                = new PersonalizedBodySlotSetup();
        hand2Setup.setBodySlotType("secondaryHand");
        hand2Setup.setItem("mwkRapier");
        hand2Setup.setIsPrimaryAttackSlot("false");

        final CharacterSetup setup = MerisielElfRogue1.getSetup();
        // removal of all items
        setup.getPropertySetups(SetupProperty.BODY_SLOTS).clear();
        // adding the sword on prim and sec hand
        setup.getPropertySetups(SetupProperty.BODY_SLOTS).add(hand1Setup);
        setup.getPropertySetups(SetupProperty.BODY_SLOTS).add(hand2Setup);

        this.entityPoolService.registerUniqueEntity(this.dndCharacterFactory
                .newEntity(setup));

        final BonusValue damage = this.damageCalculationService.calcDamageBonus(
                (Weapon) this.entityPoolService
                .getEntityById(new UniqueId("mwkRapier")),
                (DndCharacter) this.entityPoolService
                .getEntityById(this.merisielId));

        // str-bonus -> +1 --> two-hands (*1.5) NOT APPLICABLE WITH RAPIER!
        // --> +1
        assertEquals(1L, damage.getValue());
    }

    @Test
    public void merisielGetInitBonusTest() {
        final BonusValue initBonusResult
                = this.initiativeCalculationService.calcInitBonus(
                        (DndCharacter) this.entityPoolService
                        .getEntityById(this.merisielId));
        // dex mod: +3
        assertEquals(3L, initBonusResult.getValue());
    }

}
