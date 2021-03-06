package org.asciicerebrum.neocortexengine.domain.factories;

import java.util.List;
import org.asciicerebrum.neocortexengine.domain.game.CombatRound;
import org.asciicerebrum.neocortexengine.domain.game.CombatRoundEntry;
import org.asciicerebrum.neocortexengine.domain.mechanics.WorldDate;
import org.asciicerebrum.neocortexengine.domain.setup.EntitySetup;
import org.asciicerebrum.neocortexengine.domain.setup.SetupIncompleteException;
import org.asciicerebrum.neocortexengine.domain.setup.SetupProperty;

/**
 *
 * @author species8472
 */
public class CombatRoundFactory implements EntityFactory<CombatRound> {

    /**
     * The factory for combat round entries.
     */
    private EntityFactory<CombatRoundEntry> combatRoundEntryFactory;

    /**
     * The factory for the world date.
     */
    private EntityFactory<WorldDate> worldDateFactory;

    @Override
    public final CombatRound newEntity(final EntitySetup setup) {

        if (!setup.isSetupComplete()) {
            throw new SetupIncompleteException("The setup of the combat round"
                    + " is not complete.");
        }

        final CombatRound combatRound = new CombatRound();

        List<EntitySetup> crEntrySetups
                = setup.getPropertySetups(SetupProperty.COMBAT_ROUND_ENTRIES);
        if (crEntrySetups != null) {
            for (EntitySetup crEntrySetup : crEntrySetups) {
                combatRound.addCombatRoundEntry(
                        this.getCombatRoundEntryFactory().newEntity(
                                crEntrySetup));
            }
        }

        EntitySetup dateSetup = setup.getPropertySetup(
                SetupProperty.COMBAT_ROUND_CURRENT_DATE);
        if (dateSetup != null) {
            combatRound.setCurrentDate(this.getWorldDateFactory()
                    .newEntity(dateSetup));
        }

        return combatRound;
    }

    /**
     * @param combatRoundEntryFactoryIn the combatRoundEntryFactory to set
     */
    public final void setCombatRoundEntryFactory(
            final EntityFactory<CombatRoundEntry> combatRoundEntryFactoryIn) {
        this.combatRoundEntryFactory = combatRoundEntryFactoryIn;
    }

    /**
     * @param worldDateFactoryInput the worldDateFactory to set
     */
    public final void setWorldDateFactory(
            final EntityFactory<WorldDate> worldDateFactoryInput) {
        this.worldDateFactory = worldDateFactoryInput;
    }

    /**
     * @return the combatRoundEntryFactory
     */
    public final EntityFactory<CombatRoundEntry> getCombatRoundEntryFactory() {
        return combatRoundEntryFactory;
    }

    /**
     * @return the worldDateFactory
     */
    public final EntityFactory<WorldDate> getWorldDateFactory() {
        return worldDateFactory;
    }

}
