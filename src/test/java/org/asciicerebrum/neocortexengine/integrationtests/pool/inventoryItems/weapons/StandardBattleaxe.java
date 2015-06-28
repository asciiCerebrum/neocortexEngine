package org.asciicerebrum.neocortexengine.integrationtests.pool.inventoryItems.weapons;

import org.asciicerebrum.neocortexengine.domain.setup.WeaponSetup;

/**
 *
 * @author species8472
 */
public class StandardBattleaxe {

    public static WeaponSetup getSetup() {
        final WeaponSetup weapon = new WeaponSetup();

        weapon.setId("standardBattleaxe");
        weapon.setName("battleaxe");
        weapon.setSizeCategory("medium");

        return weapon;
    }

}
