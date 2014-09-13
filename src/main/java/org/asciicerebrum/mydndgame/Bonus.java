package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.interfaces.entities.BonusTarget;
import org.asciicerebrum.mydndgame.interfaces.entities.BonusValueProvider;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.lang.ObjectUtils;
import org.asciicerebrum.mydndgame.interfaces.entities.ConditionEvaluator;
import org.asciicerebrum.mydndgame.interfaces.entities.IBonus;
import org.asciicerebrum.mydndgame.interfaces.entities.IBonusType;
import org.asciicerebrum.mydndgame.interfaces.entities.ISituationContext;

/**
 *
 * @author species8472
 */
public class Bonus implements IBonus {

    /**
     * The value of the bonus.
     */
    private Long value;
    /**
     * The rank for the bonus. rank for multiple boni in a row, e.g. base attack
     * boni +2/+1 and the like; rank start from 0
     */
    private Long rank;
    /**
     * The type of the bonus. E.g. size bonus.
     */
    private IBonusType bonusType;
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
    private BonusValueProvider dynamicValueProvider;

    /**
     * Checks if the bonus can be applied when the condition evaluates to true.
     */
    private ConditionEvaluator conditionEvaluator;

    /**
     * {@inheritDoc}
     */
    @Override
    public final Boolean resembles(final IBonus bonus) {
        return this.resemblesValue(bonus,
                Arrays.asList(ResemblanceFacet.values()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Boolean resembles(final IBonus bonus,
            final List<ResemblanceFacet> facets) {

        if (facets == null || facets.isEmpty()) {
            return this.resembles(bonus);
        }

        return this.resemblesValue(bonus, facets);
    }

    /**
     * 1st test in chain of resemblance.
     *
     * @param bonus the bonus.
     * @param facets the facets to take into account.
     * @return false or further delegate result.
     */
    private Boolean resemblesValue(final IBonus bonus,
            final List<ResemblanceFacet> facets) {
        if (facets.contains(ResemblanceFacet.VALUE)
                && !ObjectUtils.equals(this.getValue(), bonus.getValue())) {
            return Boolean.FALSE;
        }
        return this.resemblesRank(bonus, facets);
    }

    /**
     * 2nd test in chain of resemblance.
     *
     * @param bonus the bonus.
     * @param facets the facets to take into account.
     * @return false or further delegate result.
     */
    private Boolean resemblesRank(final IBonus bonus,
            final List<ResemblanceFacet> facets) {
        if (facets.contains(ResemblanceFacet.RANK)
                && !ObjectUtils.equals(this.getRank(), bonus.getRank())) {
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
    private Boolean resemblesBonusType(final IBonus bonus,
            final List<ResemblanceFacet> facets) {
        if (facets.contains(ResemblanceFacet.BONUS_TYPE)
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
    private Boolean resemblesTarget(final IBonus bonus,
            final List<ResemblanceFacet> facets) {
        if (facets.contains(ResemblanceFacet.TARGET)
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
    private Boolean resemblesDynamicValueProvider(final IBonus bonus,
            final List<ResemblanceFacet> facets) {
        if (facets.contains(ResemblanceFacet.DYNAMIC_VALUE_PROVIDER)
                && !ObjectUtils.equals(this.getDynamicValueProvider(),
                        bonus.getDynamicValueProvider())) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final ConditionEvaluator getConditionEvaluator() {
        return conditionEvaluator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setConditionEvaluator(
            final ConditionEvaluator conditionEvaluatorInput) {
        this.conditionEvaluator = conditionEvaluatorInput;
    }

    /**
     * For sorting boni by their ranks.
     */
    public static class RankComparator implements Comparator<IBonus>,
            Serializable {

        /**
         * The serial version uid.
         */
        private static final long serialVersionUID = 1L;

        /**
         * {@inheritDoc}
         */
        @Override
        public final int compare(final IBonus o1, final IBonus o2) {
            return o1.getRank().compareTo(o2.getRank());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Long getValue() {
        return value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setValue(final Long valueInput) {
        this.value = valueInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final IBonusType getBonusType() {
        return bonusType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setBonusType(final IBonusType bonusTypeInput) {
        this.bonusType = bonusTypeInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Long getRank() {
        return rank;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setRank(final Long rankInput) {
        this.rank = rankInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final IBonus makeCopy() {
        IBonus clonedBonus = new Bonus();
        clonedBonus.setBonusType(this.getBonusType());
        clonedBonus.setRank(this.getRank());
        clonedBonus.setTarget(this.getTarget());
        clonedBonus.setValue(this.getValue());
        clonedBonus.setDynamicValueProvider(this.getDynamicValueProvider());

        return clonedBonus;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final BonusValueProvider getDynamicValueProvider() {
        return dynamicValueProvider;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setDynamicValueProvider(
            final BonusValueProvider dynamicValueProviderInput) {
        this.dynamicValueProvider = dynamicValueProviderInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final BonusTarget getTarget() {
        return target;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setTarget(final BonusTarget bonusTargetInput) {
        this.target = bonusTargetInput;
    }

    /**
     * {@inheritDoc} Remember there is a difference between an effective value
     * of null and 0. Null: the bonus is defacto non-existent. 0: the bonus
     * applies with a value of 0.
     */
    @Override
    public final Long getEffectiveValue(final ISituationContext context) {

        if (this.getConditionEvaluator() != null
                && !this.getConditionEvaluator().evaluate(context)) {
            return null;
        }
        if (this.value != null) {
            return this.value;
        }
        if (this.getDynamicValueProvider() != null) {
            return this.getDynamicValueProvider().getDynamicValue(context);
        }
        return null;
    }

}
