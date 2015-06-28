package org.asciicerebrum.neocortexengine.mechanics.managers;

import java.util.Iterator;
import javax.naming.OperationNotSupportedException;
import org.asciicerebrum.neocortexengine.domain.events.EventEntry;
import org.asciicerebrum.neocortexengine.domain.events.EventType;
import org.asciicerebrum.neocortexengine.domain.game.Campaign;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacters;
import org.asciicerebrum.neocortexengine.domain.mechanics.workflow.IWorkflow;
import org.asciicerebrum.neocortexengine.domain.mechanics.workflow.Interaction;
import org.asciicerebrum.neocortexengine.services.events.EventTriggerService;

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

    /**
     * Triggering events.
     */
    private EventTriggerService eventTriggerService;

    @Override
    public final void initiateCombatRound(final Campaign campaign,
            final DndCharacters participants)
            throws OperationNotSupportedException {

        final EventEntry preEvent
                = new EventEntry(EventType.COMBATROUND_PREINIT);
        preEvent.setWhom(participants.getUniqueIds());
        this.getEventTriggerService().trigger(preEvent);

        final Interaction interaction = new Interaction();
        interaction.setTargetCharacters(participants);

        if (this.initializeCombatRoundWorkflow != null) {
            this.initializeCombatRoundWorkflow.runWorkflow(interaction,
                    campaign);
        }

        // this workflow can also be not setup!
        if (this.conditionExpirationWorkflow != null) {
            // first participant in row must get rid of the flat footed
            // condition.
            this.conditionExpirationWorkflow.runWorkflow(interaction, campaign);
        }

        if (campaign.getCombatRound() != null) {
            final EventEntry postEvent
                    = new EventEntry(EventType.COMBATROUND_POSTINIT);
            postEvent.setWhom(campaign.getCombatRound()
                    .getOrderedParticipantIds());
            this.getEventTriggerService().trigger(postEvent);
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
            workflow.runWorkflow(interaction, campaign);
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

    /**
     * @return the eventTriggerService
     */
    public final EventTriggerService getEventTriggerService() {
        return eventTriggerService;
    }

    /**
     * @param eventTriggerServiceInput the eventTriggerService to set
     */
    public final void setEventTriggerService(
            final EventTriggerService eventTriggerServiceInput) {
        this.eventTriggerService = eventTriggerServiceInput;
    }
}
