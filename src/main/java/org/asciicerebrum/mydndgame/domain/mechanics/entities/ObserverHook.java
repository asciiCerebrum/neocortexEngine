package org.asciicerebrum.mydndgame.domain.mechanics.entities;

/**
 *
 * @author species8472
 */
public enum ObserverHook {

    /**
     * The base ability - this hook is valid for all abilities.
     */
    ABILITY,
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
     * The maximum dexterity bonus of an armor.
     */
    ARMOR_MAX_DEX_BONUS,
    /**
     * The attack action.
     */
    ATTACK,
    /**
     * The process of applying inflicted conditions.
     */
    CONDITION_APPLICATION,
    /**
     * Factor of a weapon's critical throw.
     */
    CRITICAL_FACTOR,
    /**
     * Critical minimum level of a weapon.
     */
    CRITICAL_MIMINUM_LEVEL,
    /**
     * The damage action.
     */
    DAMAGE,
    /**
     * The processes of applying inflicted damage.
     */
    DAMAGE_APPLICATION,
    /**
     * The slots an item is made for. They could be changed or extended by
     * magical effects, etc.
     */
    DESIGNATED_BODY_SLOT,
    /**
     * Encumbrance. Duh.
     */
    ENCUMBRANCE,
    /**
     * The hit points of a character.
     */
    HIT_POINTS,
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
     * Proficiency. You don't say!
     */
    PROFICIENCY,
    /**
     * The ranged attack action.
     */
    RANGED_ATTACK,
    /**
     * The damage dice action for ranged in particular.
     */
    RANGED_DAMAGE,
    /**
     * The size category of an object or a character.
     */
    SIZE_CATEGORY,
    /**
     * Categories of a weapon.
     */
    WEAPON_CATEGORIES,
    /**
     * Damage dice of a weapon.
     */
    WEAPON_DAMAGE,
    /**
     * Types of a weapon.
     */
    WEAPON_TYPES;
}
