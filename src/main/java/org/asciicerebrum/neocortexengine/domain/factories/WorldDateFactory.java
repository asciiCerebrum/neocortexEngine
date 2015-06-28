package org.asciicerebrum.neocortexengine.domain.factories;

import org.asciicerebrum.neocortexengine.domain.core.particles.CombatRoundNumber;
import org.asciicerebrum.neocortexengine.domain.core.particles.CombatRoundPosition;
import org.asciicerebrum.neocortexengine.domain.mechanics.WorldDate;
import org.asciicerebrum.neocortexengine.domain.setup.EntitySetup;
import org.asciicerebrum.neocortexengine.domain.setup.SetupIncompleteException;
import org.asciicerebrum.neocortexengine.domain.setup.SetupProperty;

/**
 *
 * @author species8472
 */
public class WorldDateFactory implements EntityFactory<WorldDate> {

    @Override
    public final WorldDate newEntity(final EntitySetup setup) {

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

}
