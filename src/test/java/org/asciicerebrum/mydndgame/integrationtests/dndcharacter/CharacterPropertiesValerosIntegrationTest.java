package org.asciicerebrum.mydndgame.integrationtests.dndcharacter;

import com.google.common.collect.Iterators;
import org.asciicerebrum.mydndgame.domain.core.particles.ArmorClass;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusRank;
import org.asciicerebrum.mydndgame.domain.core.particles.BonusValueTuple;
import org.asciicerebrum.mydndgame.domain.core.particles.HitPoints;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.factories.ArmorFactory;
import org.asciicerebrum.mydndgame.domain.factories.DndCharacterFactory;
import org.asciicerebrum.mydndgame.domain.factories.WeaponFactory;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.integrationtests.pool.dndCharacters.ValerosHumanFighter1;
import org.asciicerebrum.mydndgame.integrationtests.pool.inventoryItems.armors.MwkChainmail;
import org.asciicerebrum.mydndgame.integrationtests.pool.inventoryItems.weapons.StandardLongsword;
import org.asciicerebrum.mydndgame.services.context.EntityPoolService;
import org.asciicerebrum.mydndgame.services.statistics.AcCalculationService;
import org.asciicerebrum.mydndgame.services.statistics.HpCalculationService;
import org.asciicerebrum.mydndgame.testcategories.IntegrationTest;
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

    private UniqueId valerosId;

    @Before
    public void setUp() {

        this.entityPoolService.registerUniqueEntity(this.dndCharacterFactory
                .newEntity(ValerosHumanFighter1.getSetup()));

        this.entityPoolService.registerUniqueEntity(this.armorFactory
                .newEntity(MwkChainmail.getSetup()));
        this.entityPoolService.registerUniqueEntity(this.weaponFactory
                .newEntity(StandardLongsword.getSetup()));

        this.valerosId = new UniqueId("valeros");
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

}
