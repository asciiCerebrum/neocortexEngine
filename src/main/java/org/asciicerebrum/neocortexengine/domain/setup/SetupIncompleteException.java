package org.asciicerebrum.neocortexengine.domain.setup;

/**
 *
 * @author species8472
 */
public class SetupIncompleteException extends RuntimeException {

    /**
     * Constructing the exception.
     *
     * @param message the message to print.
     */
    public SetupIncompleteException(final String message) {
        super(message);
    }

}
