package org.asciicerebrum.mydndgame.domain.setup;

/**
 *
 * @author species8472
 */
public class WorldDateSetup extends AbstractEntitySetup {

    private static final SetupProperty[] REQUIRED_SINGLE_PROPERTIES
            = {SetupProperty.WORLD_DATE_ROUND,
                SetupProperty.WORLD_DATE_ROUND_POSITION};

    @Override
    public boolean isSetupComplete() {
        return this.checkRequiredSingleProperties(REQUIRED_SINGLE_PROPERTIES);
    }

    public final void setCombatRoundNumber(final String combatRoundNumber) {
        this.getSingleProperties().put(SetupProperty.WORLD_DATE_ROUND,
                combatRoundNumber);
    }

    public final void setCombatRoundPosition(final String combatRoundPosition) {
        this.getSingleProperties().put(SetupProperty.WORLD_DATE_ROUND_POSITION,
                combatRoundPosition);
    }

}
