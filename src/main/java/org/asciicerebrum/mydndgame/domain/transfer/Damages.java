package org.asciicerebrum.mydndgame.domain.transfer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author species8472
 */
public class Damages {
    
    private final List<Damage> damages = new ArrayList<Damage>();
    
    public Damages(final Damage... damagesInput) {
        this.damages.addAll(Arrays.asList(damagesInput));
    }    
    
    public final void addDamage(final Damage damage) {
        this.damages.add(damage);
    }
    
    public final Iterator<Damage> iterator() {
        return this.damages.iterator();
    }
    
}
