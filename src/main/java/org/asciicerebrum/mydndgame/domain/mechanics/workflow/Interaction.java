package org.asciicerebrum.mydndgame.domain.mechanics.workflow;

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

}
