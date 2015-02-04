package org.asciicerebrum.mydndgame.domain.ruleentities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author species8472
 */
public class FeatBindings {

    private final List<FeatBinding> elements = new ArrayList<FeatBinding>();

    public final void add(final FeatBinding featBinding) {
        this.elements.add(featBinding);
    }

    public final boolean contains(final FeatBinding featBinding) {
        return this.elements.contains(featBinding);
    }

    public final Iterator<FeatBinding> iterator() {
        return this.elements.iterator();
    }

}
