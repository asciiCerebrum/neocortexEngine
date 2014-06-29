package org.asciicerebrum.mydndgame;

/**
 *
 * @author species8472
 */
public class Bonus {

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
    public final Bonus makeCopy() {
        Bonus clonedBonus = new Bonus();
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
