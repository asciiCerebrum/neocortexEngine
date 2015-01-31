package org.asciicerebrum.mydndgame.conditionevaluator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.asciicerebrum.mydndgame.domain.core.mechanics.Bonus;
import org.asciicerebrum.mydndgame.domain.game.entities.DndCharacter;
import org.asciicerebrum.mydndgame.observers.IObserver;

/**
 *
 * @author species8472
 */
public class AndListEvaluator implements ConditionEvaluator {

    /**
     * Container list for all sub condition evaluators that must all evaluate to
     * true.
     */
    private List<ConditionEvaluator> conditionEvaluators
            = new ArrayList<ConditionEvaluator>();

    /**
     * @param conditionEvaluatorsInput the conditionEvaluators to set
     */
    public final void setConditionEvaluators(
            final List<ConditionEvaluator> conditionEvaluatorsInput) {
        this.conditionEvaluators = conditionEvaluatorsInput;
    }

    public final Iterator<ConditionEvaluator> iterator() {
        return this.conditionEvaluators.iterator();
    }

    /**
     * {@inheritDoc} Only true, if all elements in the list evaluate to true.
     */
    @Override
    public final boolean evaluate(final DndCharacter dndCharacter,
            final IObserver referenceObserver) {
        for (ConditionEvaluator singleEval : this.conditionEvaluators) {
            if (!singleEval.evaluate(dndCharacter, referenceObserver)) {
                return false;
            }
        }
        return true;
    }

    /**
     * {@inheritDoc} Only true, if all elements in the list evaluate to true.
     */
    @Override
    public final boolean evaluate(final DndCharacter dndCharacter,
            final Bonus referenceBonus) {
        for (ConditionEvaluator singleEval : this.conditionEvaluators) {
            if (!singleEval.evaluate(dndCharacter, referenceBonus)) {
                return false;
            }
        }
        return true;
    }

}
