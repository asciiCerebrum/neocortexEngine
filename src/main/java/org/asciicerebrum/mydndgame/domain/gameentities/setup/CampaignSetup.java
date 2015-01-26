package org.asciicerebrum.mydndgame.domain.gameentities.setup;

import java.util.ArrayList;
import java.util.List;
import org.asciicerebrum.mydndgame.domain.gameentities.Campaign;
import org.asciicerebrum.mydndgame.domain.gameentities.CombatRound;
import org.asciicerebrum.mydndgame.domain.gameentities.DndCharacter;
import org.asciicerebrum.mydndgame.domain.gameentities.InventoryItem;

/**
 *
 * @author species8472
 */
public class CampaignSetup extends AbstractEntitySetup<Campaign> {

    public boolean isSetupComplete() {
        return true;
    }

    public final void addDndCharacter(
            final EntitySetup<DndCharacter> characterSetup) {
        List<EntitySetup> participantSetups
                = (List<EntitySetup>) this.listSetup
                .get(SetupProperty.PARTICIPANT_CHARACTERS);
        if (participantSetups == null) {
            participantSetups = new ArrayList<EntitySetup>();
            this.listSetup.put(SetupProperty.PARTICIPANT_CHARACTERS,
                    participantSetups);
        }
        participantSetups.add(characterSetup);
    }

    public final void addInventoryItem(
            final EntitySetup<InventoryItem> inventorySetup) {
        List<EntitySetup> inventorySetups
                = (List<EntitySetup>) this.listSetup
                .get(SetupProperty.INVENTORY_ITEMS);
        if (inventorySetups == null) {
            inventorySetups = new ArrayList<EntitySetup>();
            this.listSetup.put(SetupProperty.INVENTORY_ITEMS,
                    inventorySetups);
        }
        inventorySetups.add(inventorySetup);
    }

    public final void setCombatRound(
            final EntitySetup<CombatRound> combatRoundSetup) {
        this.singleSetup.put(SetupProperty.COMBAT_ROUND, combatRoundSetup);
    }

}
