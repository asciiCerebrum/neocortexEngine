package org.asciicerebrum.mydndgame.interfaces.entities;

/**
 *
 * @author species8472
 */
public enum ObserverHook {

    /**
     * The attack action.
     */
    ATTACK,
    /**
     * The damage action.
     */
    DAMAGE,
    /**
     * The melee attack action.
     */
    MELEE_ATTACK,
    /**
     * The damage dice action for melee in particular. E.g. needed for the feat
     * power attack.
     */
    MELEE_DAMAGE,
    /**
     * For the price calculation.
     */
    PRICE,
    /**
     * The ranged attack action.
     */
    RANGED_ATTACK,
    /**
     * The damage dice action for ranged in particular.
     */
    RANGED_DAMAGE;
}
