package org.asciicerebrum.neocortexengine.integrationtests.pool.inventoryItems.weapons;

import org.asciicerebrum.neocortexengine.domain.setup.WeaponSetup;

/**
 *
 * @author species8472
 */
public class StandardLongbow {

    public static WeaponSetup getSetup() {
        final WeaponSetup weapon = new WeaponSetup();

        weapon.setId("standardLongbow");
        weapon.setName("longbow");
        weapon.setSizeCategory("medium");

        return weapon;
    }

}
