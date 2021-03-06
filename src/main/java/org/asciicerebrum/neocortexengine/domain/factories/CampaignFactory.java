package org.asciicerebrum.neocortexengine.domain.factories;

import java.util.List;
import org.asciicerebrum.neocortexengine.domain.game.CombatRound;
import org.asciicerebrum.neocortexengine.domain.game.Campaign;
import org.asciicerebrum.neocortexengine.domain.events.RollHistory;
import org.asciicerebrum.neocortexengine.domain.events.RollHistoryEntry;
import org.asciicerebrum.neocortexengine.domain.setup.EntitySetup;
import org.asciicerebrum.neocortexengine.domain.setup.SetupProperty;
import org.asciicerebrum.neocortexengine.infrastructure.ApplicationContextProvider;

/**
 *
 * @author species8472
 */
public class CampaignFactory implements EntityFactory<Campaign> {

    /**
     * The factory for creating a combat round instance.
     */
    private EntityFactory<CombatRound> combatRoundFactory;

    /**
     * The factory for creating a roll history entry.
     */
    private EntityFactory<RollHistoryEntry> rollHistoryEntryFactory;

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

        List<EntitySetup> rollSetups = setup.getPropertySetups(
                SetupProperty.ROLL_HISTORY_ENTRIES);
        if (rollSetups != null) {
            final RollHistory rollHistory = campaign.getRollHistory();

            for (final EntitySetup rollSetup : rollSetups) {
                rollHistory.add(this.getRollHistoryEntryFactory().newEntity(
                        rollSetup));
            }
        }

        return campaign;
    }

    /**
     * Builds a standard empty campaign entity.
     *
     * @return the fresh and empty campaign.
     */
    public final Campaign newEntity() {
        return ApplicationContextProvider
                .getApplicationContext().getBean(Campaign.class);
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

    /**
     * @return the rollHistoryEntryFactory
     */
    public final EntityFactory<RollHistoryEntry> getRollHistoryEntryFactory() {
        return rollHistoryEntryFactory;
    }

    /**
     * @param rollHistoryEntryFactoryIn the rollHistoryEntryFactory to set
     */
    public final void setRollHistoryEntryFactory(
            final EntityFactory<RollHistoryEntry> rollHistoryEntryFactoryIn) {
        this.rollHistoryEntryFactory = rollHistoryEntryFactoryIn;
    }

}
