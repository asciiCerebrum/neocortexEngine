package org.asciicerebrum.mydndgame.domain.setup;

import java.util.List;

/**
 *
 * @author species8472
 */
public class CombatRoundSetup extends AbstractEntitySetup {

    /**
     * @param combatRoundEntries the combat round entries.
     */
    public final void setCombatRoundEntries(
            final List<EntitySetup> combatRoundEntries) {
        this.getListSetup().put(SetupProperty.COMBAT_ROUND_ENTRIES,
                combatRoundEntries);
    }

    /**
     * @param worldDate the current date.
     */
    public final void setCurrentDate(final EntitySetup worldDate) {
        this.getSingleSetup().put(SetupProperty.COMBAT_ROUND_CURRENT_DATE,
                worldDate);
    }

    @Override
    public final boolean isSetupComplete() {
        return true;
    }

}
