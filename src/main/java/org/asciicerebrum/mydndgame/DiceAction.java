package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.interfaces.entities.BonusTarget;
import org.asciicerebrum.mydndgame.interfaces.entities.IDice;
import org.asciicerebrum.mydndgame.interfaces.entities.IDiceAction;
import org.asciicerebrum.mydndgame.interfaces.entities.IWeaponCategory;

/**
 *
 * @author species8472
 */
public class DiceAction implements IDiceAction, BonusTarget {

    /**
     * The unique id of this dice action.
     */
    private String id;
    /**
     * The type of die associates with this dice action. E.g. attack roll has a
     * d20.
     */
    private IDice diceType;
    /**
     * The number of dice associated with this action. E.g. 1d20 in attack roll.
     */
    private Long diceNumber;
    /**
     * The costant value, if there is no randomness given in this action.
     */
    private Long constValue;
    /**
     * The attack mode (ranged/melee) associated with this bonus target. Can
     * also be null.
     */
    private IWeaponCategory associatedAttackMode;

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
    public final IDice getDiceType() {
        return diceType;
    }

    /**
     * @param diceTypeInput the diceType to set
     */
    public final void setDiceType(final IDice diceTypeInput) {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public final IWeaponCategory getAssociatedAttackMode() {
        return associatedAttackMode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setAssociatedAttackMode(
            final IWeaponCategory associatedAttackModeInput) {
        this.associatedAttackMode = associatedAttackModeInput;
    }
}
