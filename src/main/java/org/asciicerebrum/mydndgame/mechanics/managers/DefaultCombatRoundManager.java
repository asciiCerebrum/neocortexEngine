package org.asciicerebrum.mydndgame.mechanics.managers;

import java.util.Iterator;
import javax.naming.OperationNotSupportedException;
import org.asciicerebrum.mydndgame.domain.game.Campaign;
import org.asciicerebrum.mydndgame.domain.game.DndCharacters;
import org.asciicerebrum.mydndgame.domain.mechanics.workflow.IWorkflow;
import org.asciicerebrum.mydndgame.domain.mechanics.workflow.Interaction;

/**
 *
 * @author species8472
 */
public class DefaultCombatRoundManager implements CombatRoundManager {

    /**
     * Workflow for initialization of a combat encounter.
     */
    private IWorkflow initializeCombatRoundWorkflow;

    /**
     * Workflow for the expiry of conditions.
     */
    private IWorkflow conditionExpirationWorkflow;

    @Override
    public final void initiateCombatRound(final Campaign campaign,
            final DndCharacters participants)
            throws OperationNotSupportedException {

        Interaction interaction = new Interaction();
        interaction.setTargetCharacters(participants);

        if (this.initializeCombatRoundWorkflow != null) {
            this.initializeCombatRoundWorkflow.runWorkflow(interaction);
            campaign.setCombatRound(interaction.getCombatRound());
        }

        // this workflow can also be not setup!
        if (this.conditionExpirationWorkflow != null) {
            // first participant in row must get rid of the flat footed
            // condition.
            this.conditionExpirationWorkflow.runWorkflow(interaction);
        }
    }

    @Override
    public final void executeInteraction(final Campaign campaign,
            final Interaction interaction) {

        // reject when it's not the characters turn.
        if (!campaign.getCombatRound().isCurrentParticipant(
                interaction.getTriggeringCharacter().getUniqueId())) {
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
