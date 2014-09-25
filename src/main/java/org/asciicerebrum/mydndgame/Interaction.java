package org.asciicerebrum.mydndgame;

import java.util.List;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.IInteraction;
import org.asciicerebrum.mydndgame.interfaces.entities.IInteractionType;

/**
 *
 * @author species8472
 */
public class Interaction implements IInteraction {

    /**
     * The type of interaction. E.g. attack, full attack, etc.
     */
    private IInteractionType interactionType;

    /**
     * The source of action.
     */
    private ICharacter triggeringCharacter;

    /**
     * The affected characters.
     */
    private List<ICharacter> targetCharacters;

    /**
     * {@inheritDoc}
     */
    @Override
    public final IInteractionType getInteractionType() {
        return interactionType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setInteractionType(
            final IInteractionType interactionTypeInput) {
        this.interactionType = interactionTypeInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final ICharacter getTriggeringCharacter() {
        return triggeringCharacter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setTriggeringCharacter(
            final ICharacter triggeringCharacterInput) {
        this.triggeringCharacter = triggeringCharacterInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<ICharacter> getTargetCharacters() {
        return targetCharacters;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setTargetCharacters(
            final List<ICharacter> targetCharactersInput) {
        this.targetCharacters = targetCharactersInput;
    }

}
