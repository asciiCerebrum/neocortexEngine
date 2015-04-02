package org.asciicerebrum.mydndgame.domain.setup;

/**
 * Key enums for the entity setup and factories.
 *
 * @author species8472
 */
public enum SetupProperty {

    /**
     * The ability being increased during a level up.
     */
    ABILITY_ADVANCEMENT,
    /**
     * The number of this certain level advancement.
     */
    ADVANCEMENT_NUMBER,
    /**
     * A basic ability of the character.
     */
    BASE_ABILITY,
    /**
     * The collection of all base ability entries.
     */
    BASE_ABILITY_ENTRIES,
    /**
     * The ability value of a given ability.
     */
    BASE_ABILITY_VALUE,
    /**
     * The item inside a body slot.
     */
    BODY_SLOT_ITEM,
    /**
     * The kind of body slot.
     */
    BODY_SLOT_TYPE,
    /**
     * If the body slot is used for primary attacks.
     */
    BODY_SLOT_PRIMARY_ATTACK,
    /**
     * The collection of all body slots.
     */
    BODY_SLOTS,
    /**
     * The class level of a character.
     */
    CLASS_LEVEL,
    /**
     * The combat round.
     */
    COMBAT_ROUND,
    /**
     * The current date of a combat round.
     */
    COMBAT_ROUND_CURRENT_DATE,
    /**
     * The entries inside a combat round (tuple of participant and position).
     */
    COMBAT_ROUND_ENTRIES,
    /**
     * The participant of a combat round.
     */
    COMBAT_ROUND_PARTICIPANT,
    /**
     * The participant's position in the combat round.
     */
    COMBAT_ROUND_POSITION,
    /**
     * Point in time when a certain condition of a character expires.
     */
    CONDITION_EXPIRY_DATE,
    /**
     * Point in time when a certain condition of a character begins.
     */
    CONDITION_START_DATE,
    /**
     * The unique entity that is the reason for the condition to take effect.
     */
    CONDITION_CAUSE_ENTITY,
    /**
     * The kind of condition.
     */
    CONDITION_TYPE,
    /**
     * The collection of conditions.
     */
    CONDITIONS,
    /**
     * The XP of a character.
     */
    EXPERIENCE_POINTS,
    /**
     * The feat being added during a level up.
     */
    FEAT_ADVANCEMENT,
    /**
     * The object that is bound to the feat. E.g. for weapon focus.
     */
    FEAT_BINDING,
    /**
     * The type of feat. E.g. weapon finesse, etc.
     */
    FEAT_TYPE,
    /**
     * The HP of a character.
     */
    HIT_POINTS,
    /**
     * The HP being added during a level up.
     */
    HIT_POINTS_ADVANCEMENT,
    /**
     * The non-lethal HP of a character.
     */
    HIT_POINTS_NONLETHAL,
    /**
     * The collection of level advancements of a character.
     */
    LEVEL_ADVANCEMENTS,
    /**
     * The name of a character or an item.
     */
    NAME,
    /**
     * The race of a character.
     */
    RACE,
    /**
     * The size category of a sized object.
     */
    SIZE_CATEGORY,
    /**
     * Mwk, magic, material like cold iron etc.
     */
    SPECIAL_ABILITIES,
    /**
     * The state registry as a whole for a character.
     */
    STATE_REGISTRY,
    /**
     * The unique id of a state object that is to be retrieved from the
     * campagin.
     */
    STATE_REGISTRY_CONTEXT_OBJECT_ID,
    /**
     * The entry for the state registry.
     */
    STATE_REGISTRY_ENTRY,
    /**
     * The particle of the state registry, characterizing the state or condition
     * of the entry.
     */
    STATE_REGISTRY_PARTICLE,
    /**
     * A (numerical, boolean, etc.) value for the state entry.
     */
    STATE_REGISTRY_VALUE,
    /**
     * The value type of the value (numerical, etc.).
     */
    STATE_REGISTRY_VALUE_TYPE,
    /**
     * The id of a unique entity.
     */
    UNIQUEID,
    /**
     * The round identifier in a world date.
     */
    WORLD_DATE_ROUND,
    /**
     * The position identifier in a world date.
     */
    WORLD_DATE_ROUND_POSITION
}
