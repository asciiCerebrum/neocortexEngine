package org.asciicerebrum.mydndgame.domain.gameentities.prototypes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author species8472
 */
public class WeaponPrototypes {

    private final List<WeaponPrototype> weaponPrototypes
            = new ArrayList<WeaponPrototype>();

    public final void add(final WeaponPrototype weaponPrototype) {
        this.weaponPrototypes.add(weaponPrototype);
    }

    public final Iterator<WeaponPrototype> iterator() {
        return this.weaponPrototypes.iterator();
    }

    public final boolean contains(final WeaponPrototype weaponPrototype) {
        return this.weaponPrototypes.contains(weaponPrototype);
    }

}
