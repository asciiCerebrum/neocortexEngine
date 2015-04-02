package org.asciicerebrum.mydndgame.integrationtests.pool.inventoryItems.armors;

import org.asciicerebrum.mydndgame.domain.setup.ArmorSetup;

/**
 *
 * @author species8472
 */
public class StandardStuddedLeather {

    public static ArmorSetup getSetup() {
        final ArmorSetup armor = new ArmorSetup();

        armor.setId("standardStuddedLeather");
        armor.setName("studdedLeather");
        armor.setSizeCategory("medium");

        return armor;
    }
}
