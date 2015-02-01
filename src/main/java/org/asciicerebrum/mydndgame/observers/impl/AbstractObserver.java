package org.asciicerebrum.mydndgame.observers.impl;

import org.asciicerebrum.mydndgame.conditionevaluator.ConditionEvaluator;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverHook;
import org.asciicerebrum.mydndgame.domain.game.entities.DndCharacter;
import org.asciicerebrum.mydndgame.observers.IObserver;
import org.asciicerebrum.mydndgame.observers.IObserver.ObserverScope;

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
     * The scope this observer is created for. It defaults to ALL.
     */
    private ObserverScope scope = ObserverScope.ALL;

    /**
     * Callback method that is called by the parent to make the actual
     * triggering after the check of the valid evaluators.
     *
     * @param object the object to manipulate by the trigger.
     * @param dndCharacter the context.
     * @return the manipulated object.
     */
    protected abstract Object triggerCallback(Object object,
            DndCharacter dndCharacter);

    /**
     * {@inheritDoc} Assures that the condition evalator is always checked
     * first!
     */
    @Override
    public final Object trigger(final Object object,
            final DndCharacter dndCharacter) {

        if (this.getConditionEvaluator() != null
                && !this.getConditionEvaluator()
                .evaluate(dndCharacter, this)) {
            return object;
        }

        return this.triggerCallback(object, dndCharacter);
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

    public final ConditionEvaluator getConditionEvaluator() {
        return conditionEvaluator;
    }

    public final void setConditionEvaluator(
            final ConditionEvaluator conditionEvaluatorInput) {
        this.conditionEvaluator = conditionEvaluatorInput;
    }

    /**
     * @return the scope
     */
    @Override
    public final ObserverScope getScope() {
        return scope;
    }

    /**
     * @param scopeInput the scope to set
     */
    @Override
    public final void setScope(final ObserverScope scopeInput) {
        this.scope = scopeInput;
    }

}
