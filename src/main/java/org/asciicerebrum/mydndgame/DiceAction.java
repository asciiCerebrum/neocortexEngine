package org.asciicerebrum.mydndgame;

import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.mydndgame.interfaces.entities.BonusTarget;
import org.asciicerebrum.mydndgame.interfaces.entities.IDice;
import org.asciicerebrum.mydndgame.interfaces.entities.IDiceAction;
import org.asciicerebrum.mydndgame.interfaces.entities.IObserver;
import org.asciicerebrum.mydndgame.interfaces.entities.IWeaponCategory;
import org.asciicerebrum.mydndgame.interfaces.entities.ObserverHook;

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
     * The observers of this dice action. They are designed to be registered in
     * the targeted character to modify certain values of all kinds of
     * attributes.
     */
    private List<IObserver> targetObservers = new ArrayList<IObserver>();

    /**
     * The observer hook (ranged/melee) associated with this bonus target. Can
     * also be null.
     */
    private ObserverHook associatedHook;

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
     * {@inheritDoc}
     */
    @Override
    public final IDice getDiceType() {
        return diceType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setDiceType(final IDice diceTypeInput) {
        this.diceType = diceTypeInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Long getDiceNumber() {
        return diceNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
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

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<IObserver> getTargetObservers() {
        return targetObservers;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setTargetObservers(
            final List<IObserver> targetObserversInput) {
        this.targetObservers = targetObserversInput;
    }

    /**
     * @return the associatedHook
     */
    public ObserverHook getAssociatedHook() {
        return associatedHook;
    }

    /**
     * @param associatedHook the associatedHook to set
     */
    public void setAssociatedHook(ObserverHook associatedHook) {
        this.associatedHook = associatedHook;
    }
}
