package org.asciicerebrum.mydndgame.interfaces.entities;

import org.asciicerebrum.mydndgame.interfaces.valueproviders.BonusValueProvider;

/**
 *
 * @author Tabea Raab
 */
public interface IBonus {

    /**
     * @return the dynamicValueProvider.
     */
    BonusValueProvider getDynamicValueProvider();

    /**
     * @param dynamicValueProvider the dynamicValueProvider to set
     */
    void setDynamicValueProvider(BonusValueProvider dynamicValueProvider);

    /**
     * @param valueInput the value to set.
     */
    void setValue(Long valueInput);

    /**
     * @return the value.
     */
    Long getValue();

    /**
     * @param bonusTargetInput the target to set.
     */
    void setTarget(BonusTarget bonusTargetInput);

    /**
     * @return the target.
     */
    BonusTarget getTarget();

    /**
     * @param rankInput the rank to set.
     */
    void setRank(Long rankInput);

    /**
     * @return the rank.
     */
    Long getRank();

    /**
     * @param bonusTypeInput the bonusType to set.
     */
    void setBonusType(IBonusType bonusTypeInput);

    /**
     * @return the bonusType.
     */
    IBonusType getBonusType();

    /**
     * Makes a clone of the bonus which resembles it. That means the clones
     * passes the resembles test.
     *
     * @return the cloned version of this bonus.
     */
    IBonus makeCopy();

    /**
     * Use either the constant value of the bonus or (if null) the dynamic
     * value.
     *
     * @param context the context for the dynamic version.
     * @return either the constant or dynamic value.
     */
    Long getEffectiveValue(ISituationContext context);

    /**
     * Works similar to equals. Two boni resemble each other if a specific list
     * of attributes are equal. Equality by reference must be contained so we do
     * not overwrite the equals method!
     *
     * @param bonus the bonus to test the resemblance with.
     * @return true if all attributes match. False otherwise.
     */
    Boolean resembles(IBonus bonus);

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
