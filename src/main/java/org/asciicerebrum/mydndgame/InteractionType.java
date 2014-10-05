package org.asciicerebrum.mydndgame;

import java.util.List;
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
     * The workflows associated with this interaction type.
     */
    private List<IWorkflow> workflows;

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
    public final List<IWorkflow> getWorkflows() {
        return workflows;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setWorkflows(final List<IWorkflow> workflowsInput) {
        this.workflows = workflowsInput;
    }

}
