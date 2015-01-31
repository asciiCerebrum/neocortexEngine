package org.asciicerebrum.mydndgame.domain.game.entities.prototypes;

import org.asciicerebrum.mydndgame.domain.rules.entities.WeaponCategory;
import org.asciicerebrum.mydndgame.domain.core.mechanics.Boni;
import org.asciicerebrum.mydndgame.domain.core.mechanics.BonusSource;
import org.asciicerebrum.mydndgame.domain.core.mechanics.BonusSources;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverSource;
import org.asciicerebrum.mydndgame.observers.Observers;
import org.asciicerebrum.mydndgame.domain.core.particles.DiceConstant;
import org.asciicerebrum.mydndgame.domain.core.particles.DiceNumber;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.game.entities.Dice;
import org.asciicerebrum.mydndgame.domain.core.mechanics.BonusTarget;
import org.asciicerebrum.mydndgame.domain.core.mechanics.ObserverHook;

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
    public UniqueId getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(UniqueId id) {
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
    public DiceNumber getDiceNumber() {
        return diceNumber;
    }

    /**
     * @param diceNumber the diceNumber to set
     */
    public void setDiceNumber(DiceNumber diceNumber) {
        this.diceNumber = diceNumber;
    }

    /**
     * @return the constValue
     */
    public DiceConstant getConstValue() {
        return constValue;
    }

    /**
     * @param constValue the constValue to set
     */
    public void setConstValue(DiceConstant constValue) {
        this.constValue = constValue;
    }

    /**
     * @return the associatedAttackMode
     */
    @Override
    public final WeaponCategory getAssociatedAttackMode() {
        return associatedAttackMode;
    }

    /**
     * @param associatedAttackModeInput the associatedAttackMode to set
     */
    @Override
    public final void setAssociatedAttackMode(
            final WeaponCategory associatedAttackModeInput) {
        this.associatedAttackMode = associatedAttackModeInput;
    }

    /**
     * @return the targetObservers
     */
    public Observers getTargetObservers() {
        return targetObservers;
    }

    /**
     * @param targetObservers the targetObservers to set
     */
    public void setTargetObservers(Observers targetObservers) {
        this.targetObservers = targetObservers;
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
