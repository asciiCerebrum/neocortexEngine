package org.asciicerebrum.mydndgame.observers;

import org.asciicerebrum.mydndgame.interfaces.entities.ConditionEvaluator;
import org.asciicerebrum.mydndgame.interfaces.entities.IObserver;
import org.asciicerebrum.mydndgame.interfaces.entities.ObserverHook;

/**
 *
 * @author species8472
 */
public abstract class AbstractObserver implements IObserver {

    /**
     * The hook enum where the observer is used.
     */
    private ObserverHook hook;

    /**
     * To evaluate the condition if the observer is really triggered or not.
     */
    private ConditionEvaluator conditionEvaluator;

    /**
     * {@inheritDoc}
     */
    @Override
    public final ObserverHook getHook() {
        return this.hook;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setHook(final ObserverHook hookInput) {
        this.hook = hookInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final ConditionEvaluator getConditionEvaluator() {
        return conditionEvaluator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setConditionEvaluator(
            final ConditionEvaluator conditionEvaluatorInput) {
        this.conditionEvaluator = conditionEvaluatorInput;
    }

}
