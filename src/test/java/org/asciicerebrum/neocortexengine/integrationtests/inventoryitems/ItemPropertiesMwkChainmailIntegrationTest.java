package org.asciicerebrum.neocortexengine.integrationtests.inventoryitems;

import org.asciicerebrum.neocortexengine.domain.core.particles.Cost;
import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueId;
import org.asciicerebrum.neocortexengine.domain.factories.ArmorFactory;
import org.asciicerebrum.neocortexengine.domain.factories.DndCharacterFactory;
import org.asciicerebrum.neocortexengine.domain.game.Armor;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.facades.game.ArmorServiceFacade;
import org.asciicerebrum.neocortexengine.integrationtests.pool.dndCharacters.ValerosHumanFighter1;
import org.asciicerebrum.neocortexengine.integrationtests.pool.inventoryItems.armors.MwkChainmail;
import org.asciicerebrum.neocortexengine.services.context.EntityPoolService;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author species8472
 */
@Category(IntegrationTest.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class ItemPropertiesMwkChainmailIntegrationTest {

    private static final Logger LOG
            = LoggerFactory.getLogger(
                    ItemPropertiesMwkChainmailIntegrationTest.class);

    @Autowired
    private EntityPoolService entityPoolService;

    @Autowired
    private ArmorFactory armorFactory;

    @Autowired
    private DndCharacterFactory dndCharacterFactory;

    @Autowired
    private ArmorServiceFacade armorServiceFacade;

    private UniqueId mwkChainmailId;

    private UniqueId valerosId;

    @Before
    public void setUp() {

        this.entityPoolService.registerUniqueEntity(this.armorFactory
                .newEntity(MwkChainmail.getSetup()));

        this.entityPoolService.registerUniqueEntity(this.dndCharacterFactory
                .newEntity(ValerosHumanFighter1.getSetup()));

        this.mwkChainmailId = new UniqueId("mwkChainmail");
        this.valerosId = new UniqueId("valeros");
    }

    @After
    public void tearDown() {
        this.entityPoolService.empty();
    }

    @Test
    public void mwkChainmailHasMwkFeature() {
        final Armor armor
                = (Armor) this.entityPoolService.getEntityById(
                        this.mwkChainmailId);
        assertEquals("masterworkArmor", armor.getSpecialAbilities().iterator()
                .next().getUniqueId().getValue());
    }

    @Test
    public void mwkChainmailCostTest() {
        final DndCharacter dndCharacter = new DndCharacter();
        dndCharacter.setUniqueId(new UniqueId("dndCharacter"));

        LOG.debug("Testing mwkChainmailCostTest()...");
        final Cost result
                = this.armorServiceFacade.getCost(
                        (Armor) this.entityPoolService.getEntityById(
                                this.mwkChainmailId), dndCharacter);
        LOG.debug("...done.");
        // item cost: 150
        // mwk: 150
        assertEquals(300L, result.getValue());
    }

    @Test
    public void mwkChainmailCostWhenWornTest() {
        final Cost result
                = this.armorServiceFacade.getCost(
                        (Armor) this.entityPoolService.getEntityById(
                                this.mwkChainmailId),
                        (DndCharacter) this.entityPoolService.getEntityById(
                                this.valerosId));
        // item cost: 150
        // mwk: 150
        // wearing the armor MUST NOT affect the cost, of course!
        assertEquals(300L, result.getValue());
    }

}
