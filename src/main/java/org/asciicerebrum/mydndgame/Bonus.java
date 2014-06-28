package org.asciicerebrum.mydndgame;

/**
 *
 * @author species8472
 */
public class Bonus {

    private Long value;
    // rank for multiple boni in a row, e.g. base attack
    // boni +2/+1 and the like; rank start from 0
    private Long rank;
    private BonusType bonusType;
    private BonusTarget target;
    // for dynamic bonus values, e.g. the dex-bonus on ac is dynamic because
    // it depends on the dex-value and so on the dex modifier, which is
    // calculated.
    // dynamic value has priority over static one.
    private BonusValueProvider dynamicValueProvider;

    /**
     * @return the value
     */
    public Long getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Long value) {
        this.value = value;
    }

    /**
     * @return the bonusType
     */
    public BonusType getBonusType() {
        return bonusType;
    }

    /**
     * @param bonusType the bonusType to set
     */
    public void setBonusType(BonusType bonusType) {
        this.bonusType = bonusType;
    }

    /**
     * @return the rank
     */
    public Long getRank() {
        return rank;
    }

    /**
     * @param rank the rank to set
     */
    public void setRank(Long rank) {
        this.rank = rank;
    }

    public Bonus makeCopy() {
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
    public BonusValueProvider getDynamicValueProvider() {
        return dynamicValueProvider;
    }

    /**
     * @param dynamicValueProvider the dynamicValueProvider to set
     */
    public void setDynamicValueProvider(BonusValueProvider dynamicValueProvider) {
        this.dynamicValueProvider = dynamicValueProvider;
    }

    /**
     * @return the target
     */
    public BonusTarget getTarget() {
        return target;
    }

    /**
     * @param target the target to set
     */
    public void setTarget(BonusTarget target) {
        this.target = target;
    }

}
