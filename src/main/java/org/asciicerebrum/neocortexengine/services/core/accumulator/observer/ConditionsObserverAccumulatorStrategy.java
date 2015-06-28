package org.asciicerebrum.neocortexengine.services.core.accumulator.observer;

import java.util.Iterator;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.neocortexengine.domain.ruleentities.composition.Condition;
import org.asciicerebrum.neocortexengine.domain.ruleentities.composition.Conditions;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.Observers;

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
        if (!(observerSource instanceof Conditions)) {
            return observers;
        }
        final Conditions conditions = (Conditions) observerSource;
        final Iterator<Condition> conditionIterator = conditions.iterator();

        while (conditionIterator.hasNext()) {
            final Condition condition = conditionIterator.next();
            observers.add(this.getConditionStrategy().getObservers(
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

    /**
     * @return the conditionStrategy
     */
    public final ObserverAccumulatorStrategy getConditionStrategy() {
        return conditionStrategy;
    }

}
