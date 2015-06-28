package org.asciicerebrum.neocortexengine.domain.setup;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author species8472
 */
public class CampaignSetup extends AbstractEntitySetup {

    @Override
    public final boolean isSetupComplete() {
        return true;
    }

    /**
     * @param combatRoundSetup the combat round setup.
     */
    public final void setCombatRound(
            final EntitySetup combatRoundSetup) {
        this.getSingleSetup().put(SetupProperty.COMBAT_ROUND, combatRoundSetup);
    }

    /**
     * @param rollHistoryEntrySetup the roll history entry.
     */
    public final void addRollHistoryEntry(
            final EntitySetup rollHistoryEntrySetup) {
        List<EntitySetup> rollHistoryEntries
                = this.getListSetup().get(SetupProperty.ROLL_HISTORY_ENTRIES);
        if (rollHistoryEntries == null) {
            rollHistoryEntries = new ArrayList<EntitySetup>();
            this.getListSetup().put(SetupProperty.ROLL_HISTORY_ENTRIES,
                    rollHistoryEntries);
        }
        rollHistoryEntries.add(rollHistoryEntrySetup);
    }

}
