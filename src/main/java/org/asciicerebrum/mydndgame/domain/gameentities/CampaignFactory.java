package org.asciicerebrum.mydndgame.domain.gameentities;

import java.util.Iterator;
import org.asciicerebrum.mydndgame.domain.gameentities.Reassignments.ReassignmentEntry;
import org.asciicerebrum.mydndgame.domain.gameentities.setup.EntitySetup;
import org.asciicerebrum.mydndgame.domain.gameentities.setup.SetupProperty;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author species8472
 */
public class CampaignFactory implements EntityFactory<Campaign> {

    /**
     * Spring context to get the beans from.
     */
    private ApplicationContext context;

    private EntityFactory<DndCharacter> characterFactory;

    private EntityFactory<InventoryItem> inventoryItemFactory;

    private EntityFactory<CombatRound> combatRoundFactory;

    @Override
    public Campaign newEntity(EntitySetup<Campaign> setup,
            Reassignments reassignments) {

        Campaign campaign = this.context.getBean(Campaign.class);

        for (EntitySetup<InventoryItem> itemSetup
                : setup.getPropertySetups(
                        SetupProperty.INVENTORY_ITEMS)) {
            campaign.registerUniqueEntity(
                    this.inventoryItemFactory.newEntity(itemSetup,
                            reassignments));
        }

        for (EntitySetup<DndCharacter> characterSetup
                : setup.getPropertySetups(
                        SetupProperty.PARTICIPANT_CHARACTERS)) {
            campaign.registerUniqueEntity(
                    this.characterFactory.newEntity(characterSetup,
                            reassignments));
        }

        EntitySetup<CombatRound> combatRoundSetup
                = setup.getPropertySetup(SetupProperty.COMBAT_ROUND);
        if (combatRoundSetup != null) {
            campaign.setCombatRound(this.combatRoundFactory
                    .newEntity(combatRoundSetup, reassignments));
        }

        // do reassignments because of yet unresolved cyclic dependencies.
        Iterator<ReassignmentEntry> entryIterator = reassignments.getIterator();
        while (entryIterator.hasNext()) {
            //TODO log this
            ReassignmentEntry entry = entryIterator.next();
            entry.getFactory().reAssign(entry.getSetup(), entry.getEntity());
        }

        return campaign;
    }

    @Override
    public void reAssign(EntitySetup<Campaign> setup, Campaign entity) {
        // nothing to do here
    }

}
