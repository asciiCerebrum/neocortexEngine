package org.asciicerebrum.mydndgame.interfaces.entities;

/**
 *
 * @author species8472
 */
public enum ObserverHook {

    /**
     * The dexterity ability modifier.
     */
    ABILITY_DEX,
    /**
     * The strength ability modifier.
     */
    ABILITY_STR,
    /**
     * The intelligence ability modifier.
     */
    ABILITY_INT,
    /**
     * The wisdom ability modifier.
     */
    ABILITY_WIS,
    /**
     * The constitution ability modifier.
     */
    ABILITY_CON,
    /**
     * The charisma ability modifier.
     */
    ABILITY_CHA,
    /**
     * Armor Class. Everything that does not go into the base armor class. E.g.
     * something from conditions like flat-footed.
     */
    AC,
    /**
     * The base armor class. Everything that goes into the standard ac value,
     * like dex modifier etc.
     */
    AC_BASE,
    /**
     * The armor's check penalty.
     */
    ARMOR_CHECK_PENALTY,
    /**
     * The attack action.
     */
    ATTACK,
    /**
     * The damage action.
     */
    DAMAGE,
    /**
     * The processes of applying inflicted damage.
     */
    DAMAGE_APPLICATION,
    /**
     * The initiative action.
     */
    INITIATIVE,
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
