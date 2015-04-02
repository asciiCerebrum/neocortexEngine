package org.asciicerebrum.mydndgame.integrationtests.weapon;

import org.asciicerebrum.mydndgame.domain.game.Weapon;
import org.asciicerebrum.mydndgame.domain.setup.WeaponSetup;
import org.asciicerebrum.mydndgame.domain.factories.WeaponFactory;
import org.asciicerebrum.mydndgame.testcategories.IntegrationTest;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author species8472
 */
@Category(IntegrationTest.class)
public class WeaponCreationIntegrationTest {

    private static final String AXE4HARSK_ID = "axe4Harsk";

    private Weapon sword4Valeros;
    private Weapon axe4Harsk;

    @Before
    public void setUp() {
        ApplicationContext context
                = new ClassPathXmlApplicationContext(
                        new String[]{"applicationContext.xml"});

        WeaponSetup sword4ValerosSetup = new WeaponSetup();
        sword4ValerosSetup.setId("sword4Valeros");
        sword4ValerosSetup.setName("longsword");
        sword4ValerosSetup.setSizeCategory("medium");

        WeaponSetup axe4HarskSetup = new WeaponSetup();
        axe4HarskSetup.setId(AXE4HARSK_ID);
        axe4HarskSetup.setName("battleaxe");
        axe4HarskSetup.setSizeCategory("medium");

        this.sword4Valeros
                = (Weapon) new WeaponFactory().newEntity(sword4ValerosSetup);
        this.axe4Harsk
                = (Weapon) new WeaponFactory().newEntity(axe4HarskSetup);
    }

    @Test
    public void testIdHarsk() {
        assertEquals(AXE4HARSK_ID, this.axe4Harsk.getUniqueId().getValue());
    }
}
