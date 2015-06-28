package org.asciicerebrum.neocortexengine.domain.events;

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
    CONDITION_LOSE,
    /**
     * The event of applying the inflicted damage.
     */
    DAMAGE_APPLICATION,
    /**
     * The event of inflicting damage.
     */
    DAMAGE_INFLICTED,
    /**
     * The event of some character ending its turn.
     */
    END_TURN_END,
    /**
     * The event of some character starting its turn after the end turn of the
     * previous character.
     */
    END_TURN_START,
    /**
     * The event thrown when the single attack hits normally.
     */
    SINGLE_ATTACK_HIT,
    /**
     * The event thrown when the single attack hits critically.
     */
    SINGLE_ATTACK_HIT_CRITICAL,
    /**
     * The event thrown when the single attack misses.
     */
    SINGLE_ATTACK_MISS,
    /**
     * The event thrown before a single attack is performed.
     */
    SINGLE_ATTACK_PRE,

}
