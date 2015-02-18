package org.asciicerebrum.mydndgame.domain.setup;

import java.util.List;

/**
 *
 * @author species8472
 */
public class CombatRoundSetup extends AbstractEntitySetup {

    public final void setCombatRoundEntries(
            final List<EntitySetup> combatRoundEntries) {
        this.getListSetup().put(SetupProperty.COMBAT_ROUND_ENTRIES,
                combatRoundEntries);
    }

    public final void setCurrentDate(final EntitySetup worldDate) {
        this.getSingleSetup().put(SetupProperty.COMBAT_ROUND_CURRENT_DATE,
                worldDate);
    }

    @Override
    public final boolean isSetupComplete() {
        return true;
    }

}
