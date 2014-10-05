package org.asciicerebrum.mydndgame.interfaces.entities;

import java.util.List;

/**
 *
 * @author species8472
 */
public interface IInteractionType extends Identifiable {

    /**
     * @return the workflows
     */
    List<IWorkflow> getWorkflows();

    /**
     * @param workflows the workflows to set
     */
    void setWorkflows(List<IWorkflow> workflows);

}
