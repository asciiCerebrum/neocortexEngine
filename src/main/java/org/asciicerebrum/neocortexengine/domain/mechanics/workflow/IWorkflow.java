package org.asciicerebrum.neocortexengine.domain.mechanics.workflow;

import org.asciicerebrum.neocortexengine.domain.game.Campaign;

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
     * @param campaign The campaign the interaction takes place in.
     */
    void runWorkflow(Interaction interaction, Campaign campaign);
}
