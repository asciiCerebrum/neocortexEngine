package org.asciicerebrum.neocortexengine.facades.game;

import org.asciicerebrum.neocortexengine.domain.ruleentities.Encumbrance;
import org.asciicerebrum.neocortexengine.domain.ruleentities.Proficiency;
import org.asciicerebrum.neocortexengine.domain.ruleentities.WeaponCategories;
import org.asciicerebrum.neocortexengine.domain.ruleentities.WeaponCategory;
import org.asciicerebrum.neocortexengine.domain.ruleentities.WeaponType;
import org.asciicerebrum.neocortexengine.domain.ruleentities.WeaponTypes;
import org.asciicerebrum.neocortexengine.domain.core.particles.CriticalFactor;
import org.asciicerebrum.neocortexengine.domain.core.particles.CriticalMinimumLevel;
import org.asciicerebrum.neocortexengine.domain.game.DndCharacter;
import org.asciicerebrum.neocortexengine.domain.game.Weapon;
import org.asciicerebrum.neocortexengine.domain.ruleentities.DamageType;
import org.asciicerebrum.neocortexengine.domain.ruleentities.DiceAction;

/**
 *
 * @author species8472
 */
public interface WeaponServiceFacade extends InventoryItemServiceFacade {

    /**
     * Calculates the modified critical minimum level of a given weapon in the
     * context of a given dnd character.
     *
     * @param weapon the weapon to calculate the value for.
     * @param dndCharacter the context.
     * @return the modified value.
     */
    CriticalMinimumLevel getCriticalMinimumLevel(Weapon weapon,
            DndCharacter dndCharacter);

    /**
     * Calculates the modified critical factor of a given weapon in the context
     * of a given dnd character.
     *
     * @param weapon the weapon to calculate the value for.
     * @param dndCharacter the context.
     * @return the modified value.
     */
    CriticalFactor getCriticalFactor(Weapon weapon, DndCharacter dndCharacter);

    /**
     * Calculates the modified damage throw of a given weapon in the context of
     * a given character.
     *
     * @param weapon the weapon to calculate the value for.
     * @param dndCharacter the context.
     * @return the modified value.
     */
    DiceAction getDamage(Weapon weapon, DndCharacter dndCharacter);

    /**
     * Calculates the current valid damage type of the given weapon in the
     * context of the given character.
     *
     * @param weapon the weapon to calculate the value for.
     * @param dndCharacter the context.
     * @return the current damage type selected.
     */
    DamageType getDamageType(Weapon weapon, DndCharacter dndCharacter);

    /**
     * Calculates the modified available weapon types of a given weapon in the
     * context of a given character.
     *
     * @param weapon the weapon to calculate the value for.
     * @param dndCharacter the context.
     * @return the modified value.
     */
    WeaponTypes getWeaponTypes(Weapon weapon, DndCharacter dndCharacter);

    /**
     * Tests if the given weapon is compatible with a given weapon type in the
     * context of a given character.
     *
     * @param weaponType the weapon type in question.
     * @param weapon the weapon to calculate the value for.
     * @param dndCharacter the context.
     * @return true if compatible, false otherwise.
     */
    boolean hasWeaponType(WeaponType weaponType, Weapon weapon,
            DndCharacter dndCharacter);

    /**
     * Calculates the modified encumbrance of a given weapon in the context of a
     * given dnd character.
     *
     * @param weapon the weapon to calculate the value for.
     * @param dndCharacter the context.
     * @return the modified value.
     */
    Encumbrance getEncumbrance(Weapon weapon, DndCharacter dndCharacter);

    /**
     * Tests if the given weapon is compatible with the given encumbrance in the
     * context of a given character.
     *
     * @param encumbrance the encumbrance in question.
     * @param weapon the weapon to calculate the value for.
     * @param dndCharacter the context.
     * @return true if compatible, false otherwise.
     */
    boolean hasEncumbrance(Encumbrance encumbrance, Weapon weapon,
            DndCharacter dndCharacter);

    /**
     * Calculates the modified proficiency of a given weapon in the context of a
     * dnd character.
     *
     * @param weapon the weapon to calculate the value for.
     * @param dndCharacter the context.
     * @return the modified value.
     */
    Proficiency getProficiency(Weapon weapon, DndCharacter dndCharacter);

    /**
     * Tests if the given weapon is compatible with the given proficiency in the
     * context of a given character.
     *
     * @param proficiency the proficiency in question.
     * @param weapon the weapon to calculate the value for.
     * @param dndCharacter the context.
     * @return true if compatible, false otherwise.
     */
    boolean hasProficiency(Proficiency proficiency, Weapon weapon,
            DndCharacter dndCharacter);

    /**
     * Calculates the modified categories of a given weapon in the context of a
     * dnd character.
     *
     * @param weapon the weapon to calculate the value for.
     * @param dndCharacter the context.
     * @return the modified value.
     */
    WeaponCategories getCategories(Weapon weapon, DndCharacter dndCharacter);

    /**
     * Tests if the given weapon is compatible with the given attackMode in the
     * context of a given character.
     *
     * @param attackMode the attackMode in question.
     * @param weapon the weapon to calculate the value for.
     * @param dndCharacter the context.
     * @return true if compatible, false otherwise.
     */
    boolean isAttackModeCompatible(WeaponCategory attackMode, Weapon weapon,
            DndCharacter dndCharacter);

}
