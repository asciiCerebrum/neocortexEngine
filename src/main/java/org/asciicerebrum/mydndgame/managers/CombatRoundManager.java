package org.asciicerebrum.mydndgame.managers;

import javax.naming.OperationNotSupportedException;
import org.asciicerebrum.mydndgame.domain.game.Campaign;
import org.asciicerebrum.mydndgame.domain.game.DndCharacters;
import org.asciicerebrum.mydndgame.domain.mechanics.workflow.Interaction;

/**
 *
 * @author species8472
 */
public interface CombatRoundManager {

    /**
     * Initiates the combat round. Only one round can be initiated at the same
     * time. If one is already initiated, false is returned.
     *
     * @param campaign contains the combat round.
     * @param participants The participants of the combat round.
     * @throws javax.naming.OperationNotSupportedException could be thrown due
     * to the call of other workflows.
     */
    void initiateCombatRound(Campaign campaign, DndCharacters participants)
            throws OperationNotSupportedException;

    /**
     * Retrieves the desired interaction from the character and executes it.
     * Rejects it, if it comes from the wrong character.
     *
     * @param campaign the campaign this interaction takes place in.
     * @param interaction the interaction in question.
     */
    void executeInteraction(Campaign campaign, Interaction interaction);
}
