package org.asciicerebrum.mydndgame.domain.mechanics.workflow;

import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;

/**
 *
 * @author species8472
 */
public class InteractionType {

    /**
     * The identifier of this type of interaction. E.g. attack, full attack,
     * etc.
     */
    private UniqueId id;

    /**
     * The workflows associated with this interaction type.
     */
    private Workflows workflows;

    /**
     * @return the id
     */
    public final UniqueId getId() {
        return id;
    }

    /**
     * @param idInput the id to set
     */
    public final void setId(final UniqueId idInput) {
        this.id = idInput;
    }

    /**
     * @return the workflows
     */
    public final Workflows getWorkflows() {
        return workflows;
    }

    /**
     * @param workflowsInput the workflows to set
     */
    public final void setWorkflows(final Workflows workflowsInput) {
        this.workflows = workflowsInput;
    }

}
