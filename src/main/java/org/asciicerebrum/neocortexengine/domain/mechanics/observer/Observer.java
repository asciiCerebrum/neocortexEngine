package org.asciicerebrum.neocortexengine.domain.mechanics.observer;

import org.asciicerebrum.neocortexengine.mechanics.conditionevaluators.ConditionEvaluator;
import org.asciicerebrum.neocortexengine.domain.mechanics.ObserverHook;

/**
 *
 * @author species8472
 */
public class Observer {

    /**
     * Defines the scope of the observer.
     */
    public enum ObserverScope {

        /**
         * Targets everything.
         */
        ALL,
        /**
         * Targets only the unique entity the observer is part of.
         */
        SPECIFIC;
    }

    /**
     * The hook enum where the observer is used.
     */
    private ObserverHook hook;

    /**
     * To evaluate the condition if the observer is really triggered or not.
     */
    private ConditionEvaluator conditionEvaluator;

    /**
     * The strategy that acutally handles the triggering.
     */
    private ObserverTriggerStrategy triggerStrategy;

    /**
     * The scope this observer is created for. It defaults to ALL.
     */
    private ObserverScope scope = ObserverScope.ALL;

    /**
     * @return the hook.
     */
    public final ObserverHook getHook() {
        return this.hook;
    }

    /**
     * @param hookInput the hook.
     */
    public final void setHook(final ObserverHook hookInput) {
        this.hook = hookInput;
    }

    /**
     * @return the condition evaluator.
     */
    public final ConditionEvaluator getConditionEvaluator() {
        return conditionEvaluator;
    }

    /**
     * @param conditionEvaluatorInput the condition evaluator.
     */
    public final void setConditionEvaluator(
            final ConditionEvaluator conditionEvaluatorInput) {
        this.conditionEvaluator = conditionEvaluatorInput;
    }

    /**
     * @return the scope
     */
    public final ObserverScope getScope() {
        return scope;
    }

    /**
     * @param scopeInput the scope to set
     */
    public final void setScope(final ObserverScope scopeInput) {
        this.scope = scopeInput;
    }

    /**
     * @return the triggerStrategy
     */
    public final ObserverTriggerStrategy getTriggerStrategy() {
        return triggerStrategy;
    }

    /**
     * @param triggerStrategyInput the triggerStrategy to set
     */
    public final void setTriggerStrategy(
            final ObserverTriggerStrategy triggerStrategyInput) {
        this.triggerStrategy = triggerStrategyInput;
    }

}
