package org.asciicerebrum.mydndgame.interfaces.entities;

/**
 *
 * @author species8472
 */
public interface IInteractionType extends Identifiable {

    /**
     * @return the workflow
     */
    IWorkflow getWorkflow();

    /**
     * @param workflow the workflow to set
     */
    void setWorkflow(IWorkflow workflow);

}
