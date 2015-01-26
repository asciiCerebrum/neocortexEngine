package org.asciicerebrum.mydndgame.domain.gameentities;

import org.asciicerebrum.mydndgame.domain.core.particles.CombatRoundPosition;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.gameentities.setup.EntitySetup;
import org.asciicerebrum.mydndgame.domain.gameentities.setup.SetupIncompleteException;
import org.asciicerebrum.mydndgame.domain.gameentities.setup.SetupProperty;

/**
 *
 * @author species8472
 */
public class CombatRoundEntryFactory
        implements EntityFactory<CombatRoundEntry> {

    private Campaign campaign;

    public final CombatRoundEntry newEntity(
            final EntitySetup<CombatRoundEntry> setup,
            final Reassignments reassignments) {

        if (!setup.isSetupComplete()) {
            throw new SetupIncompleteException("The setup of the combat round "
                    + "entry is not complete.");
        }

        final CombatRoundEntry crEntry = new CombatRoundEntry();

        crEntry.setCombatRoundPosition(new CombatRoundPosition(
                setup.getProperty(SetupProperty.COMBAT_ROUND_POSITION)));

        final DndCharacter participant = this.findParticipant(setup);
        if (participant == null) {
            reassignments.addEntry(this, setup, crEntry);
        }
        crEntry.setParticipant(participant);

        return crEntry;
    }

    final DndCharacter findParticipant(
            final EntitySetup<CombatRoundEntry> setup) {
        final UniqueId uniqueId = new UniqueId(setup.getProperty(
                SetupProperty.COMBAT_ROUND_PARTICIPANT));
        return (DndCharacter) this.campaign.getEntityById(uniqueId);
    }

    public void reAssign(final EntitySetup<CombatRoundEntry> setup,
            final CombatRoundEntry entity) {
        final DndCharacter participant = this.findParticipant(setup);
        entity.setParticipant(participant);
    }

}
