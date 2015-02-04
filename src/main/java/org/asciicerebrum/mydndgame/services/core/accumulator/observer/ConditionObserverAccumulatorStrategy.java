package org.asciicerebrum.mydndgame.services.core.accumulator.observer;

import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.mydndgame.domain.ruleentities.composition.Condition;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.Observers;

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

        observers.add(this.conditionTypeStrategy.getObservers(
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

}
