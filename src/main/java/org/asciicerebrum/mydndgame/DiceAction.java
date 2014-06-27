package org.asciicerebrum.mydndgame;

/**
 *
 * @author species8472
 */
public class DiceAction {

    private String id;
    private Dice diceType;
    private Long diceNumber;
    private Long constValue;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the diceType
     */
    public Dice getDiceType() {
        return diceType;
    }

    /**
     * @param diceType the diceType to set
     */
    public void setDiceType(Dice diceType) {
        this.diceType = diceType;
    }

    /**
     * @return the diceNumber
     */
    public Long getDiceNumber() {
        return diceNumber;
    }

    /**
     * @param diceNumber the diceNumber to set
     */
    public void setDiceNumber(Long diceNumber) {
        this.diceNumber = diceNumber;
    }

    /**
     * @return the constValue
     */
    public Long getConstValue() {
        return constValue;
    }

    /**
     * @param constValue the constValue to set
     */
    public void setConstValue(Long constValue) {
        this.constValue = constValue;
    }
}
