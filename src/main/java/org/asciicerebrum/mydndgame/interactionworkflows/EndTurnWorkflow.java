package org.asciicerebrum.mydndgame.interactionworkflows;

import org.asciicerebrum.mydndgame.InteractionResponse;
import org.asciicerebrum.mydndgame.interfaces.entities.ICombatRound;
import org.asciicerebrum.mydndgame.interfaces.entities.IInteraction;
import org.asciicerebrum.mydndgame.interfaces.entities.IInteractionResponse;
import org.asciicerebrum.mydndgame.interfaces.entities.IWorkflow;
import org.asciicerebrum.mydndgame.interfaces.entities.InteractionResponseKey;

/**
 *
 * @author species8472
 */
public class EndTurnWorkflow implements IWorkflow {

    /**
     * {@inheritDoc}
     */
    @Override
    public final IInteractionResponse runWorkflow(
            final IInteraction interaction) {
        return this.runWorkflow(interaction, new InteractionResponse());
    }

    /**
     * {@inheritDoc} Moving to the next turn in the combat round.
     */
    @Override
    public final IInteractionResponse runWorkflow(
            final IInteraction interaction,
            final IInteractionResponse response) {

        ICombatRound combatRound
                = response.getValue(InteractionResponseKey.COMBAT_ROUND,
                        ICombatRound.class);

        combatRound.moveToNextPosition();

        return response;
    }

}
