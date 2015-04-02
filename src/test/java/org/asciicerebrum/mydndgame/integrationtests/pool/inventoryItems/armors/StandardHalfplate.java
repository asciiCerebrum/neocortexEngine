package org.asciicerebrum.mydndgame.integrationtests.pool.inventoryItems.armors;

import org.asciicerebrum.mydndgame.domain.setup.ArmorSetup;

/**
 *
 * @author species8472
 */
public class StandardHalfplate {

    public static ArmorSetup getSetup() {
        final ArmorSetup armor = new ArmorSetup();

        armor.setId("standardHalfPlate");
        armor.setName("halfPlate");
        armor.setSizeCategory("medium");

        return armor;
    }
}
