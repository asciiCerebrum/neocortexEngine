package org.asciicerebrum.mydndgame.builders;

import java.util.Map.Entry;
import org.asciicerebrum.mydndgame.CombatRound;
import org.asciicerebrum.mydndgame.CombatRoundSetup;
import org.asciicerebrum.mydndgame.WorldDate;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.ICombatRound;
import org.asciicerebrum.mydndgame.interfaces.entities.IWorldDate;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author species8472
 */
public class CombatRoundBuilder {

    /**
     * Setup information for the weapon.
     */
    private final CombatRoundSetup setup;
    /**
     * Spring context to get the beans from.
     */
    private final ApplicationContext context;

    /**
     * Constructor for creating a combat round builder.
     *
     * @param setupInput the combat round setup object.
     * @param contextInput the spring application context.
     */
    public CombatRoundBuilder(final CombatRoundSetup setupInput,
            final ApplicationContext contextInput) {
        this.setup = setupInput;
        this.context = contextInput;
    }

    /**
     * Central method on building the combat round.
     *
     * @return the created unique combat round.
     */
    public final ICombatRound build() {

        ICombatRound combatRound = new CombatRound();

        // current date
        IWorldDate currentDate = new WorldDate();
        currentDate.setCombatRoundNumber(setup.getCurrentRoundNumber());
        currentDate.setCombatRoundPosition(setup.getCurrentPosition());
        combatRound.setCurrentDate(currentDate);

        // participants
        for (Entry<String, String> participation
                : setup.getParticipantIdPositionMap().entrySet()) {
            // retrieve the characters from the application context.
            ICharacter character
                    = context.getBean(participation.getKey(), ICharacter.class);
            combatRound.addParticipant(character, participation.getValue());
        }

        return combatRound;
    }

}
