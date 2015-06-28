package org.asciicerebrum.neocortexengine.integrationtests.pool.inventoryItems.weapons;

import org.asciicerebrum.neocortexengine.domain.setup.WeaponSetup;

/**
 *
 * @author species8472
 */
public class StandardDagger {

    public static WeaponSetup getSetup() {
        final WeaponSetup weapon = new WeaponSetup();

        weapon.setId("standardDagger");
        weapon.setName("dagger");
        weapon.setSizeCategory("medium");

        return weapon;
    }

}
