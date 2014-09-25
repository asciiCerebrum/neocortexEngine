package org.asciicerebrum.mydndgame.interfaces.entities;

/**
 *
 * @author species8472
 */
public interface IWorkflow {

    /**
     * Executes this workflow instance.
     *
     * @param interaction The interaction with needed workflow parameters.
     */
    void runWorkflow(IInteraction interaction);

}
