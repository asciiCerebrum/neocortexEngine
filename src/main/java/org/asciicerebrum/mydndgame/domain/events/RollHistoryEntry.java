package org.asciicerebrum.mydndgame.domain.events;

import org.asciicerebrum.mydndgame.domain.core.particles.BonusValue;
import org.asciicerebrum.mydndgame.domain.core.particles.DiceNumber;
import org.asciicerebrum.mydndgame.domain.core.particles.DiceRoll;
import org.asciicerebrum.mydndgame.domain.core.particles.DiceSides;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueIds;
import org.asciicerebrum.mydndgame.domain.mechanics.WorldDate;

/**
 *
 * @author species8472
 */
public class RollHistoryEntry {

    /**
     * The character making the roll.
     */
    private UniqueId sourceDndCharacterId;

    /**
     * The characters affected by the roll, if any.
     */
    private UniqueIds targetDndCharacterIds = new UniqueIds();

    /**
     * The context of the roll, for example the weapon, if any.
     */
    private UniqueId contextEntityId;

    /**
     * The current date of the dice roll.
     */
    private WorldDate worldDate;

    /**
     * The number of dice the roll includes.
     */
    private DiceNumber diceNumber;

    /**
     * The sides of the die used.
     */
    private DiceSides diceSides;

    /**
     * The bonus value added to the dice roll.
     */
    private BonusValue bonusValue;

    /**
     * The total result of the dice roll plus the bonus.
     */
    private DiceRoll totalResult;

    /**
     * The id of the dice action.
     */
    private UniqueId diceActionId;

    /**
     * @return the sourceDndCharacterId
     */
    public final UniqueId getSourceDndCharacterId() {
        return sourceDndCharacterId;
    }

    /**
     * @param sourceDndCharacterIdInput the sourceDndCharacterId to set
     */
    public final void setSourceDndCharacterId(
            final UniqueId sourceDndCharacterIdInput) {
        this.sourceDndCharacterId = sourceDndCharacterIdInput;
    }

    /**
     * @return the targetDndCharacterId
     */
    public final UniqueIds getTargetDndCharacterIds() {
        return targetDndCharacterIds;
    }

    /**
     * @param targetDndCharacterIdsInput the targetDndCharacterId to set
     */
    public final void setTargetDndCharacterIds(
            final UniqueIds targetDndCharacterIdsInput) {
        this.targetDndCharacterIds = targetDndCharacterIdsInput;
    }

    /**
     * @return the contextEntityId
     */
    public final UniqueId getContextEntityId() {
        return contextEntityId;
    }

    /**
     * @param contextEntityIdInput the contextEntityId to set
     */
    public final void setContextEntityId(final UniqueId contextEntityIdInput) {
        this.contextEntityId = contextEntityIdInput;
    }

    /**
     * @return the worldDate
     */
    public final WorldDate getWorldDate() {
        return worldDate;
    }

    /**
     * @param worldDateInput the worldDate to set
     */
    public final void setWorldDate(final WorldDate worldDateInput) {
        this.worldDate = worldDateInput;
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
     * @return the diceSides
     */
    public final DiceSides getDiceSides() {
        return diceSides;
    }

    /**
     * @param diceSidesInput the diceSides to set
     */
    public final void setDiceSides(final DiceSides diceSidesInput) {
        this.diceSides = diceSidesInput;
    }

    /**
     * @return the bonusValue
     */
    public final BonusValue getBonusValue() {
        return bonusValue;
    }

    /**
     * @param bonusValueInput the bonusValue to set
     */
    public final void setBonusValue(final BonusValue bonusValueInput) {
        this.bonusValue = bonusValueInput;
    }

    /**
     * @return the totalResult
     */
    public final DiceRoll getTotalResult() {
        return totalResult;
    }

    /**
     * @param totalResultInput the totalResult to set
     */
    public final void setTotalResult(final DiceRoll totalResultInput) {
        this.totalResult = totalResultInput;
    }

    /**
     * @return the diceActionId
     */
    public final UniqueId getDiceActionId() {
        return diceActionId;
    }

    /**
     * @param diceActionIdInput the diceActionId to set
     */
    public final void setDiceActionId(final UniqueId diceActionIdInput) {
        this.diceActionId = diceActionIdInput;
    }

}
