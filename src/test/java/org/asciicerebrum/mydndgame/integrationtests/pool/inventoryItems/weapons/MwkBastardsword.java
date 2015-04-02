package org.asciicerebrum.mydndgame.integrationtests.pool.inventoryItems.weapons;

import org.asciicerebrum.mydndgame.domain.setup.WeaponSetup;

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
