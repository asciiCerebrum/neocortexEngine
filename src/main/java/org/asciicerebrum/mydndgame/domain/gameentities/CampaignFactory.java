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
    public Campaign newEntity(EntitySetup setup,
            Reassignments reassignments) {

        Campaign campaign = this.context.getBean(Campaign.class);

        for (EntitySetup itemSetup
                : setup.getPropertySetups(
                        SetupProperty.INVENTORY_ITEMS)) {
            campaign.registerUniqueEntity(
                    this.inventoryItemFactory.newEntity(itemSetup,
                            reassignments));
        }

        for (EntitySetup characterSetup
                : setup.getPropertySetups(
                        SetupProperty.PARTICIPANT_CHARACTERS)) {
            campaign.registerUniqueEntity(
                    this.characterFactory.newEntity(characterSetup,
                            reassignments));
        }

        EntitySetup combatRoundSetup
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
    public void reAssign(EntitySetup setup, Campaign entity) {
        // nothing to do here
    }

    /**
     * @param contextInput the context to set
     */
    public final void setContext(final ApplicationContext contextInput) {
        this.context = contextInput;
    }

    /**
     * @param characterFactoryInput the characterFactory to set
     */
    public final void setCharacterFactory(
            final EntityFactory<DndCharacter> characterFactoryInput) {
        this.characterFactory = characterFactoryInput;
    }

    /**
     * @param inventoryItemFactoryInput the inventoryItemFactory to set
     */
    public final void setInventoryItemFactory(
            final EntityFactory<InventoryItem> inventoryItemFactoryInput) {
        this.inventoryItemFactory = inventoryItemFactoryInput;
    }

    /**
     * @param combatRoundFactoryInput the combatRoundFactory to set
     */
    public final void setCombatRoundFactory(
            final EntityFactory<CombatRound> combatRoundFactoryInput) {
        this.combatRoundFactory = combatRoundFactoryInput;
    }

}
