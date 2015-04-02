package org.asciicerebrum.mydndgame.domain.factories;

import org.asciicerebrum.mydndgame.domain.game.CombatRound;
import org.asciicerebrum.mydndgame.domain.game.Campaign;
import org.asciicerebrum.mydndgame.domain.setup.EntitySetup;
import org.asciicerebrum.mydndgame.domain.setup.SetupProperty;
import org.asciicerebrum.mydndgame.infrastructure.ApplicationContextProvider;

/**
 *
 * @author species8472
 */
public class CampaignFactory implements EntityFactory<Campaign> {

    /**
     * The factory for creating a combat round instance.
     */
    private EntityFactory<CombatRound> combatRoundFactory;

    @Override
    public final Campaign newEntity(final EntitySetup setup) {

        final Campaign campaign = ApplicationContextProvider
                .getApplicationContext().getBean(Campaign.class);

        EntitySetup combatRoundSetup
                = setup.getPropertySetup(SetupProperty.COMBAT_ROUND);
        if (combatRoundSetup != null) {
            campaign.setCombatRound(this.getCombatRoundFactory()
                    .newEntity(combatRoundSetup));
        }

        return campaign;
    }

    /**
     * @param combatRoundFactoryInput the combatRoundFactory to set
     */
    public final void setCombatRoundFactory(
            final EntityFactory<CombatRound> combatRoundFactoryInput) {
        this.combatRoundFactory = combatRoundFactoryInput;
    }

    /**
     * @return the combatRoundFactory
     */
    public final EntityFactory<CombatRound> getCombatRoundFactory() {
        return this.combatRoundFactory;
    }

}
