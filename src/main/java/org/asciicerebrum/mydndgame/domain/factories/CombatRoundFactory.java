package org.asciicerebrum.mydndgame.domain.factories;

import java.util.List;
import org.asciicerebrum.mydndgame.domain.game.CombatRound;
import org.asciicerebrum.mydndgame.domain.game.CombatRoundEntry;
import org.asciicerebrum.mydndgame.domain.mechanics.WorldDate;
import org.asciicerebrum.mydndgame.domain.setup.EntitySetup;
import org.asciicerebrum.mydndgame.domain.setup.SetupIncompleteException;
import org.asciicerebrum.mydndgame.domain.setup.SetupProperty;

/**
 *
 * @author species8472
 */
public class CombatRoundFactory implements EntityFactory<CombatRound> {

    /**
     * The factory for combat round entries.
     */
    private EntityFactory<CombatRoundEntry> combatRoundEntryFactory;

    /**
     * The factory for the world date.
     */
    private EntityFactory<WorldDate> worldDateFactory;

    @Override
    public final CombatRound newEntity(final EntitySetup setup,
            final Reassignments reassignments) {

        if (!setup.isSetupComplete()) {
            throw new SetupIncompleteException("The setup of the combat round"
                    + " is not complete.");
        }

        final CombatRound combatRound = new CombatRound();

        List<EntitySetup> crEntrySetups
                = setup.getPropertySetups(SetupProperty.COMBAT_ROUND_ENTRIES);
        if (crEntrySetups != null) {
            for (EntitySetup crEntrySetup : crEntrySetups) {
                combatRound.addCombatRoundEntry(
                        this.getCombatRoundEntryFactory().newEntity(
                                crEntrySetup, reassignments));
            }
        }

        EntitySetup dateSetup = setup.getPropertySetup(
                SetupProperty.COMBAT_ROUND_CURRENT_DATE);
        if (dateSetup != null) {
            combatRound.setCurrentDate(this.getWorldDateFactory()
                    .newEntity(dateSetup, reassignments));
        }

        return combatRound;
    }

    @Override
    public final void reAssign(final EntitySetup setup,
            final CombatRound entity, final Reassignments reassignments) {
        //nothing to do here
    }

    /**
     * @param combatRoundEntryFactoryIn the combatRoundEntryFactory to set
     */
    public final void setCombatRoundEntryFactory(
            final EntityFactory<CombatRoundEntry> combatRoundEntryFactoryIn) {
        this.combatRoundEntryFactory = combatRoundEntryFactoryIn;
    }

    /**
     * @param worldDateFactoryInput the worldDateFactory to set
     */
    public final void setWorldDateFactory(
            final EntityFactory<WorldDate> worldDateFactoryInput) {
        this.worldDateFactory = worldDateFactoryInput;
    }

    /**
     * @return the combatRoundEntryFactory
     */
    public final EntityFactory<CombatRoundEntry> getCombatRoundEntryFactory() {
        return combatRoundEntryFactory;
    }

    /**
     * @return the worldDateFactory
     */
    public final EntityFactory<WorldDate> getWorldDateFactory() {
        return worldDateFactory;
    }

}
