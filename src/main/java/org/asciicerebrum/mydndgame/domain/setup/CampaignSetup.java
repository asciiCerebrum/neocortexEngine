package org.asciicerebrum.mydndgame.domain.setup;

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

}
