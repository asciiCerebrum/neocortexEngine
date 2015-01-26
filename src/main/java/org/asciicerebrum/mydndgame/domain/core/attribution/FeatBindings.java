package org.asciicerebrum.mydndgame.domain.core.attribution;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author species8472
 */
public class FeatBindings {

    private final List<FeatBinding> featBindings = new ArrayList<FeatBinding>();

    public final void add(final FeatBinding featBinding) {
        this.featBindings.add(featBinding);
    }

    public final boolean contains(final FeatBinding featBinding) {
        return this.featBindings.contains(featBinding);
    }

    public final Iterator<FeatBinding> iterator() {
        return this.featBindings.iterator();
    }

}
