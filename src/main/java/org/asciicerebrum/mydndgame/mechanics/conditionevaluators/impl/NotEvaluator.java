package org.asciicerebrum.mydndgame.mechanics.conditionevaluators.impl;

import org.asciicerebrum.mydndgame.domain.core.ICharacter;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.mechanics.conditionevaluators.ConditionEvaluator;

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
    public final boolean evaluate(final ICharacter dndCharacter,
            final UniqueEntity contextItem) {
        return !this.getConditionEvaluator().evaluate(dndCharacter,
                contextItem);
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
