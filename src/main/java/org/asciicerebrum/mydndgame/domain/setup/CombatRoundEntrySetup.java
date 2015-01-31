package org.asciicerebrum.mydndgame.domain.setup;

/**
 *
 * @author species8472
 */
public class CombatRoundEntrySetup extends AbstractEntitySetup {

    private static final SetupProperty[] REQUIRED_SINGLE_PROPERTIES
            = {SetupProperty.COMBAT_ROUND_PARTICIPANT,
                SetupProperty.COMBAT_ROUND_POSITION};

    public final void setParticipantId(final String participantId) {
        this.singleProperties.put(SetupProperty.COMBAT_ROUND_PARTICIPANT,
                participantId);
    }

    public final void setRoundPosition(final String roundPosition) {
        this.singleProperties.put(SetupProperty.COMBAT_ROUND_POSITION,
                roundPosition);
    }

    @Override
    public final boolean isSetupComplete() {
        return this.checkRequiredSingleProperties(REQUIRED_SINGLE_PROPERTIES);
    }

}
