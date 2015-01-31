package org.asciicerebrum.mydndgame.domain.core.attribution;

import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.interactionworkflows.Workflows;

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
    public UniqueId getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(UniqueId id) {
        this.id = id;
    }

    /**
     * @return the workflows
     */
    public Workflows getWorkflows() {
        return workflows;
    }

    /**
     * @param workflows the workflows to set
     */
    public void setWorkflows(Workflows workflows) {
        this.workflows = workflows;
    }

}
