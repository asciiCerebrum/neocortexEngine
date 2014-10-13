package org.asciicerebrum.mydndgame.interactionworkflows;

import org.asciicerebrum.mydndgame.InteractionResponse;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.ICombatRound;
import org.asciicerebrum.mydndgame.interfaces.entities.IInteraction;
import org.asciicerebrum.mydndgame.interfaces.entities.IInteractionResponse;
import org.asciicerebrum.mydndgame.interfaces.entities.IWorkflow;
import org.asciicerebrum.mydndgame.interfaces.entities.InteractionResponseKey;

/**
 *
 * @author species8472
 */
public class ConditionExpirationWorkflow implements IWorkflow {

    /**
     * {@inheritDoc} It checks all conditions of all participants if they expire
     * in this new combat round position and unregisters them.
     */
    @Override
    public final IInteractionResponse runWorkflow(
            final IInteraction interaction,
            final IInteractionResponse response) {

        ICombatRound combatRound
                = response.getValue(InteractionResponseKey.COMBAT_ROUND,
                        ICombatRound.class);

        for (ICharacter participant : combatRound.getParticipants()) {
            participant.removeConditionsByExpiryDate(
                    combatRound.getCurrentDate());
        }
        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final IInteractionResponse runWorkflow(
            final IInteraction interaction) {
        return this.runWorkflow(interaction, new InteractionResponse());
    }

}
