package org.asciicerebrum.mydndgame.domain.mechanics.workflow;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author species8472
 */
public class Workflows {

    /**
     * Central collection of workflows.
     */
    private final List<IWorkflow> elements = new ArrayList<IWorkflow>();

    /**
     * Adds a futher workflow to the collection.
     *
     * @param workflow the workflow to add.
     */
    public final void addWorkflow(final IWorkflow workflow) {
        this.elements.add(workflow);
    }

    /**
     * Iterator over the collection of workflows.
     *
     * @return the iterator.
     */
    public final Iterator<IWorkflow> iterator() {
        return this.elements.iterator();
    }

}
