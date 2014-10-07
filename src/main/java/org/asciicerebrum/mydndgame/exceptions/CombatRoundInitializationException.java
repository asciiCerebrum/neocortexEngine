package org.asciicerebrum.mydndgame.exceptions;

/**
 *
 * @author species8472
 */
public class CombatRoundInitializationException extends RuntimeException {

    /**
     * Exception for failure in combat round initialization.
     */
    public CombatRoundInitializationException() {
        super("Combat round could not be initialized.");
    }

}
