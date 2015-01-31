package org.asciicerebrum.mydndgame.observers;

import org.asciicerebrum.mydndgame.domain.game.entities.DndCharacter;
import org.asciicerebrum.mydndgame.conditionevaluator.ConditionEvaluator;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverHook;

/**
 *
 * @author species8472
 */
public interface IObserver {

    public enum ObserverScope {

        /**
         * Targets everything.
         */
        ALL,
        /**
         * Targets only the unique entity the observer is part of.
         */
        SPECIFIC;
    }

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
     * @param dndCharacter the situation character to act upon.
     * @return the modified object.
     */
    Object trigger(Object object, DndCharacter dndCharacter);

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

    ObserverScope getScope();

    void setScope(ObserverScope scope);

}
