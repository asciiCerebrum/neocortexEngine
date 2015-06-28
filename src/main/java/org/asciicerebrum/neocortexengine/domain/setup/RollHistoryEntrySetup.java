package org.asciicerebrum.neocortexengine.domain.setup;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author species8472
 */
public class RollHistoryEntrySetup extends AbstractEntitySetup {

    /**
     * The required properties.
     */
    private static final SetupProperty[] REQUIRED_SINGLE_PROPERTIES
            = {SetupProperty.ROLL_HISTORY_SOURCE_CHARACTER_ID,
                SetupProperty.ROLL_HISTORY_DICE_NUMBER,
                SetupProperty.ROLL_HISTORY_DICE_SIDES,
                SetupProperty.ROLL_HISTORY_TOTAL_RESULT,
                SetupProperty.ROLL_HISTORY_DICEACTION_ID};

    /**
     * The required properties for sub setups.
     */
    private static final SetupProperty[] REQUIRED_SETUP_PROPERTIES
            = {SetupProperty.ROLL_HISTORY_WORLD_DATE};

    @Override
    public final boolean isSetupComplete() {
        return this.checkRequiredSingleProperties(REQUIRED_SINGLE_PROPERTIES)
                && this.checkRequiredSingleSetup(REQUIRED_SETUP_PROPERTIES);
    }

    /**
     *
     * @param sourceId the source id.
     */
    public final void setSourceCharacterId(final String sourceId) {
        this.getSingleProperties().put(
                SetupProperty.ROLL_HISTORY_SOURCE_CHARACTER_ID, sourceId);
    }

    /**
     *
     * @param targetId the target id.
     */
    public final void addTargetCharacterId(final String targetId) {
        List<String> targets = this.getListProperties().get(
                SetupProperty.ROLL_HISTORY_TARGET_CHARACTER_IDS);
        if (targets == null) {
            targets = new ArrayList<String>();
            this.getListProperties().put(
                    SetupProperty.ROLL_HISTORY_TARGET_CHARACTER_IDS, targets);
        }
        targets.add(targetId);
    }

    /**
     *
     * @param contextId the context id.
     */
    public final void setContextEntityId(final String contextId) {
        this.getSingleProperties().put(
                SetupProperty.ROLL_HISTORY_CONTEXT_ENTITY_ID, contextId);
    }

    /**
     *
     * @param setup the world date setup.
     */
    public final void setWorldDate(final WorldDateSetup setup) {
        this.getSingleSetup().put(SetupProperty.ROLL_HISTORY_WORLD_DATE,
                (EntitySetup) setup);
    }

    /**
     *
     * @param diceNumber the number of dice.
     */
    public final void setDiceNumber(final String diceNumber) {
        this.getSingleProperties().put(
                SetupProperty.ROLL_HISTORY_DICE_NUMBER, diceNumber);
    }

    /**
     *
     * @param diceSides the number of sides on the dice.
     */
    public final void setDiceSides(final String diceSides) {
        this.getSingleProperties().put(
                SetupProperty.ROLL_HISTORY_DICE_SIDES, diceSides);
    }

    /**
     *
     * @param bonusValue the bonus value.
     */
    public final void setBonusValue(final String bonusValue) {
        this.getSingleProperties().put(
                SetupProperty.ROLL_HISTORY_BONUS_VALUE, bonusValue);
    }

    /**
     *
     * @param totalResult the total result with the bonus.
     */
    public final void setTotalResult(final String totalResult) {
        this.getSingleProperties().put(
                SetupProperty.ROLL_HISTORY_TOTAL_RESULT, totalResult);
    }

    /**
     *
     * @param diceActionId the id of the dice action.
     */
    public final void setDiceActionId(final String diceActionId) {
        this.getSingleProperties().put(
                SetupProperty.ROLL_HISTORY_DICEACTION_ID, diceActionId);
    }

}
