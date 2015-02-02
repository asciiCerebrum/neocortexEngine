package org.asciicerebrum.mydndgame.domain.mechanics.interfaces;

import org.asciicerebrum.mydndgame.domain.transfer.Interaction;

/**
 *
 * @author species8472
 */
public interface IWorkflow {

    /**
     * Executes this workflow instance without an incoming response. Mostly if
     * this is the first invocation in a row of workflows.
     *
     * @param interaction The interaction with needed workflow parameters.
     */
    void runWorkflow(Interaction interaction);
}
