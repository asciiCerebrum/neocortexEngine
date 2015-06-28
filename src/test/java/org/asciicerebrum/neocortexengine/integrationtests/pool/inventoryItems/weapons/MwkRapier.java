package org.asciicerebrum.neocortexengine.integrationtests.pool.inventoryItems.weapons;

import org.asciicerebrum.neocortexengine.domain.setup.WeaponSetup;

/**
 *
 * @author species8472
 */
public class MwkRapier {
    
    public static WeaponSetup getSetup() {
        final WeaponSetup weapon = new WeaponSetup();
        
        weapon.setId("mwkRapier");
        weapon.setName("rapier");
        weapon.setSizeCategory("medium");
        
        weapon.addSpecialAbility("masterworkWeapon");
        
        return weapon;
    }

}
