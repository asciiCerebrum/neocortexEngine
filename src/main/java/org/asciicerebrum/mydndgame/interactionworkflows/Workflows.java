package org.asciicerebrum.mydndgame.interactionworkflows;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author species8472
 */
public class Workflows {

    private final List<IWorkflow> workflows = new ArrayList<IWorkflow>();

    public final void addWorkflow(final IWorkflow workflow) {
        this.workflows.add(workflow);
    }

    public final Iterator<IWorkflow> iterator() {
        return this.workflows.iterator();
    }

}
