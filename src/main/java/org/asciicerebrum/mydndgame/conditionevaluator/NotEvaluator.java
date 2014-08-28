package org.asciicerebrum.mydndgame.conditionevaluator;

import org.asciicerebrum.mydndgame.interfaces.entities.ConditionEvaluator;
import org.asciicerebrum.mydndgame.interfaces.entities.ISituationContext;

/**
 *
 * @author species8472
 */
public class NotEvaluator implements ConditionEvaluator {

    /**
     * Sub condition evaluators that is negated.
     */
    private ConditionEvaluator conditionEvaluator;

    /**
     * {@inheritDoc} Checks if the given sub evaluator is false.
     */
    @Override
    public final Boolean evaluate(final ISituationContext situationContext) {
        return !this.getConditionEvaluator().evaluate(situationContext);
    }

    /**
     * @return the conditionEvaluator
     */
    public final ConditionEvaluator getConditionEvaluator() {
        return conditionEvaluator;
    }

    /**
     * @param conditionEvaluatorInput the conditionEvaluator to set
     */
    public final void setConditionEvaluator(
            final ConditionEvaluator conditionEvaluatorInput) {
        this.conditionEvaluator = conditionEvaluatorInput;
    }

}
