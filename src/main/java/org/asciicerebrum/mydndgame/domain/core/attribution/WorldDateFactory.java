package org.asciicerebrum.mydndgame.domain.core.attribution;

import org.asciicerebrum.mydndgame.domain.core.particles.CombatRoundNumber;
import org.asciicerebrum.mydndgame.domain.core.particles.CombatRoundPosition;
import org.asciicerebrum.mydndgame.domain.gameentities.EntityFactory;
import org.asciicerebrum.mydndgame.domain.gameentities.Reassignments;
import org.asciicerebrum.mydndgame.domain.gameentities.setup.EntitySetup;
import org.asciicerebrum.mydndgame.domain.gameentities.setup.SetupIncompleteException;
import org.asciicerebrum.mydndgame.domain.gameentities.setup.SetupProperty;

/**
 *
 * @author species8472
 */
public class WorldDateFactory implements EntityFactory<WorldDate> {

    @Override
    public WorldDate newEntity(final EntitySetup<WorldDate> setup,
            final Reassignments reassignments) {

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
    public void reAssign(final EntitySetup<WorldDate> setup,
            final WorldDate entity) {
        // nothing to do here
    }

}