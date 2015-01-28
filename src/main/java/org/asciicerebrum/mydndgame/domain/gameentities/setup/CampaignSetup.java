package org.asciicerebrum.mydndgame.domain.gameentities.setup;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author species8472
 */
public class CampaignSetup extends AbstractEntitySetup {

    @Override
    public final boolean isSetupComplete() {
        return true;
    }

    public final void addDndCharacter(
            final EntitySetup characterSetup) {
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
            final EntitySetup inventorySetup) {
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
            final EntitySetup combatRoundSetup) {
        this.singleSetup.put(SetupProperty.COMBAT_ROUND, combatRoundSetup);
    }

}
