package org.asciicerebrum.mydndgame.services.application;

import java.util.Iterator;
import org.asciicerebrum.mydndgame.domain.mechanics.WorldDate;
import org.asciicerebrum.mydndgame.domain.mechanics.ObserverHooks;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSources;
import org.asciicerebrum.mydndgame.domain.rules.composition.Condition;
import org.asciicerebrum.mydndgame.domain.rules.composition.Conditions;
import org.asciicerebrum.mydndgame.domain.game.dndcharacter.DndCharacter;
import org.asciicerebrum.mydndgame.domain.mechanics.ObserverHook;
import org.asciicerebrum.mydndgame.services.core.ObservableService;

/**
 *
 * @author species8472
 */
public class ConditionApplicationService {

    private ObservableService observableService;

    public final void applyCondition(final DndCharacter dndCharacter,
            final Conditions conditions) {

        final Iterator<Condition> conditionIterator = conditions.iterator();
        while (conditionIterator.hasNext()) {
            final Condition condition = conditionIterator.next();

            final Condition convertedCondition
                    = (Condition) this.observableService.triggerObservers(
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

    final void applySingleCondition(final DndCharacter dndCharacter,
            final Condition condition) {
        dndCharacter.getConditions().add(condition);
    }

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
}
