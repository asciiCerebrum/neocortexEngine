package org.asciicerebrum.mydndgame.managers;

import org.asciicerebrum.mydndgame.interfaces.entities.IInteraction;

/**
 *
 * @author species8472
 */
public class CombatRoundManager {

    /**
     * Retrieves the desired interaction from the character and executes it.
     *
     * @param interaction the interaction in question.
     */
    public final void executeInteraction(final IInteraction interaction) {

        interaction.getInteractionType().getWorkflow().runWorkflow(interaction);

    }

}
