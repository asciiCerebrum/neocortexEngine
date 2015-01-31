package org.asciicerebrum.mydndgame.domain.rules.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author species8472
 */
public class WeaponPrototypes {

    private final List<WeaponPrototype> elements
            = new ArrayList<WeaponPrototype>();

    public final void add(final WeaponPrototype weaponPrototype) {
        this.elements.add(weaponPrototype);
    }

    public final Iterator<WeaponPrototype> iterator() {
        return this.elements.iterator();
    }

    public final boolean contains(final WeaponPrototype weaponPrototype) {
        return this.elements.contains(weaponPrototype);
    }

}
