package org.asciicerebrum.mydndgame.interfaces.entities;

import java.util.List;

/**
 *
 * @author species8472
 */
public interface IInteraction {

    /**
     * @return the interactionType
     */
    IInteractionType getInteractionType();

    /**
     * @param interactionType the interactionType to set
     */
    void setInteractionType(IInteractionType interactionType);

    /**
     * @return the triggeringCharacter
     */
    ICharacter getTriggeringCharacter();

    /**
     * @param triggeringCharacter the triggeringCharacter to set
     */
    void setTriggeringCharacter(ICharacter triggeringCharacter);

    /**
     * @return the targetCharacters
     */
    List<ICharacter> getTargetCharacters();

    /**
     * @param targetCharacters the targetCharacters to set
     */
    void setTargetCharacters(List<ICharacter> targetCharacters);

}
