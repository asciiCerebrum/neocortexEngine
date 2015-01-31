package org.asciicerebrum.mydndgame.domain.game;

import java.util.List;
import org.asciicerebrum.mydndgame.domain.factories.Reassignments;
import org.asciicerebrum.mydndgame.domain.factories.EntityFactory;
import org.asciicerebrum.mydndgame.domain.setup.EntitySetup;
import org.asciicerebrum.mydndgame.domain.setup.SetupIncompleteException;
import org.asciicerebrum.mydndgame.domain.setup.SetupProperty;

/**
 *
 * @author species8472
 */
public class CombatRoundFactory implements EntityFactory<CombatRound> {

    private EntityFactory<CombatRoundEntry> combatRoundEntryFactory;

    private EntityFactory<WorldDate> worldDateFactory;

    @Override
    public final CombatRound newEntity(final EntitySetup setup,
            final Reassignments reassignments) {

        if (!setup.isSetupComplete()) {
            throw new SetupIncompleteException("The setup of the combat round"
                    + " is not complete.");
        }

        final CombatRound combatRound = new CombatRound();

        List<EntitySetup> crEntrySetups
                = setup.getPropertySetups(SetupProperty.COMBAT_ROUND_ENTRIES);
        if (crEntrySetups != null) {
            CombatRoundEntries crEntries = new CombatRoundEntries();
            for (EntitySetup crEntrySetup : crEntrySetups) {
                crEntries.addCombatRoundEntry(
                        this.combatRoundEntryFactory.newEntity(crEntrySetup,
                                reassignments));
            }
            combatRound.setCombatRoundEntries(crEntries);
        }

        EntitySetup dateSetup = setup.getPropertySetup(
                SetupProperty.COMBAT_ROUND_CURRENT_DATE);
        if (dateSetup != null) {
            combatRound.setCurrentDate(this.worldDateFactory
                    .newEntity(dateSetup, reassignments));
        }

        return combatRound;
    }

    @Override
    public final void reAssign(final EntitySetup setup,
            final CombatRound entity) {
        //nothing to do here
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

}
