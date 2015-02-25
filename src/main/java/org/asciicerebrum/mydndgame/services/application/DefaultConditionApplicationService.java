package org.asciicerebrum.mydndgame.services.application;

import java.util.Iterator;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.mechanics.ObserverHook;
import org.asciicerebrum.mydndgame.domain.mechanics.ObserverHooks;
import org.asciicerebrum.mydndgame.domain.mechanics.WorldDate;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSources;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.Condition;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.Conditions;
import org.asciicerebrum.mydndgame.services.core.ObservableService;

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
    }

    @Override
    public final void removeExpiredConditions(
            final DndCharacter dndCharacter,
            final WorldDate currentDate) {
        final Iterator<Condition> conditionIterator
                = dndCharacter.getConditions().iterator();
        while (conditionIterator.hasNext()) {
            final Condition condition = conditionIterator.next();

            if (currentDate.isAfter(condition.getExpiryDate())) {
                conditionIterator.remove();
            }
        }
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
}
