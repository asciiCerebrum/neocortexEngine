package org.asciicerebrum.mydndgame.mechanics.interactionworkflows;

import org.asciicerebrum.mydndgame.domain.mechanics.interfaces.IWorkflow;
import org.asciicerebrum.mydndgame.domain.mechanics.interaction.Interaction;

/**
 *
 * @author species8472
 */
public class EndTurnWorkflow implements IWorkflow {

    /**
     * Subsequent workflow for ending the turn: expirations of conditions.
     */
    private IWorkflow conditionExpirationWorkflow;

    /**
     * {@inheritDoc}
     */
    @Override
    public final void runWorkflow(
            final Interaction interaction) {

        interaction.getCombatRound().moveToNextPosition();

        if (this.conditionExpirationWorkflow != null) {
            // run workflow conditionExpirationWorkflow
            this.conditionExpirationWorkflow.runWorkflow(
                    interaction);
        }
    }

    /**
     * @param conditionExpirationWorkflowInput the conditionExpirationWorkflow
     * to set
     */
    public final void setConditionExpirationWorkflow(
            final IWorkflow conditionExpirationWorkflowInput) {
        this.conditionExpirationWorkflow = conditionExpirationWorkflowInput;
    }

}
