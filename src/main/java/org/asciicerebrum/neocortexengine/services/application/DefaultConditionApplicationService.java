package org.asciicerebrum.neocortexengine.services.application;

import java.util.Iterator;
import org.asciicerebrum.neocortexengine.domain.events.EventEntry;
import org.asciicerebrum.neocortexengine.domain.events.EventType;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.mechanics.ObserverHook;
import org.asciicerebrum.neocortexengine.domain.mechanics.ObserverHooks;
import org.asciicerebrum.neocortexengine.domain.mechanics.WorldDate;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.source.ObserverSources;
import org.asciicerebrum.neocortexengine.domain.ruleentities.composition.Condition;
import org.asciicerebrum.neocortexengine.domain.ruleentities.composition.Conditions;
import org.asciicerebrum.neocortexengine.services.core.ObservableService;
import org.asciicerebrum.neocortexengine.services.events.EventTriggerService;

/**
 *
 * @author species8472
 */
public class DefaultConditionApplicationService
        implements ConditionApplicationService {

    /**
     * The observable service.
     */
    private ObservableService observableService;

    /**
     * The service for triggering events.
     */
    private EventTriggerService eventTriggerService;

    @Override
    public final void applyCondition(final DndCharacter dndCharacter,
            final Conditions conditions) {

        final Iterator<Condition> conditionIterator = conditions.iterator();
        while (conditionIterator.hasNext()) {
            final Condition condition = conditionIterator.next();

            final Condition convertedCondition
                    = (Condition) this.getObservableService().triggerObservers(
                            condition, dndCharacter,
                            new ObserverSources(dndCharacter),
                            new ObserverHooks(
                                    ObserverHook.CONDITION_APPLICATION),
                            dndCharacter);
            if (convertedCondition != null) {
                this.applySingleCondition(dndCharacter, convertedCondition);
            }
        }
    }

    /**
     * Applies a single condition on the character.
     *
     * @param dndCharacter the character to apply the condition on.
     * @param condition the condition to apply.
     */
    final void applySingleCondition(final DndCharacter dndCharacter,
            final Condition condition) {
        dndCharacter.addCondition(condition);
        this.triggerEvent(condition, dndCharacter, EventType.CONDITION_GAIN);
    }

    @Override
    public final void removeExpiredConditions(
            final DndCharacter dndCharacter,
            final WorldDate currentDate) {
        final Iterator<Condition> conditionIterator
                = dndCharacter.getConditions().iterator();
        while (conditionIterator.hasNext()) {
            final Condition condition = conditionIterator.next();

            if (currentDate.isAfterOrEqual(condition.getExpiryDate())) {
                conditionIterator.remove();
                this.triggerEvent(condition, dndCharacter,
                        EventType.CONDITION_LOSE);
            }
        }
    }

    /**
     * Helper method for triggering condition related events.
     *
     * @param condition the condition in question.
     * @param dndCharacter the character related to the condition.
     * @param eventType the event type to trigger.
     */
    private void triggerEvent(final Condition condition,
            final DndCharacter dndCharacter, final EventType eventType) {
        final EventEntry eventEntry
                = new EventEntry(eventType);
        eventEntry.setWho(dndCharacter.getUniqueId());
        eventEntry.setWhat(condition.getConditionType().getUniqueId());
        eventEntry.setWhen(condition.getStartingDate());

        this.getEventTriggerService().trigger(eventEntry);
    }

    /**
     * @param observableServiceInput the observableService to set
     */
    public final void setObservableService(
            final ObservableService observableServiceInput) {
        this.observableService = observableServiceInput;
    }

    /**
     * @return the observableService
     */
    public final ObservableService getObservableService() {
        return observableService;
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
