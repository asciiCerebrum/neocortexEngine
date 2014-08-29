package org.asciicerebrum.mydndgame.observers;

import org.asciicerebrum.mydndgame.interfaces.entities.ConditionEvaluator;
import org.asciicerebrum.mydndgame.interfaces.entities.IObserver;
import org.asciicerebrum.mydndgame.interfaces.entities.ISituationContext;
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
     * Callback method that is called by the parent to make the actual
     * triggering after the check of the valid evaluators.
     *
     * @param object the object to manipulate by the trigger.
     * @param situationContext the situation context.
     * @return the manipulated object.
     */
    protected abstract Object triggerCallback(Object object,
            ISituationContext situationContext);

    /**
     * {@inheritDoc} Assures that the condition evalator is always checked
     * first!
     */
    @Override
    public final Object trigger(final Object object,
            final ISituationContext situationContext) {

        if (this.getConditionEvaluator() != null
                && !this.getConditionEvaluator().evaluate(situationContext)) {
            return object;
        }

        return this.triggerCallback(object, situationContext);
    }

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
