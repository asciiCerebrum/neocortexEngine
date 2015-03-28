package org.asciicerebrum.mydndgame.domain.factories;

import org.asciicerebrum.mydndgame.domain.core.particles.CombatRoundPosition;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.game.Campaign;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.CombatRoundEntry;
import org.asciicerebrum.mydndgame.domain.setup.EntitySetup;
import org.asciicerebrum.mydndgame.domain.setup.SetupIncompleteException;
import org.asciicerebrum.mydndgame.domain.setup.SetupProperty;

/**
 *
 * @author species8472
 */
public class CombatRoundEntryFactory
        implements EntityFactory<CombatRoundEntry> {

    @Override
    public final CombatRoundEntry newEntity(final EntitySetup setup,
            final Campaign campaign, final Reassignments reassignments) {

        if (!setup.isSetupComplete()) {
            throw new SetupIncompleteException("The setup of the combat round "
                    + "entry is not complete.");
        }

        final CombatRoundEntry crEntry = new CombatRoundEntry();

        crEntry.setCombatRoundPosition(new CombatRoundPosition(
                setup.getProperty(SetupProperty.COMBAT_ROUND_POSITION)));

        final DndCharacter participant = this.findParticipant(setup, campaign);
        if (participant == null) {
            reassignments.addEntry(this, setup, crEntry);
        }
        crEntry.setParticipant(participant);

        return crEntry;
    }

    /**
     * Retrieves a dnd character from the campaign by its unique id. The id is
     * given by the setup.
     *
     * @param setup the setup containing the id.
     * @param campaign the campaign as the central entity map.
     * @return the dnd character found.
     */
    final DndCharacter findParticipant(
            final EntitySetup setup, final Campaign campaign) {
        final UniqueId uniqueId = new UniqueId(setup.getProperty(
                SetupProperty.COMBAT_ROUND_PARTICIPANT));
        return (DndCharacter) campaign.getEntityById(uniqueId);
    }

    @Override
    public final void reAssign(final EntitySetup setup,
            final CombatRoundEntry entity, final Campaign campaign,
            final Reassignments reassignments) {
        final DndCharacter participant = this.findParticipant(setup, campaign);
        entity.setParticipant(participant);
    }

}
