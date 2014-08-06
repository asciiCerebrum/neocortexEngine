package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.interfaces.entities.BonusTarget;
import org.asciicerebrum.mydndgame.interfaces.valueproviders.BonusValueProvider;
import java.io.Serializable;
import java.util.Comparator;
import org.asciicerebrum.mydndgame.interfaces.entities.IBonus;
import org.asciicerebrum.mydndgame.interfaces.entities.IBonusType;
import org.asciicerebrum.mydndgame.interfaces.valueproviders.BonusValueContext;

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
     * {@inheritDoc}
     */
    @Override
    public final Boolean resembles(final IBonus bonus) {
        if (!bonus.getBonusType().equals(this.getBonusType())) {
            return Boolean.FALSE;
        }
        if (!bonus.getTarget().equals(this.getTarget())) {
            return Boolean.FALSE;
        }
        if (!bonus.getDynamicValueProvider().equals(
                this.getDynamicValueProvider())) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
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
     * @param dynamicValueProviderInput the dynamicValueProvider to set
     */
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
     * {@inheritDoc}
     */
    @Override
    public final Long getEffectiveValue(final BonusValueContext context) {

        if (this.value != null) {
            return this.value;
        }
        if (this.getDynamicValueProvider() != null) {
            return this.getDynamicValueProvider().getDynamicValue(context);
        }
        return null;
    }

}
