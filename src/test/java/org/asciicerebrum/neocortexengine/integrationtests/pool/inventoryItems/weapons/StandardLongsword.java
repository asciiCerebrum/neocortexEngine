package org.asciicerebrum.neocortexengine.integrationtests.pool.inventoryItems.weapons;

import org.asciicerebrum.neocortexengine.domain.setup.WeaponSetup;

/**
 *
 * @author species8472
 */
public class StandardLongsword {

    public static WeaponSetup getSetup() {
        final WeaponSetup weapon = new WeaponSetup();

        weapon.setId("standardLongsword");
        weapon.setName("longsword");
        weapon.setSizeCategory("medium");

        return weapon;
    }

}
