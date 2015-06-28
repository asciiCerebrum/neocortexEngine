package org.asciicerebrum.neocortexengine.integrationtests.pool.inventoryItems.armors;

import org.asciicerebrum.neocortexengine.domain.setup.ArmorSetup;

/**
 *
 * @author species8472
 */
public class MwkHeavySteelShield {

    public static ArmorSetup getSetup() {
        final ArmorSetup armor = new ArmorSetup();

        armor.setId("mwkHeavySteelShield");
        armor.setName("heavySteelShield");
        armor.setSizeCategory("medium");

        armor.addSpecialAbility("masterworkArmor");

        return armor;
    }
}
