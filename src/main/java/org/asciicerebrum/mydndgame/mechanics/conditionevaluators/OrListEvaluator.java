package org.asciicerebrum.mydndgame.mechanics.conditionevaluators;

import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.mydndgame.domain.core.UniqueEntity;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.interfaces.ConditionEvaluator;

/**
 *
 * @author species8472
 */
public class OrListEvaluator implements ConditionEvaluator {

    /**
     * Container list for all sub condition evaluators, one of them must
     * evaluate to true.
     */
    private List<ConditionEvaluator> conditionEvaluators
            = new ArrayList<ConditionEvaluator>();

    /**
     * {@inheritDoc} Only true, if one element in the list evaluates to true.
     */
    @Override
    public final boolean evaluate(final DndCharacter dndCharacter,
            final UniqueEntity contextItem) {
        for (ConditionEvaluator singleEval : this.conditionEvaluators) {
            if (singleEval.evaluate(dndCharacter, contextItem)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param conditionEvaluatorsInput the conditionEvaluators to set
     */
    public final void setConditionEvaluators(
            final List<ConditionEvaluator> conditionEvaluatorsInput) {
        this.conditionEvaluators = conditionEvaluatorsInput;
    }

}
