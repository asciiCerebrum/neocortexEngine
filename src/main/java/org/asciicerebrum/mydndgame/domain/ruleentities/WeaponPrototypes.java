package org.asciicerebrum.mydndgame.domain.ruleentities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author species8472
 */
public class WeaponPrototypes {

    /**
     * Central collection of weapon prototypes.
     */
    private final List<WeaponPrototype> elements
            = new ArrayList<WeaponPrototype>();

    /**
     * Adds a further weapon prototype to the collection.
     *
     * @param weaponPrototype the prototype to add.
     */
    public final void add(final WeaponPrototype weaponPrototype) {
        this.elements.add(weaponPrototype);
    }

    /**
     * Iterator over the collection of weapon prototypes.
     *
     * @return the iterator.
     */
    public final Iterator<WeaponPrototype> iterator() {
        return this.elements.iterator();
    }

    /**
     * Tests if given weapon prototype is part of the collection.
     *
     * @param weaponPrototype the weapon prototype in question.
     * @return true if part of the collection, false otherwise.
     */
    public final boolean contains(final WeaponPrototype weaponPrototype) {
        return this.elements.contains(weaponPrototype);
    }

}
