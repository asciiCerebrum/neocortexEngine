package org.asciicerebrum.mydndgame.services.core.accumulator.observer;

import java.util.Iterator;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSource;
import org.asciicerebrum.mydndgame.domain.core.attribution.Condition;
import org.asciicerebrum.mydndgame.domain.core.attribution.Conditions;
import org.asciicerebrum.mydndgame.domain.gameentities.UniqueEntity;
import org.asciicerebrum.mydndgame.observers.Observers;

/**
 *
 * @author species8472
 */
public class ConditionsObserverAccumulatorStrategy
        implements ObserverAccumulatorStrategy {

    /**
     * Accumulator strategy for the single condition.
     */
    private ObserverAccumulatorStrategy conditionStrategy;

    @Override
    public final Observers getObservers(final ObserverSource observerSource,
            final UniqueEntity targetEntity) {
        final Observers observers = new Observers();
        final Conditions conditions = (Conditions) observerSource;
        final Iterator<Condition> conditionIterator = conditions.iterator();

        while (conditionIterator.hasNext()) {
            final Condition condition = conditionIterator.next();
            observers.add(this.conditionStrategy.getObservers(
                    condition, targetEntity));
        }
        return observers;
    }

    @Override
    public final boolean isApplicable(final ObserverSource observerSource) {
        return observerSource instanceof Conditions;
    }

    /**
     * @param conditionStrategyInput the conditionStrategy to set
     */
    public final void setConditionStrategy(
            final ObserverAccumulatorStrategy conditionStrategyInput) {
        this.conditionStrategy = conditionStrategyInput;
    }

}
