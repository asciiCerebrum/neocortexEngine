package org.asciicerebrum.mydndgame.domain.mechanics.workflow;

import org.asciicerebrum.mydndgame.domain.game.Campaign;
import org.asciicerebrum.mydndgame.domain.game.CombatRound;
import org.asciicerebrum.mydndgame.domain.game.DndCharacter;
import org.asciicerebrum.mydndgame.domain.game.DndCharacters;

/**
 *
 * @author species8472
 */
public class Interaction {

    /**
     * The type of interaction. E.g. attack, full attack, etc.
     */
    private InteractionType interactionType;

    /**
     * The source of action.
     */
    private DndCharacter triggeringCharacter;

    /**
     * The affected characters.
     */
    private DndCharacters targetCharacters;

    /**
     * Holds meta information about the combat encounter.
     */
    private CombatRound combatRound;

    /**
     * The campaign this interaction takes place in.
     */
    private final Campaign campaign;

    /**
     * The campaign must be set during the construction.
     *
     * @param campaignInput the campaign of this interaction.
     */
    public Interaction(final Campaign campaignInput) {
        this.campaign = campaignInput;
    }

    /**
     * @return the interaction type.
     */
    public final InteractionType getInteractionType() {
        return interactionType;
    }

    /**
     * @param interactionTypeInput the interaction type.
     */
    public final void setInteractionType(
            final InteractionType interactionTypeInput) {
        this.interactionType = interactionTypeInput;
    }

    /**
     * @return the combatRound
     */
    public final CombatRound getCombatRound() {
        return combatRound;
    }

    /**
     * @param combatRoundInput the combatRound to set
     */
    public final void setCombatRound(final CombatRound combatRoundInput) {
        this.combatRound = combatRoundInput;
    }

    /**
     * @return the triggeringCharacter
     */
    public final DndCharacter getTriggeringCharacter() {
        return triggeringCharacter;
    }

    /**
     * @param triggeringCharacterInput the triggeringCharacter to set
     */
    public final void setTriggeringCharacter(
            final DndCharacter triggeringCharacterInput) {
        this.triggeringCharacter = triggeringCharacterInput;
    }

    /**
     * @return the targetCharacters
     */
    public final DndCharacters getTargetCharacters() {
        return targetCharacters;
    }

    /**
     * @param targetCharactersInput the targetCharacters to set
     */
    public final void setTargetCharacters(
            final DndCharacters targetCharactersInput) {
        this.targetCharacters = targetCharactersInput;
    }

    /**
     * @return the first dnd character targeted.
     */
    public final DndCharacter getFirstTargetCharacter() {
        return this.getTargetCharacters().iterator().next();
    }

    /**
     * @return the campaign
     */
    public final Campaign getCampaign() {
        return campaign;
    }

}
