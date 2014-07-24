package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.interfaces.entities.BonusTarget;
import org.asciicerebrum.mydndgame.interfaces.valueproviders.BonusValueProvider;
import java.io.Serializable;
import java.util.Comparator;
import org.asciicerebrum.mydndgame.interfaces.entities.IBonus;
import org.asciicerebrum.mydndgame.interfaces.entities.IBonusType;

/**
 *
 * @author species8472
 */
public class Bonus implements IBonus {

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
     * @return the value
     */
    public final Long getValue() {
        return value;
    }

    /**
     * @param valueInput the value to set
     */
    public final void setValue(final Long valueInput) {
        this.value = valueInput;
    }

    /**
     * @return the bonusType
     */
    public final IBonusType getBonusType() {
        return bonusType;
    }

    /**
     * @param bonusTypeInput the bonusType to set
     */
    public final void setBonusType(final IBonusType bonusTypeInput) {
        this.bonusType = bonusTypeInput;
    }

    /**
     * @return the rank
     */
    public final Long getRank() {
        return rank;
    }

    /**
     * @param rankInput the rank to set
     */
    public final void setRank(final Long rankInput) {
        this.rank = rankInput;
    }

    /**
     *
     * @return the cloned version of this bonus.
     */
    public final IBonus makeCopy() {
        IBonus clonedBonus = new Bonus();
        clonedBonus.setBonusType(this.getBonusType());
        clonedBonus.setRank(this.getRank());
        clonedBonus.setTarget(this.getTarget());
        clonedBonus.setValue(this.getValue());

        return clonedBonus;
    }

    /**
     * @return the dynamicValueProvider
     */
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

}
