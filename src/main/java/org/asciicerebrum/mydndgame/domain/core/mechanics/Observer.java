package org.asciicerebrum.mydndgame.domain.core.mechanics;

import org.asciicerebrum.mydndgame.conditionevaluator.ConditionEvaluator;
import org.asciicerebrum.mydndgame.observertrigger.ObserverTriggerStrategy;

/**
 *
 * @author species8472
 */
public class Observer {

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

    public final ObserverHook getHook() {
        return this.hook;
    }

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
