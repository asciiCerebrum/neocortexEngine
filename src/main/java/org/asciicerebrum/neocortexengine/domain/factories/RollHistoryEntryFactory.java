package org.asciicerebrum.neocortexengine.domain.factories;

import org.asciicerebrum.neocortexengine.domain.core.particles.BonusValue;
import org.asciicerebrum.neocortexengine.domain.core.particles.DiceNumber;
import org.asciicerebrum.neocortexengine.domain.core.particles.DiceRoll;
import org.asciicerebrum.neocortexengine.domain.core.particles.DiceSides;
import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueId;
import org.asciicerebrum.neocortexengine.domain.mechanics.WorldDate;
import org.asciicerebrum.neocortexengine.domain.events.RollHistoryEntry;
import org.asciicerebrum.neocortexengine.domain.setup.EntitySetup;
import org.asciicerebrum.neocortexengine.domain.setup.SetupIncompleteException;
import org.asciicerebrum.neocortexengine.domain.setup.SetupProperty;

/**
 *
 * @author species8472
 */
public class RollHistoryEntryFactory
        implements EntityFactory<RollHistoryEntry> {

    /**
     * Factory for the world date.
     */
    private EntityFactory<WorldDate> worldDateFactory;

    @Override
    public final RollHistoryEntry newEntity(final EntitySetup setup) {

        if (!setup.isSetupComplete()) {
            throw new SetupIncompleteException("The setup of the roll history "
                    + "entry is not complete.");
        }

        final RollHistoryEntry rollHistoryEntry = new RollHistoryEntry();

        rollHistoryEntry.setSourceDndCharacterId(new UniqueId(setup.getProperty(
                SetupProperty.ROLL_HISTORY_SOURCE_CHARACTER_ID)));

        if (setup.getProperties(SetupProperty.ROLL_HISTORY_TARGET_CHARACTER_IDS)
                != null) {
            for (String targetIdString : setup.getProperties(
                    SetupProperty.ROLL_HISTORY_TARGET_CHARACTER_IDS)) {
                UniqueId targetId = new UniqueId(targetIdString);
                rollHistoryEntry.getTargetDndCharacterIds().add(targetId);
            }
        }

        rollHistoryEntry.setContextEntityId(new UniqueId(setup.getProperty(
                SetupProperty.ROLL_HISTORY_CONTEXT_ENTITY_ID)));

        rollHistoryEntry.setWorldDate(this.getWorldDateFactory().newEntity(
                setup.getPropertySetup(SetupProperty.ROLL_HISTORY_WORLD_DATE)));

        rollHistoryEntry.setDiceNumber(new DiceNumber(setup.getProperty(
                SetupProperty.ROLL_HISTORY_DICE_NUMBER)));

        rollHistoryEntry.setDiceSides(new DiceSides(setup.getProperty(
                SetupProperty.ROLL_HISTORY_DICE_SIDES)));

        rollHistoryEntry.setBonusValue(new BonusValue(setup.getProperty(
                SetupProperty.ROLL_HISTORY_BONUS_VALUE)));

        rollHistoryEntry.setTotalResult(new DiceRoll(setup.getProperty(
                SetupProperty.ROLL_HISTORY_TOTAL_RESULT)));

        rollHistoryEntry.setDiceActionId(new UniqueId(setup.getProperty(
                SetupProperty.ROLL_HISTORY_DICEACTION_ID)));

        return rollHistoryEntry;
    }

    /**
     * @param worldDateFactoryInput the worldDateFactory to set
     */
    public final void setWorldDateFactory(
            final EntityFactory<WorldDate> worldDateFactoryInput) {
        this.worldDateFactory = worldDateFactoryInput;
    }

    /**
     * @return the worldDateFactory
     */
    public final EntityFactory<WorldDate> getWorldDateFactory() {
        return worldDateFactory;
    }

}
