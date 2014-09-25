package org.asciicerebrum.mydndgame.interfaces.entities;

/**
 *
 * @author species8472
 */
public interface IObserver {

    /**
     *
     * @return the hook enum this observer is associated with.
     */
    ObserverHook getHook();

    /**
     * Sets the hook enum for the observer.
     *
     * @param hookInput the hook to set.
     */
    void setHook(ObserverHook hookInput);

    /**
     * Makes the observer fulfill its destiny.
     *
     * @param object the object to act and modify on.
     * @param character the situation character to act upon.
     * @return the modified object.
     */
    Object trigger(Object object, ICharacter character);

    /**
     *
     * @return the evaluator for checking if the conditions are given that a
     * trigger can be activated.
     */
    ConditionEvaluator getConditionEvaluator();

    /**
     * Sets the condition evaluation bean.
     *
     * @param conditionEvaluator the conditionEvaluator.
     */
    void setConditionEvaluator(ConditionEvaluator conditionEvaluator);

}
