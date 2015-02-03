package org.asciicerebrum.mydndgame.domain.factories;

import org.asciicerebrum.mydndgame.domain.factories.Reassignments;
import org.asciicerebrum.mydndgame.domain.core.particles.CombatRoundPosition;
import org.asciicerebrum.mydndgame.domain.core.particles.UniqueId;
import org.asciicerebrum.mydndgame.domain.game.Campaign;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.factories.EntityFactory;
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

    private Campaign campaign;

    @Override
    public final CombatRoundEntry newEntity(
            final EntitySetup setup,
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
            final EntitySetup setup) {
        final UniqueId uniqueId = new UniqueId(setup.getProperty(
                SetupProperty.COMBAT_ROUND_PARTICIPANT));
        return (DndCharacter) this.campaign.getEntityById(uniqueId);
    }

    public void reAssign(final EntitySetup setup,
            final CombatRoundEntry entity) {
        final DndCharacter participant = this.findParticipant(setup);
        entity.setParticipant(participant);
    }

    /**
     * @param campaignInput the campaign to set
     */
    public final void setCampaign(final Campaign campaignInput) {
        this.campaign = campaignInput;
    }

}