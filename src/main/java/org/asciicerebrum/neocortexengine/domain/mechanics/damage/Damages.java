package org.asciicerebrum.neocortexengine.domain.mechanics.damage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author species8472
 */
public class Damages {

    /**
     * Central collection of damages.
     */
    private final List<Damage> elements = new ArrayList<Damage>();

    /**
     * Constructing the damages collection out of a list.
     *
     * @param damagesInput the list of damages.
     */
    public Damages(final Damage... damagesInput) {
        this.elements.addAll(Arrays.asList(damagesInput));
    }

    /**
     * Adds a single damage to the collection.
     *
     * @param damage the damage to add.
     */
    public final void addDamage(final Damage damage) {
        this.elements.add(damage);
    }

    /**
     * Iterator over the collection of damages.
     *
     * @return the iterator.
     */
    public final Iterator<Damage> iterator() {
        return this.elements.iterator();
    }

}
