package org.asciicerebrum.mydndgame.domain.gameentities.setup;

import java.util.List;
import org.asciicerebrum.mydndgame.domain.gameentities.CombatRound;

/**
 *
 * @author species8472
 */
public class CombatRoundSetup extends AbstractEntitySetup<CombatRound> {

    public final void setCombatRoundEntries(
            final List<EntitySetup> combatRoundEntries) {
        this.listSetup.put(SetupProperty.COMBAT_ROUND_ENTRIES,
                combatRoundEntries);
    }

    public final void setCurrentDate(final EntitySetup worldDate) {
        this.singleSetup.put(SetupProperty.COMBAT_ROUND_CURRENT_DATE,
                worldDate);
    }

    public boolean isSetupComplete() {
        return true;
    }

}
