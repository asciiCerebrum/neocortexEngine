package org.asciicerebrum.neocortexengine.domain.mechanics.bonus;

import org.asciicerebrum.neocortexengine.mechanics.conditionevaluators.ConditionEvaluator;
import java.util.Arrays;
import org.apache.commons.lang3.ObjectUtils;
import org.asciicerebrum.neocortexengine.domain.core.particles.BonusValueTuple;
import org.asciicerebrum.neocortexengine.domain.mechanics.BonusTarget;
import org.asciicerebrum.neocortexengine.domain.mechanics.BonusType;

/**
 *
 * @author species8472
 */
public class Bonus {

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
     * Scopes for the validity of a bonus.
     */
    public enum BonusScope {

        /**
         * Targets everything.
         */
        ALL,
        /**
         * Targets only the unique entity the bonus is part of.
         */
        SPECIFIC;
    }

    /**
     * The value of the bonus.
     */
    private BonusValueTuple values;
    /**
     * The type of the bonus. E.g. size bonus.
     */
    private BonusType bonusType;
    /**
     * The target where the bonus is applied. E.g. attack rolls.
     */
    private BonusTarget target;
    /**
     * The provider class for a dynamically calculated bonus value. For dynamic
     * bonus values, e.g. the dex-bonus on ac is dynamic because it depends on
     * the dex-value and so on the dex modifier, which is calculated. dynamic
     * value has priority over static one.
     */
    private DynamicValueProvider dynamicValueProvider;
    /**
     * Checks if the bonus can be applied when the condition evaluates to true.
     */
    private ConditionEvaluator conditionEvaluator;
    /**
     * The scope this bonus is created for. It defaults to ALL.
     */
    private BonusScope scope = BonusScope.ALL;

    /**
     * Works similar to equals. Two boni resemble each other if all possible
     * facets are equal. Equality by reference must be contained so we do not
     * overwrite the equals method!
     *
     * @param bonus the bonus to test the resemblance with.
     * @return true if all attributes match. False otherwise.
     */
    public final boolean resembles(final Bonus bonus) {
        return this.resemblesValues(bonus, ResemblanceFacet.values());
    }

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
    public final boolean resembles(final Bonus bonus,
            final ResemblanceFacet... facets) {

        if (facets == null || facets.length == 0) {
            return this.resembles(bonus);
        }

        return this.resemblesValues(bonus, facets);
    }

    /**
     * 1st test in chain of resemblance.
     *
     * @param bonus the bonus.
     * @param facets the facets to take into account.
     * @return false or further delegate result.
     */
    private boolean resemblesValues(final Bonus bonus,
            final ResemblanceFacet... facets) {
        if (Arrays.asList(facets).contains(ResemblanceFacet.VALUE)
                && !this.getValues().equals(bonus.getValues())) {
            return Boolean.FALSE;
        }
        return this.resemblesBonusType(bonus, facets);
    }

    /**
     * 3rd test in chain of resemblance.
     *
     * @param bonus the bonus.
     * @param facets the facets to take into account.
     * @return false or further delegate result.
     */
    private boolean resemblesBonusType(final Bonus bonus,
            final ResemblanceFacet... facets) {
        if (Arrays.asList(facets).contains(ResemblanceFacet.BONUS_TYPE)
                && !ObjectUtils.equals(bonus.getBonusType(),
                        this.getBonusType())) {
            return Boolean.FALSE;
        }
        return this.resemblesTarget(bonus, facets);
    }

    /**
     * 4th test in chain of resemblance.
     *
     * @param bonus the bonus.
     * @param facets the facets to take into account.
     * @return false or further delegate result.
     */
    private boolean resemblesTarget(final Bonus bonus,
            final ResemblanceFacet... facets) {
        if (Arrays.asList(facets).contains(ResemblanceFacet.TARGET)
                && !ObjectUtils.equals(bonus.getTarget(), this.getTarget())) {
            return Boolean.FALSE;
        }
        return this.resemblesDynamicValueProvider(bonus, facets);
    }

    /**
     * 5th test in chain of resemblance.
     *
     * @param bonus the bonus.
     * @param facets the facets to take into account.
     * @return false or further delegate result, which is true in this case.
     */
    private boolean resemblesDynamicValueProvider(final Bonus bonus,
            final ResemblanceFacet... facets) {
        if (Arrays.asList(facets).contains(
                ResemblanceFacet.DYNAMIC_VALUE_PROVIDER)
                && !ObjectUtils.equals(this.getDynamicValueProvider(),
                        bonus.getDynamicValueProvider())) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    /**
     * @return the condition evaluator.
     */
    public final ConditionEvaluator getConditionEvaluator() {
        return conditionEvaluator;
    }

    /**
     * @param conditionEvaluatorInput the condition evaluator.
     */
    public final void setConditionEvaluator(
            final ConditionEvaluator conditionEvaluatorInput) {
        this.conditionEvaluator = conditionEvaluatorInput;
    }

    /**
     * @return the bonusType
     */
    public final BonusType getBonusType() {
        return bonusType;
    }

    /**
     * @param bonusTypeInput the bonusType to set
     */
    public final void setBonusType(final BonusType bonusTypeInput) {
        this.bonusType = bonusTypeInput;
    }

    /**
     * @return the target
     */
    public final BonusTarget getTarget() {
        return target;
    }

    /**
     * @param targetInput the target to set
     */
    public final void setTarget(final BonusTarget targetInput) {
        this.target = targetInput;
    }

    /**
     * @return the dynamicValueProvider
     */
    public final DynamicValueProvider getDynamicValueProvider() {
        return dynamicValueProvider;
    }

    /**
     * @param dynamicValueProviderInput the dynamicValueProvider to set
     */
    public final void setDynamicValueProvider(
            final DynamicValueProvider dynamicValueProviderInput) {
        this.dynamicValueProvider = dynamicValueProviderInput;
    }

    /**
     * Makes a clone of the bonus which resembles it. That means the clones
     * passes the resembles test.
     *
     * @return the cloned version of this bonus.
     */
    public final Bonus makeCopy() {
        Bonus clonedBonus = new Bonus();
        clonedBonus.setBonusType(this.getBonusType());
        clonedBonus.setTarget(this.getTarget());
        clonedBonus.setValues(this.getValues());
        clonedBonus.setDynamicValueProvider(this.getDynamicValueProvider());

        return clonedBonus;
    }

    /**
     * @return the values
     */
    public final BonusValueTuple getValues() {
        return values;
    }

    /**
     * @param valuesInput the values to set
     */
    public final void setValues(final BonusValueTuple valuesInput) {
        this.values = valuesInput;
    }

    /**
     * @return the scope
     */
    public final BonusScope getScope() {
        return scope;
    }

    /**
     * @param scopeInput the scope to set
     */
    public final void setScope(final BonusScope scopeInput) {
        this.scope = scopeInput;
    }

}
