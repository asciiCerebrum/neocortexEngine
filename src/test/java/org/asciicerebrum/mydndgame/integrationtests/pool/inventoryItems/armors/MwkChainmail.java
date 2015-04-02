package org.asciicerebrum.mydndgame.integrationtests.pool.inventoryItems.armors;

import org.asciicerebrum.mydndgame.domain.setup.ArmorSetup;

/**
 *
 * @author species8472
 */
public class MwkChainmail {

    public static ArmorSetup getSetup() {
        final ArmorSetup armor = new ArmorSetup();

        armor.setId("mwkChainmail");
        armor.setName("chainmail");
        armor.setSizeCategory("medium");

        armor.addSpecialAbility("masterworkArmor");

        return armor;
    }
}
