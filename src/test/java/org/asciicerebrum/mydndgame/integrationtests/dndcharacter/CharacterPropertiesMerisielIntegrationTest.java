package org.asciicerebrum.mydndgame.integrationtests.dndcharacter;

import org.asciicerebrum.mydndgame.domain.core.particles.BonusRank;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValueTuple;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.factories.ArmorFactory;
import org.asciicerebrum.mydndgame.domain.factories.DndCharacterFactory;
import org.asciicerebrum.mydndgame.domain.factories.WeaponFactory;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.domain.setup.CharacterSetup;
import org.asciicerebrum.mydndgame.integrationtests.pool.dndCharacters.MerisielElfRogue1;
import org.asciicerebrum.mydndgame.integrationtests.pool.inventoryItems.armors.StandardStuddedLeather;
import org.asciicerebrum.mydndgame.integrationtests.pool.inventoryItems.weapons.MwkRapier;
import org.asciicerebrum.mydndgame.services.context.EntityPoolService;
import org.asciicerebrum.mydndgame.services.statistics.AtkCalculationService;
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

    private UniqueId merisielId;

    @Before
    public void setUp() {

        this.entityPoolService.registerUniqueEntity(this.dndCharacterFactory
                .newEntity(MerisielElfRogue1.getSetup()));

        this.entityPoolService.registerUniqueEntity(this.armorFactory
                .newEntity(StandardStuddedLeather.getSetup()));
        this.entityPoolService.registerUniqueEntity(this.weaponFactory
                .newEntity(MwkRapier.getSetup()));

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

}
