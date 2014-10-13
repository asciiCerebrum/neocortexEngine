package org.asciicerebrum.mydndgame.managers;

import java.util.List;
import org.asciicerebrum.mydndgame.Interaction;
import org.asciicerebrum.mydndgame.InteractionResponse;
import org.asciicerebrum.mydndgame.exceptions.CombatRoundInitializationException;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.ICombatRound;
import org.asciicerebrum.mydndgame.interfaces.entities.IInteraction;
import org.asciicerebrum.mydndgame.interfaces.entities.IInteractionResponse;
import org.asciicerebrum.mydndgame.interfaces.entities.IWorkflow;
import org.asciicerebrum.mydndgame.interfaces.entities.InteractionResponseKey;

/**
 *
 * @author species8472
 */
public class CombatRoundManager {

    /**
     * The combat round that is currently active.
     */
    private ICombatRound currentCombatRound;

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
     * @param participants The participants of the combat round.
     * @return true if combat round could be initiated, false otherwise.
     */
    public final Boolean initiateCombatRound(
            final List<ICharacter> participants) {
        if (this.currentCombatRound != null) {
            return Boolean.FALSE;
        }

        IInteraction interaction = new Interaction();
        interaction.setTargetCharacters(participants);

        IInteractionResponse initResponse
                = this.initializeCombatRoundWorkflow.runWorkflow(interaction);

        this.currentCombatRound = initResponse.getValue(
                InteractionResponseKey.COMBAT_ROUND, ICombatRound.class);
        if (this.currentCombatRound == null) {
            throw new CombatRoundInitializationException();
        }

        // first participant in row must get rid of the flat footed condition.
        this.conditionExpirationWorkflow.runWorkflow(interaction, initResponse);

        return Boolean.TRUE;
    }

    /**
     * Resumes a combat round from a persisted state. This is some kind of
     * builder that makes a combatRound object out of a combatRoundSetup object.
     *
     * @param combatRound the combat round to resume.
     * @return true if combat round could be resumed, false otherwise.
     */
    public final Boolean resumeCombatRound(final ICombatRound combatRound) {
        if (this.currentCombatRound != null) {
            return Boolean.FALSE;
        }

        this.currentCombatRound = combatRound;

        return Boolean.TRUE;
    }

    /**
     * Validates if it is the turn of a given character.
     *
     * @param character The character in question.
     * @return whether it is her turn or not.
     */
    public final Boolean isCurrentParticipant(final ICharacter character) {
        if (this.currentCombatRound == null) {
            return Boolean.FALSE;
        }

        return character.equals(
                this.currentCombatRound.getCurrentParticipant());
    }

    /**
     * Retrieves the desired interaction from the character and executes it.
     * Rejects it, if it comes from the wrong character.
     *
     * @param interaction the interaction in question.
     */
    public final void executeInteraction(final IInteraction interaction) {

        // reject when it's not the characters turn.
        if (!this.isCurrentParticipant(interaction.getTriggeringCharacter())) {
            return;
        }

        IInteractionResponse response = new InteractionResponse();
        response.setValue(InteractionResponseKey.COMBAT_ROUND,
                this.currentCombatRound);

        for (IWorkflow workflow
                : interaction.getInteractionType().getWorkflows()) {
            response = workflow.runWorkflow(interaction, response);
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
