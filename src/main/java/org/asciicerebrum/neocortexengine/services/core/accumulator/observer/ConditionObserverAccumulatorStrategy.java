package org.asciicerebrum.neocortexengine.services.core.accumulator.observer;

import org.asciicerebrum.neocortexengine.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.neocortexengine.domain.ruleentities.composition.Condition;
import org.asciicerebrum.neocortexengine.domain.core.UniqueEntity;
import org.asciicerebrum.neocortexengine.domain.mechanics.observer.Observers;

/**
 *
 * @author species8472
 */
public class ConditionObserverAccumulatorStrategy
        implements ObserverAccumulatorStrategy {

    /**
     * Accumulator strategy for the corresponding condition type.
     */
    private ObserverAccumulatorStrategy conditionTypeStrategy;

    @Override
    public final Observers getObservers(final ObserverSource observerSource,
            final UniqueEntity targetEntity) {
        final Observers observers = new Observers();
        if (!(observerSource instanceof Condition)) {
            return observers;
        }
        final Condition condition = (Condition) observerSource;

        observers.add(this.getConditionTypeStrategy().getObservers(
                condition.getConditionType(), targetEntity));

        return observers;
    }

    @Override
    public final boolean isApplicable(final ObserverSource observerSource) {
        return observerSource instanceof Condition;
    }

    /**
     * @param conditionTypeStrategyInput the conditionTypeStrategy to set
     */
    public final void setConditionTypeStrategy(
            final ObserverAccumulatorStrategy conditionTypeStrategyInput) {
        this.conditionTypeStrategy = conditionTypeStrategyInput;
    }

    /**
     * @return the conditionTypeStrategy
     */
    public final ObserverAccumulatorStrategy getConditionTypeStrategy() {
        return conditionTypeStrategy;
    }

}
