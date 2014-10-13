package org.asciicerebrum.mydndgame.interactionworkflows;

import org.asciicerebrum.mydndgame.InteractionResponse;
import org.asciicerebrum.mydndgame.interfaces.entities.ICombatRound;
import org.asciicerebrum.mydndgame.interfaces.entities.IInteraction;
import org.asciicerebrum.mydndgame.interfaces.entities.IInteractionResponse;
import org.asciicerebrum.mydndgame.interfaces.entities.IWorkflow;
import org.asciicerebrum.mydndgame.interfaces.entities.InteractionResponseKey;

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
    public final IInteractionResponse runWorkflow(
            final IInteraction interaction) {
        return this.runWorkflow(interaction, new InteractionResponse());
    }

    /**
     * {@inheritDoc} Moving to the next turn in the combat round.
     */
    @Override
    public final IInteractionResponse runWorkflow(
            final IInteraction interaction,
            final IInteractionResponse response) {

        //TODO move this code into an abstract parent class so that is is
        // automatically available - for all workflows!
        ICombatRound combatRound
                = response.getValue(InteractionResponseKey.COMBAT_ROUND,
                        ICombatRound.class);

        combatRound.moveToNextPosition();

        // run workflow conditionExpirationWorkflow
        final IInteractionResponse responseFinal
                = this.conditionExpirationWorkflow.runWorkflow(
                        interaction, response);

        return responseFinal;
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
