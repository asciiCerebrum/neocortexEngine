package org.asciicerebrum.mydndgame;

import org.asciicerebrum.mydndgame.interfaces.entities.IBodySlotType;
import org.asciicerebrum.mydndgame.interfaces.entities.ICharacter;
import org.asciicerebrum.mydndgame.interfaces.entities.ISituationContext;

/**
 *
 * @author species8472
 */
public class SituationContext implements ISituationContext {

    /**
     * The character of the context.
     */
    private ICharacter character;

    /**
     * The contextual body slot type of the character.
     */
    private IBodySlotType bodySlotType;

    /**
     * {@inheritDoc}
     */
    @Override
    public final ICharacter getCharacter() {
        return this.character;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setCharacter(final ICharacter iCharacter) {
        this.character = iCharacter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final IBodySlotType getBodySlotType() {
        return this.bodySlotType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setBodySlotType(final IBodySlotType iBodySlotType) {
        this.bodySlotType = iBodySlotType;
    }

}
