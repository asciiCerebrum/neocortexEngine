package org.asciicerebrum.neocortexengine.domain.factories;

import org.asciicerebrum.neocortexengine.domain.core.particles.CombatRoundPosition;
import org.asciicerebrum.neocortexengine.domain.core.particles.UniqueId;
import org.asciicerebrum.neocortexengine.domain.game.CombatRoundEntry;
import org.asciicerebrum.neocortexengine.domain.setup.EntitySetup;
import org.asciicerebrum.neocortexengine.domain.setup.SetupIncompleteException;
import org.asciicerebrum.neocortexengine.domain.setup.SetupProperty;

/**
 *
 * @author species8472
 */
public class CombatRoundEntryFactory
        implements EntityFactory<CombatRoundEntry> {

    @Override
    public final CombatRoundEntry newEntity(final EntitySetup setup) {

        if (!setup.isSetupComplete()) {
            throw new SetupIncompleteException("The setup of the combat round "
                    + "entry is not complete.");
        }

        final CombatRoundEntry crEntry = new CombatRoundEntry();

        crEntry.setCombatRoundPosition(new CombatRoundPosition(
                setup.getProperty(SetupProperty.COMBAT_ROUND_POSITION)));

        final UniqueId uniqueId = new UniqueId(setup.getProperty(
                SetupProperty.COMBAT_ROUND_PARTICIPANT));

        crEntry.setParticipantId(uniqueId);

        return crEntry;
    }

}
