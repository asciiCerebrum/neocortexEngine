package org.asciicerebrum.mydndgame.integrationtests.pool.inventoryItems.weapons;

import org.asciicerebrum.mydndgame.domain.setup.WeaponSetup;

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
