package org.asciicerebrum.mydndgame;

/**
 *
 * @author species8472
 */
public class DiceAction implements BonusTarget {

    /**
     * The unique id of this dice action.
     */
    private String id;
    /**
     * The type of die associates with this dice action. E.g. attack roll has a
     * d20.
     */
    private Dice diceType;
    /**
     * The number of dice associated with this action. E.g. 1d20 in attack roll.
     */
    private Long diceNumber;
    /**
     * The costant value, if there is no randomness given in this action.
     */
    private Long constValue;

    /**
     * @return the id
     */
    public final String getId() {
        return id;
    }

    /**
     * @param idInput the id to set
     */
    public final void setId(final String idInput) {
        this.id = idInput;
    }

    /**
     * @return the diceType
     */
    public final Dice getDiceType() {
        return diceType;
    }

    /**
     * @param diceTypeInput the diceType to set
     */
    public final void setDiceType(final Dice diceTypeInput) {
        this.diceType = diceTypeInput;
    }

    /**
     * @return the diceNumber
     */
    public final Long getDiceNumber() {
        return diceNumber;
    }

    /**
     * @param diceNumberInput the diceNumber to set
     */
    public final void setDiceNumber(final Long diceNumberInput) {
        this.diceNumber = diceNumberInput;
    }

    /**
     * @return the constValue
     */
    public final Long getConstValue() {
        return constValue;
    }

    /**
     * @param constValueInput the constValue to set
     */
    public final void setConstValue(final Long constValueInput) {
        this.constValue = constValueInput;
    }
}
