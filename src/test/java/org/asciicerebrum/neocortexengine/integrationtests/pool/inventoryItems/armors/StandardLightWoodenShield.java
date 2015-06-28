package org.asciicerebrum.neocortexengine.integrationtests.pool.inventoryItems.armors;

import org.asciicerebrum.neocortexengine.domain.setup.ArmorSetup;

/**
 *
 * @author species8472
 */
public class StandardLightWoodenShield {

    public static ArmorSetup getSetup() {
        final ArmorSetup armor = new ArmorSetup();

        armor.setId("standardLightWoodenShield");
        armor.setName("lightWoodenShield");
        armor.setSizeCategory("medium");

        return armor;
    }
}
