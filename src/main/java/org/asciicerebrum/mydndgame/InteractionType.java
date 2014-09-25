package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.interfaces.entities.IInteractionType;
import org.asciicerebrum.mydndgame.interfaces.entities.IWorkflow;

/**
 *
 * @author species8472
 */
public class InteractionType implements IInteractionType {

    /**
     * The identifier of this type of interaction. E.g. attack, full attack,
     * etc.
     */
    private String id;

    /**
     * The workflow associated with this interaction type.
     */
    private IWorkflow workflow;

    /**
     * {@inheritDoc}
     */
    @Override
    public final String getId() {
        return this.id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setId(final String idInput) {
        this.id = idInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final IWorkflow getWorkflow() {
        return workflow;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setWorkflow(final IWorkflow workflowInput) {
        this.workflow = workflowInput;
    }

}
