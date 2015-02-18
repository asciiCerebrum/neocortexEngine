package org.asciicerebrum.mydndgame.domain.ruleentities;

import org.asciicerebrum.mydndgame.domain.mechanics.bonus.Boni;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSource;
import org.asciicerebrum.mydndgame.domain.mechanics.bonus.source.BonusSources;
import org.asciicerebrum.mydndgame.domain.mechanics.BonusTarget;
import org.asciicerebrum.mydndgame.domain.mechanics.ObserverHook;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.source.ObserverSource;
import org.asciicerebrum.mydndgame.domain.mechanics.observer.Observers;
import org.asciicerebrum.mydndgame.domain.core.particles.DiceConstant;
import org.asciicerebrum.mydndgame.domain.core.particles.DiceNumber;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;

/**
 *
 * @author species8472
 */
public class DiceAction implements BonusSource, BonusTarget, ObserverSource {

    /**
     * The unique id of this dice action.
     */
    private UniqueId id;
    /**
     * The type of die associates with this dice action. E.g. attack roll has a
     * d20.
     */
    private Dice diceType;
    /**
     * The number of dice associated with this action. E.g. 1d20 in attack roll.
     */
    private DiceNumber diceNumber;
    /**
     * The costant value, if there is no randomness given in this action.
     */
    private DiceConstant constValue;
    /**
     * The attack mode (ranged/melee) associated with this bonus target. Can
     * also be null.
     */
    private WeaponCategory associatedAttackMode;
    /**
     * The observers of this dice action. They are designed to be registered in
     * the targeted character to modify certain values of all kinds of
     * attributes.
     */
    private Observers targetObservers;

    /**
     * The observer hook (ranged/melee) associated with this bonus target. Can
     * also be null.
     */
    private ObserverHook associatedHook;

    /**
     * @return the id
     */
    public final UniqueId getId() {
        return id;
    }

    /**
     * @param idInput the id to set
     */
    public final void setId(final UniqueId idInput) {
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
    public final DiceNumber getDiceNumber() {
        return diceNumber;
    }

    /**
     * @param diceNumberInput the diceNumber to set
     */
    public final void setDiceNumber(final DiceNumber diceNumberInput) {
        this.diceNumber = diceNumberInput;
    }

    /**
     * @return the constValue
     */
    public final DiceConstant getConstValue() {
        return constValue;
    }

    /**
     * @param constValueInput the constValue to set
     */
    public final void setConstValue(final DiceConstant constValueInput) {
        this.constValue = constValueInput;
    }

    /**
     * @return the associatedAttackMode
     */
    public final WeaponCategory getAssociatedAttackMode() {
        return associatedAttackMode;
    }

    /**
     * @param associatedAttackModeInput the associatedAttackMode to set
     */
    public final void setAssociatedAttackMode(
            final WeaponCategory associatedAttackModeInput) {
        this.associatedAttackMode = associatedAttackModeInput;
    }

    /**
     * @return the targetObservers
     */
    public final Observers getTargetObservers() {
        return targetObservers;
    }

    /**
     * @param targetObserversInput the targetObservers to set
     */
    public final void setTargetObservers(final Observers targetObserversInput) {
        this.targetObservers = targetObserversInput;
    }

    /**
     * @return the associatedHook
     */
    @Override
    public final ObserverHook getAssociatedHook() {
        return associatedHook;
    }

    /**
     * @param associatedHookInput the associatedHook to set
     */
    @Override
    public final void setAssociatedHook(
            final ObserverHook associatedHookInput) {
        this.associatedHook = associatedHookInput;
    }

    /**
     * @return the observers.
     */
    public final Observers getObservers() {
        return this.targetObservers;
    }

    @Override
    public final Boni getBoni() {
        return Boni.EMPTY_BONI;
    }

    @Override
    public final BonusSources getBonusSources() {
        return BonusSources.EMPTY_BONUSSOURCES;
    }

}
