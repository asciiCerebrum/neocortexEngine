package org.asciicerebrum.mydndgame.domain.factories;

import org.asciicerebrum.mydndgame.domain.game.CombatRound;
import java.util.Iterator;
import java.util.List;
import org.asciicerebrum.mydndgame.domain.factories.Reassignments.ReassignmentEntry;
import org.asciicerebrum.mydndgame.domain.game.Campaign;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.InventoryItem;
import org.asciicerebrum.mydndgame.domain.setup.EntitySetup;
import org.asciicerebrum.mydndgame.domain.setup.SetupProperty;
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

    /**
     * The factory for creating dnd characters.
     */
    private EntityFactory<DndCharacter> characterFactory;

    /**
     * The factory for creating inventory items.
     */
    private EntityFactory<InventoryItem> inventoryItemFactory;

    /**
     * The factory for creating a combat round instance.
     */
    private EntityFactory<CombatRound> combatRoundFactory;

    @Override
    public final Campaign newEntity(final EntitySetup setup,
            final Reassignments reassignments) {

        Campaign campaign = this.getContext().getBean(Campaign.class);

        final List<EntitySetup> inventoryItemSetups
                = setup.getPropertySetups(SetupProperty.INVENTORY_ITEMS);
        if (inventoryItemSetups != null) {
            for (EntitySetup itemSetup : inventoryItemSetups) {
                campaign.registerUniqueEntity(this.getInventoryItemFactory()
                        .newEntity(itemSetup, reassignments));
            }
        }

        final List<EntitySetup> participantSetups
                = setup.getPropertySetups(SetupProperty.PARTICIPANT_CHARACTERS);
        if (participantSetups != null) {
            for (EntitySetup characterSetup : participantSetups) {
                campaign.registerUniqueEntity(this.getCharacterFactory()
                        .newEntity(characterSetup, reassignments));
            }
        }

        EntitySetup combatRoundSetup
                = setup.getPropertySetup(SetupProperty.COMBAT_ROUND);
        if (combatRoundSetup != null) {
            campaign.setCombatRound(this.getCombatRoundFactory()
                    .newEntity(combatRoundSetup, reassignments));
        }

        // do reassignments because of yet unresolved cyclic dependencies.
        Iterator<ReassignmentEntry> entryIterator = reassignments.getIterator();
        while (entryIterator.hasNext()) {
            //TODO log this
            ReassignmentEntry entry = entryIterator.next();
            entry.getFactory().reAssign(entry.getSetup(), entry.getEntity(),
                    reassignments);
        }

        return campaign;
    }

    @Override
    public final void reAssign(final EntitySetup setup, final Campaign entity,
            final Reassignments reassignments) {
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

    /**
     * @return the context
     */
    public final ApplicationContext getContext() {
        return context;
    }

    /**
     * @return the characterFactory
     */
    public final EntityFactory<DndCharacter> getCharacterFactory() {
        return characterFactory;
    }

    /**
     * @return the inventoryItemFactory
     */
    public final EntityFactory<InventoryItem> getInventoryItemFactory() {
        return inventoryItemFactory;
    }

    /**
     * @return the combatRoundFactory
     */
    public final EntityFactory<CombatRound> getCombatRoundFactory() {
        return combatRoundFactory;
    }

}
