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
     * @param response The response of previous interactions.
     *
     * @return The modified response of this and all previous interactions.
     */
    IInteractionResponse runWorkflow(IInteraction interaction,
            IInteractionResponse response);

}
