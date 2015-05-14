package org.asciicerebrum.mydndgame.domain.events;

/**
 *
 * @author species8472
 */
public enum EventType {

    /**
     * Event thrown directly after the initialization of a new combat round.
     */
    COMBATROUND_POSTINIT,
    /**
     * Event thrown before the initialization of a new combat round.
     */
    COMBATROUND_PREINIT,
    /**
     * The event of gaining a new condition.
     */
    CONDITION_GAIN,
    /**
     * The event of losing a condition.
     */
    CONDITION_LOSE

}
