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
    private DiceAction target;

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
     * @return the target
     */
    public DiceAction getTarget() {
        return target;
    }

    /**
     * @param target the target to set
     */
    public void setTarget(DiceAction target) {
        this.target = target;
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
        clonedBonus.setBonusType(this.bonusType);
        clonedBonus.setRank(this.rank);
        clonedBonus.setTarget(this.target);
        clonedBonus.setValue(this.value);
        
        return clonedBonus;
    }

}
