package org.asciicerebrum.mydndgame.domain.setup;

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

    /**
     * Adds a further dnd character setup.
     *
     * @param characterSetup the character setup.
     */
    public final void addDndCharacter(
            final EntitySetup characterSetup) {
        List<EntitySetup> participantSetups
                = (List<EntitySetup>) this.getListSetup()
                .get(SetupProperty.PARTICIPANT_CHARACTERS);
        if (participantSetups == null) {
            participantSetups = new ArrayList<EntitySetup>();
            this.getListSetup().put(SetupProperty.PARTICIPANT_CHARACTERS,
                    participantSetups);
        }
        participantSetups.add(characterSetup);
    }

    /**
     * Adds a further inventory item setup.
     *
     * @param inventorySetup the inventory item setup.
     */
    public final void addInventoryItem(
            final EntitySetup inventorySetup) {
        List<EntitySetup> inventorySetups
                = (List<EntitySetup>) this.getListSetup()
                .get(SetupProperty.INVENTORY_ITEMS);
        if (inventorySetups == null) {
            inventorySetups = new ArrayList<EntitySetup>();
            this.getListSetup().put(SetupProperty.INVENTORY_ITEMS,
                    inventorySetups);
        }
        inventorySetups.add(inventorySetup);
    }

    /**
     * @param combatRoundSetup the combat round setup.
     */
    public final void setCombatRound(
            final EntitySetup combatRoundSetup) {
        this.getSingleSetup().put(SetupProperty.COMBAT_ROUND, combatRoundSetup);
    }

}
