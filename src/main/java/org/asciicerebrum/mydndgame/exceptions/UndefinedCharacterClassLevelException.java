package org.asciicerebrum.mydndgame.exceptions;

import org.asciicerebrum.mydndgame.domain.rules.entities.ClassLevels;
import org.asciicerebrum.mydndgame.domain.core.particles.Level;

/**
 *
 * @author species8472
 */
public class UndefinedCharacterClassLevelException extends RuntimeException {

    /**
     * Exception designed for character classes of undefined level.
     *
     * @param chClasses the classLevels in question.
     * @param level the level of undefined value.
     */
    public UndefinedCharacterClassLevelException(final ClassLevels chClasses,
            final Level level) {

        super("Level " + level.getValue() + " not available for CharacterClass "
                + chClasses.getId() + ".");
    }

}
