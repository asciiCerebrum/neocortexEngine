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
     * @return The modified response of this and all previous interactions.
     */
    IInteractionResponse runWorkflow(IInteraction interaction,
            IInteractionResponse response);

    /**
     * Executes this workflow instance without an incoming response. Mostly if
     * this is the first invocation in a row of workflows.
     *
     * @param interaction The interaction with needed workflow parameters.
     * @return the new response of this interaction.
     */
    IInteractionResponse runWorkflow(IInteraction interaction);
}
