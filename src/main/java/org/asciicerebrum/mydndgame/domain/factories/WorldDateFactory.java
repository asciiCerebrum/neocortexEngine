package org.asciicerebrum.mydndgame.domain.factories;

import org.asciicerebrum.mydndgame.domain.core.particles.CombatRoundNumber;
import org.asciicerebrum.mydndgame.domain.core.particles.CombatRoundPosition;
import org.asciicerebrum.mydndgame.domain.game.Campaign;
import org.asciicerebrum.mydndgame.domain.mechanics.WorldDate;
import org.asciicerebrum.mydndgame.domain.setup.EntitySetup;
import org.asciicerebrum.mydndgame.domain.setup.SetupIncompleteException;
import org.asciicerebrum.mydndgame.domain.setup.SetupProperty;

/**
 *
 * @author species8472
 */
public class WorldDateFactory implements EntityFactory<WorldDate> {

    @Override
    public final WorldDate newEntity(final EntitySetup setup,
            final Campaign campaign, final Reassignments reassignments) {

        if (!setup.isSetupComplete()) {
            throw new SetupIncompleteException("The setup of the world date "
                    + " is not complete.");
        }

        WorldDate worldDate = new WorldDate();
        worldDate.setCombatRoundNumber(new CombatRoundNumber(setup.getProperty(
                SetupProperty.WORLD_DATE_ROUND)));
        worldDate.setCombatRoundPosition(new CombatRoundPosition(
                setup.getProperty(SetupProperty.WORLD_DATE_ROUND_POSITION)));

        return worldDate;
    }

    @Override
    public final void reAssign(final EntitySetup setup,
            final WorldDate entity, final Campaign campaign,
            final Reassignments reassignments) {
        // nothing to do here
    }

}
