package org.asciicerebrum.neocortexengine.integrationtests.pool.inventoryItems.weapons;

import org.asciicerebrum.neocortexengine.domain.setup.WeaponSetup;

/**
 *
 * @author species8472
 */
public class MwkBastardsword {

    public static WeaponSetup getSetup() {
        final WeaponSetup weapon = new WeaponSetup();

        weapon.setId("mwkBastardsword");
        weapon.setName("bastardsword");
        weapon.setSizeCategory("medium");

        weapon.addSpecialAbility("masterworkWeapon");

        return weapon;
    }

}
