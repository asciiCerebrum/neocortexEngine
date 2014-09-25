package org.asciicerebrum.mydndgame.interfaces.entities;

import java.util.List;

/**
 *
 * @author Tabea Raab
 */
public interface IBonus {

    /**
     * Facets that define aspects which are important for the resemblance
     * analysis of two boni. Two boni are considered resembling, when they match
     * in all the facets that were given to the method in a list.
     */
    public enum ResemblanceFacet {

        /**
         * Resemblance in value.
         */
        VALUE,
        /**
         * Resemblance in rank.
         */
        RANK,
        /**
         * Resemblance in bonus type.
         */
        BONUS_TYPE,
        /**
         * Resemblance in bonus target.
         */
        TARGET,
        /**
         * Resemblance in dynamic value provider.
         */
        DYNAMIC_VALUE_PROVIDER;
    }

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
     * @param character the contextual character for the dynamic version.
     * @return either the constant or dynamic value.
     */
    Long getEffectiveValue(ICharacter character);

    /**
     * Works similar to equals. Two boni resemble each other if all possible
     * facets are equal. Equality by reference must be contained so we do not
     * overwrite the equals method!
     *
     * @param bonus the bonus to test the resemblance with.
     * @return true if all attributes match. False otherwise.
     */
    Boolean resembles(IBonus bonus);

    /**
     * Works similar to equals. Two boni resemble each other if the given list
     * of facets are equal. Equality by reference must be contained so we do not
     * overwrite the equals method!
     *
     * @param bonus the bonus to test the resemblance with.
     * @param facets the facets that define resemblance. Null or empty lists
     * lead to the full facet spectrum.
     * @return true if all given facets match. False otherwise.
     */
    Boolean resembles(IBonus bonus, List<ResemblanceFacet> facets);

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
