package org.asciicerebrum.mydndgame.conditionevaluator;

import org.asciicerebrum.mydndgame.interfaces.entities.ConditionEvaluator;
import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.mydndgame.interfaces.entities.ISituationContext;

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
    public final Boolean evaluate(final ISituationContext situationContext) {
        for (ConditionEvaluator singleEval : this.conditionEvaluators) {
            if (singleEval.evaluate(situationContext)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
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
