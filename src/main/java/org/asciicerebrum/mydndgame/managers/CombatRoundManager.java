package org.asciicerebrum.mydndgame.managers;

import java.util.Iterator;
import javax.naming.OperationNotSupportedException;
import org.asciicerebrum.mydndgame.domain.game.Campaign;
import org.asciicerebrum.mydndgame.domain.game.entities.DndCharacters;
import org.asciicerebrum.mydndgame.domain.transfer.Interaction;
import org.asciicerebrum.mydndgame.interactionworkflows.IWorkflow;

/**
 *
 * @author species8472
 */
public class CombatRoundManager {

    /**
     * Workflow for initialization of a combat encounter.
     */
    private IWorkflow initializeCombatRoundWorkflow;

    /**
     * Workflow for the expiry of conditions.
     */
    private IWorkflow conditionExpirationWorkflow;

    /**
     * Initiates the combat round. Only one round can be initiated at the same
     * time. If one is already initiated, false is returned.
     *
     * @param campaign contains the combat round.
     * @param participants The participants of the combat round.
     * @return true if combat round could be initiated, false otherwise.
     * @throws javax.naming.OperationNotSupportedException could be thrown due
     * to the call of other workflows.
     */
    public final Boolean initiateCombatRound(final Campaign campaign,
            final DndCharacters participants)
            throws OperationNotSupportedException {

        Interaction interaction = new Interaction();
        interaction.setTargetCharacters(participants);

        if (this.initializeCombatRoundWorkflow == null) {
            // in this case it does not make sense to continue.
            return Boolean.TRUE;
        }

        this.initializeCombatRoundWorkflow.runWorkflow(interaction);

        campaign.setCombatRound(interaction.getCombatRound());

        // this workflow can also be not setup!
        if (this.conditionExpirationWorkflow != null) {
            // first participant in row must get rid of the flat footed
            // condition.
            this.conditionExpirationWorkflow.runWorkflow(interaction);
        }

        return Boolean.TRUE;
    }

    /**
     * Retrieves the desired interaction from the character and executes it.
     * Rejects it, if it comes from the wrong character.
     *
     * @param campaign
     * @param interaction the interaction in question.
     */
    public final void executeInteraction(final Campaign campaign,
            final Interaction interaction) {

        // reject when it's not the characters turn.
        if (!campaign.getCombatRound().isCurrentParticipant(
                interaction.getTriggeringCharacter())) {
            return;
        }

        final Iterator<IWorkflow> wfIterator
                = interaction.getInteractionType().getWorkflows().iterator();
        while (wfIterator.hasNext()) {
            final IWorkflow workflow = wfIterator.next();
            workflow.runWorkflow(interaction);
        }
    }

    /**
     * @param initializeCombatRoundWorkflowInput the
     * initializeCombatRoundWorkflow to set
     */
    public final void setInitializeCombatRoundWorkflow(
            final IWorkflow initializeCombatRoundWorkflowInput) {
        this.initializeCombatRoundWorkflow = initializeCombatRoundWorkflowInput;
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
