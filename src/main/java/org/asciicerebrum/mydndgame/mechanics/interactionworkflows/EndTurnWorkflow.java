package org.asciicerebrum.mydndgame.mechanics.interactionworkflows;

import org.asciicerebrum.mydndgame.domain.events.EventEntry;
import org.asciicerebrum.mydndgame.domain.events.EventType;
import org.asciicerebrum.mydndgame.domain.game.Campaign;
import org.asciicerebrum.mydndgame.domain.mechanics.workflow.IWorkflow;
import org.asciicerebrum.mydndgame.domain.mechanics.workflow.Interaction;
import org.asciicerebrum.mydndgame.services.events.EventTriggerService;

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
     * Triggering events.
     */
    private EventTriggerService eventTriggerService;

    /**
     * {@inheritDoc}
     */
    @Override
    public final void runWorkflow(
            final Interaction interaction, final Campaign campaign) {

        if (this.getEventTriggerService() != null) {
            final EventEntry resultEvent
                    = new EventEntry(EventType.END_TURN_END);
            resultEvent.setWho(campaign.getCombatRound()
                    .getCurrentParticipantId());
            resultEvent.setWhen(campaign.getCombatRound().getCurrentDate());
            this.getEventTriggerService().trigger(resultEvent);
        }

        campaign.getCombatRound().moveToNextPosition();

        if (this.getEventTriggerService() != null) {
            final EventEntry resultEvent
                    = new EventEntry(EventType.END_TURN_START);
            resultEvent.setWho(campaign.getCombatRound()
                    .getCurrentParticipantId());
            resultEvent.setWhen(campaign.getCombatRound().getCurrentDate());
            this.getEventTriggerService().trigger(resultEvent);
        }

        if (this.conditionExpirationWorkflow != null) {
            // run workflow conditionExpirationWorkflow
            this.conditionExpirationWorkflow.runWorkflow(
                    interaction, campaign);
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
