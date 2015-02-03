package org.asciicerebrum.mydndgame.domain.mechanics.interfaces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author species8472
 */
public class Damages {
    
    private final List<Damage> elements = new ArrayList<Damage>();
    
    public Damages(final Damage... damagesInput) {
        this.elements.addAll(Arrays.asList(damagesInput));
    }    
    
    public final void addDamage(final Damage damage) {
        this.elements.add(damage);
    }
    
    public final Iterator<Damage> iterator() {
        return this.elements.iterator();
    }
    
}
