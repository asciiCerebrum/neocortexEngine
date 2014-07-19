package org.asciicerebrum.mydndgame.exceptions;

import org.asciicerebrum.mydndgame.CharacterClass;

/**
 *
 * @author species8472
 */
public class UndefinedCharacterClassLevelException extends RuntimeException {

    /**
     * Exception designed for character classes of undefined level.
     *
     * @param chClass the class in question.
     * @param level the level of undefined value.
     */
    public UndefinedCharacterClassLevelException(final CharacterClass chClass,
            final Integer level) {

        super("Level " + level + " not available for CharacterClass "
                + chClass.getId() + ".");
    }

}
