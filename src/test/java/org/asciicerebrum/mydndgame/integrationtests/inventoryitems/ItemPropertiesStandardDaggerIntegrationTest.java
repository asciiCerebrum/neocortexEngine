package org.asciicerebrum.mydndgame.integrationtests.inventoryitems;

import org.asciicerebrum.mydndgame.domain.core.particles.Cost;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.factories.WeaponFactory;
import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.facades.game.WeaponServiceFacade;
import org.asciicerebrum.mydndgame.integrationtests.pool.inventoryItems.weapons.StandardDagger;
import org.asciicerebrum.mydndgame.services.context.EntityPoolService;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author species8472
 */
@Category(IntegrationTest.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class ItemPropertiesStandardDaggerIntegrationTest {

    private static final Logger LOG
            = LoggerFactory.getLogger(
                    ItemPropertiesStandardDaggerIntegrationTest.class);

    @Autowired
    private EntityPoolService entityPoolService;

    @Autowired
    private WeaponFactory weaponFactory;

    @Autowired
    private WeaponServiceFacade weaponServiceFacade;

    private UniqueId daggerId;

    @Before
    public void setUp() {

        this.entityPoolService.registerUniqueEntity(this.weaponFactory
                .newEntity(StandardDagger.getSetup()));

        this.daggerId = new UniqueId("standardDagger");
    }

    @After
    public void tearDown() {
        this.entityPoolService.empty();
    }

    @Test
    public void mwkChainmailCostTest() {
        final Cost result
                = this.weaponServiceFacade.getCost(
                        (Weapon) this.entityPoolService.getEntityById(
                                this.daggerId));
        // item cost: 2
        assertEquals(2L, result.getValue());
    }

}
