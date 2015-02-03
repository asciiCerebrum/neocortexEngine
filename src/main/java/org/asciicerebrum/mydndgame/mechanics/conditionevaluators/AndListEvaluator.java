package org.asciicerebrum.mydndgame.mechanics.conditionevaluators;

import org.asciicerebrum.mydndgame.domain.mechanics.transfer.ConditionEvaluator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;

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
            final UniqueEntity contextEntity) {
        for (ConditionEvaluator singleEval : this.conditionEvaluators) {
            if (!singleEval.evaluate(dndCharacter, contextEntity)) {
                return false;
            }
        }
        return true;
    }

}
