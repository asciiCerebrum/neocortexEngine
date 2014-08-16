package org.asciicerebrum.mydndgame.interfaces.entities;

/**
 *
 * @author species8472
 */
public interface ConditionEvaluator {

    /**
     * Checks if the condition is met.
     *
     * @param situationContext the situation context.
     * @return the status of the condition.
     */
    Boolean evaluate(ISituationContext situationContext);

}
