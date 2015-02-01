package org.asciicerebrum.mydndgame.conditionevaluator.impl;

import org.asciicerebrum.mydndgame.conditionevaluator.ConditionEvaluator;
import org.asciicerebrum.mydndgame.domain.core.mechanics.Bonus;
import org.asciicerebrum.mydndgame.domain.game.entities.DndCharacter;
import org.asciicerebrum.mydndgame.observers.IObserver;

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
    public final boolean evaluate(final DndCharacter dndCharacter,
            final IObserver referenceObserver) {
        return !this.getConditionEvaluator().evaluate(dndCharacter,
                referenceObserver);
    }

    @Override
    public final boolean evaluate(final DndCharacter dndCharacter,
            final Bonus referenceBonus) {
        return !this.getConditionEvaluator().evaluate(dndCharacter,
                referenceBonus);
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
