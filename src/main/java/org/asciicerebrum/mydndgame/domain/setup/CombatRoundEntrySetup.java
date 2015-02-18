package org.asciicerebrum.mydndgame.domain.setup;

/**
 *
 * @author species8472
 */
public class CombatRoundEntrySetup extends AbstractEntitySetup {

    /**
     * The required properties.
     */
    private static final SetupProperty[] REQUIRED_SINGLE_PROPERTIES
            = {SetupProperty.COMBAT_ROUND_PARTICIPANT,
                SetupProperty.COMBAT_ROUND_POSITION};

    /**
     * @param participantId the participant id.
     */
    public final void setParticipantId(final String participantId) {
        this.getSingleProperties().put(SetupProperty.COMBAT_ROUND_PARTICIPANT,
                participantId);
    }

    /**
     * @param roundPosition the round position.
     */
    public final void setRoundPosition(final String roundPosition) {
        this.getSingleProperties().put(SetupProperty.COMBAT_ROUND_POSITION,
                roundPosition);
    }

    @Override
    public final boolean isSetupComplete() {
        return this.checkRequiredSingleProperties(REQUIRED_SINGLE_PROPERTIES);
    }

}
