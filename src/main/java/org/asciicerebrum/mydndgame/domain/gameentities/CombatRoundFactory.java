package org.asciicerebrum.mydndgame.domain.gameentities;

import java.util.List;
import org.asciicerebrum.mydndgame.domain.core.attribution.WorldDate;
import org.asciicerebrum.mydndgame.domain.gameentities.setup.EntitySetup;
import org.asciicerebrum.mydndgame.domain.gameentities.setup.SetupIncompleteException;
import org.asciicerebrum.mydndgame.domain.gameentities.setup.SetupProperty;

/**
 *
 * @author species8472
 */
public class CombatRoundFactory implements EntityFactory<CombatRound> {

    private EntityFactory<CombatRoundEntry> combatRoundEntryFactory;

    private EntityFactory<WorldDate> worldDateFactory;

    @Override
    public final CombatRound newEntity(final EntitySetup<CombatRound> setup,
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

    public final void reAssign(final EntitySetup<CombatRound> setup,
            final CombatRound entity) {

    }

}
