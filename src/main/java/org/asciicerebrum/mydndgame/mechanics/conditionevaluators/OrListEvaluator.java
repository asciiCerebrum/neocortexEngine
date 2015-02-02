package org.asciicerebrum.mydndgame.mechanics.conditionevaluators;

import org.asciicerebrum.mydndgame.domain.mechanics.interfaces.ConditionEvaluator;
import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.mydndgame.domain.mechanics.entities.Bonus;
import org.asciicerebrum.mydndgame.domain.mechanics.entities.Observer;
import org.asciicerebrum.mydndgame.domain.game.entities.DndCharacter;

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
            final Observer referenceObserver) {
        for (ConditionEvaluator singleEval : this.conditionEvaluators) {
            if (singleEval.evaluate(dndCharacter, referenceObserver)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public final boolean evaluate(final DndCharacter dndCharacter,
            final Bonus referenceBonus) {
        for (ConditionEvaluator singleEval : this.conditionEvaluators) {
            if (singleEval.evaluate(dndCharacter, referenceBonus)) {
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
