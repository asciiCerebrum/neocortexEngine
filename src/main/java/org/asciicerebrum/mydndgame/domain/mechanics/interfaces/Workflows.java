package org.asciicerebrum.mydndgame.domain.mechanics.interfaces;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author species8472
 */
public class Workflows {

    private final List<IWorkflow> elements = new ArrayList<IWorkflow>();

    public final void addWorkflow(final IWorkflow workflow) {
        this.elements.add(workflow);
    }

    public final Iterator<IWorkflow> iterator() {
        return this.elements.iterator();
    }

}
