package org.asciicerebrum.mydndgame.domain.gameentities.setup;

import org.asciicerebrum.mydndgame.domain.core.attribution.WorldDate;

/**
 *
 * @author species8472
 */
public class WorldDateSetup extends AbstractEntitySetup<WorldDate> {

    private static final SetupProperty[] REQUIRED_SINGLE_PROPERTIES
            = {SetupProperty.WORLD_DATE_ROUND,
                SetupProperty.WORLD_DATE_ROUND_POSITION};

    public boolean isSetupComplete() {
        return this.checkRequiredSingleProperties(REQUIRED_SINGLE_PROPERTIES);
    }

    public final void setCombatRoundNumber(final String combatRoundNumber) {
        this.singleProperties.put(SetupProperty.WORLD_DATE_ROUND,
                combatRoundNumber);
    }

    public final void setCombatRoundPosition(final String combatRoundPosition) {
        this.singleProperties.put(SetupProperty.WORLD_DATE_ROUND_POSITION,
                combatRoundPosition);
    }

}
