package org.asciicerebrum.mydndgame.conditionevaluator;

import org.asciicerebrum.mydndgame.interfaces.entities.ConditionEvaluator;
import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;

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
     * {@inheritDoc} Only true, if all elements in the list evaluate to true.
     */
    @Override
    public final Boolean evaluate(final ICharacter character) {
        for (ConditionEvaluator singleEval : this.conditionEvaluators) {
            if (!singleEval.evaluate(character)) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    /**
     * @return the conditionEvaluators
     */
    public final List<ConditionEvaluator> getConditionEvaluators() {
        return conditionEvaluators;
    }

    /**
     * @param conditionEvaluatorsInput the conditionEvaluators to set
     */
    public final void setConditionEvaluators(
            final List<ConditionEvaluator> conditionEvaluatorsInput) {
        this.conditionEvaluators = conditionEvaluatorsInput;
    }

}
