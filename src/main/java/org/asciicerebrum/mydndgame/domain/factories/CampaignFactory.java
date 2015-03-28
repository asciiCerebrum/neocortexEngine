package org.asciicerebrum.mydndgame.domain.factories;

import org.asciicerebrum.mydndgame.domain.game.CombatRound;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.asciicerebrum.mydndgame.domain.factories.Reassignments.ReassignmentEntry;
import org.asciicerebrum.mydndgame.domain.game.Campaign;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.InventoryItem;
import org.asciicerebrum.mydndgame.domain.setup.EntitySetup;
import org.asciicerebrum.mydndgame.domain.setup.SetupProperty;
import org.asciicerebrum.mydndgame.infrastructure.ApplicationContextProvider;

/**
 *
 * @author species8472
 */
public class CampaignFactory implements EntityFactory<Campaign> {

    /**
     * The factory for creating dnd characters.
     */
    private EntityFactory<DndCharacter> characterFactory;

    /**
     * The factory for creating inventory items.
     */
    private Map<Class, EntityFactory<InventoryItem>> inventoryItemFactories;

    /**
     * The factory for creating a combat round instance.
     */
    private EntityFactory<CombatRound> combatRoundFactory;

    @Override
    public final Campaign newEntity(final EntitySetup setup,
            final Campaign campaignInput) {

        final Campaign campaign = ApplicationContextProvider
                .getApplicationContext().getBean(Campaign.class);

        final List<EntitySetup> inventoryItemSetups
                = setup.getPropertySetups(SetupProperty.INVENTORY_ITEMS);
        if (inventoryItemSetups != null) {
            for (EntitySetup itemSetup : inventoryItemSetups) {
                final Class setupClass = itemSetup.getClass();
                final EntityFactory<InventoryItem> factory
                        = this.getInventoryItemFactories().get(setupClass);
                final InventoryItem item
                        = factory.newEntity(itemSetup, campaign);

                campaign.registerUniqueEntity(item);
            }
        }

        final List<EntitySetup> participantSetups
                = setup.getPropertySetups(SetupProperty.PARTICIPANT_CHARACTERS);
        if (participantSetups != null) {
            for (EntitySetup characterSetup : participantSetups) {
                campaign.registerUniqueEntity(this.getCharacterFactory()
                        .newEntity(characterSetup, campaign));
            }
        }

        EntitySetup combatRoundSetup
                = setup.getPropertySetup(SetupProperty.COMBAT_ROUND);
        if (combatRoundSetup != null) {
            campaign.setCombatRound(this.getCombatRoundFactory()
                    .newEntity(combatRoundSetup, campaign));
        }

        // do reassignments because of yet unresolved cyclic dependencies.
        Iterator<ReassignmentEntry> entryIterator
                = campaign.reassignmentIterator();
        while (entryIterator.hasNext()) {
            //TODO log this
            ReassignmentEntry entry = entryIterator.next();
            entry.getFactory().reAssign(entry.getSetup(), entry.getEntity(),
                    campaign);
        }

        return campaign;
    }

    @Override
    public final void reAssign(final EntitySetup setup, final Campaign entity,
            final Campaign campaign) {
        // nothing to do here
    }

    /**
     * @param characterFactoryInput the characterFactory to set
     */
    public final void setCharacterFactory(
            final EntityFactory<DndCharacter> characterFactoryInput) {
        this.characterFactory = characterFactoryInput;
    }

    /**
     * @param factoriesInput the inventoryItemFactory to set
     */
    public final void setInventoryItemFactories(
            final Map<Class, EntityFactory<InventoryItem>> factoriesInput) {
        this.inventoryItemFactories = factoriesInput;
    }

    /**
     * @param combatRoundFactoryInput the combatRoundFactory to set
     */
    public final void setCombatRoundFactory(
            final EntityFactory<CombatRound> combatRoundFactoryInput) {
        this.combatRoundFactory = combatRoundFactoryInput;
    }

    /**
     * @return the characterFactory
     */
    public final EntityFactory<DndCharacter> getCharacterFactory() {
        return this.characterFactory;
    }

    /**
     * @return the inventoryItemFactory
     */
    public final Map<Class, EntityFactory<InventoryItem>>
            getInventoryItemFactories() {
        return this.inventoryItemFactories;
    }

    /**
     * @return the combatRoundFactory
     */
    public final EntityFactory<CombatRound> getCombatRoundFactory() {
        return this.combatRoundFactory;
    }

}
